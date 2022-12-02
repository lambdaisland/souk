(ns repl-sessions.pg-stuff
  (:require [clojure.string :as str]
            [next.jdbc :as jdbc]
            [honey.sql :as sql]
            [next.jdbc.sql :as nsql]
            [next.jdbc.date-time :as jdbc-date-time]
            [next.jdbc.result-set :as rs]
            [next.jdbc.plan]
            [cheshire.core :as json]
            ))

(defn pg-url [db-name]
  (str "jdbc:pgsql://localhost:5432/" db-name "?user=postgres"))

(defn recreate-db! [name]
  (let [ds (jdbc/get-datasource (pg-url "postgres"))]
    (jdbc/execute! ds [(str "DROP DATABASE IF EXISTS " name)])
    (jdbc/execute! ds [(str "CREATE DATABASE " name)])))

(recreate-db! "souk")

(defn sql-ident [v]
  (if (sequential? v)
    (str/join "." (map identifier v))
    (str "\""
         (if (keyword? v)
           (subs (str v) 1)
           v)
         "\"")))

(defn sql-kw [k]
  (str/upper-case
   (str/replace
    (if (keyword? k)
      (name k)
      k)
    #"-" " ")))

(defn sql-str [& ss]
  (str "'" (str/replace (apply str ss) #"'" "''") "'"))

(defn sql-list
  ([items]
   (sql-list "(" ")" ", " items))
  ([before after separator items]
   (str before (apply str (str/join separator items)) after)))

(defn strs [& items]
  (str/join " " items))

(defn sql [& items]
  (apply strs
         (map (fn [x]
                (cond
                  (vector? x)
                  (case (first x)
                    :ident (sql-ident (second x))
                    :kw (sql-kw (second x))
                    :str (sql-str (second x))
                    :raw (second x)
                    :list (sql-list (map sql (next x)))
                    :commas (sql-list "" "" ", " (map sql (next x)))
                    :fn (str (second x)
                             (sql-list (map sql (nnext x))))
                    (apply sql x))
                  (keyword? x)
                  (sql-ident x)
                  (symbol? x)
                  (sql-kw x)
                  (string? x)
                  (sql-str x)
                  (sequential? x)
                  (sql-list "(" ")" ", " (map sql x))))
              items)))

(sql 'create-table :activitystreams/Person
     (list
      [:souk/id 'text 'primary-key]
      [:souk/properties 'jsonb 'default "{}"] ))

(def set-ts-trigger-def "CREATE OR REPLACE FUNCTION trigger_set_timestamp()\nRETURNS TRIGGER AS $$\nBEGIN\n  NEW.\"meta/updated-at\" = NOW();\n  RETURN NEW;\nEND;\n$$ LANGUAGE plpgsql;")
(def default-properties
  [[:rdf/id 'text 'primary-key]
   [:rdf/props 'jsonb 'default "{}"]
   [:meta/created-at 'timestamp-with-time-zone 'default [:fn 'now] 'not-null]
   [:meta/updated-at 'timestamp-with-time-zone]])

(let [ds (jdbc/get-datasource (pg-url "souk"))]
  (jdbc/execute! ds [set-ts-trigger-def])
  (jdbc/execute! ds [(sql 'drop-table
                          'if-exists
                          :activitystreams/Person)])
  (jdbc/execute! ds [(sql 'create-table :activitystreams/Person
                          (concat
                           default-properties
                           [[:activitystreams/name 'text 'not-null]
                            [:activitystreams/preferredUsername 'text 'not-null]
                            [:activitystreams/url 'text 'not-null]
                            [:activitystreams/summary 'text]
                            [:ldp/inbox 'text 'not-null]
                            [:activitystreams/outbox 'text 'not-null]
                            [:activitystreams/published 'timestamp-with-time-zone]]))])
  (jdbc/execute! ds [(sql 'create-trigger :set-timestamp
                       'before-update
                       'on :activitystreams/Person
                       'for-each-row
                       'execute-procedure [:fn 'trigger_set_timestamp])]))
(def table-columns
  (into {}
        (let [ds (jdbc/get-datasource (pg-url "souk"))
              opts {}]
          (with-open [con (jdbc/get-connection ds opts)]
            (let [md (.getMetaData con)] ; produces java.sql.DatabaseMetaData
              (doall
               (for [{:keys [pg_class/TABLE_NAME]} (-> md
                                                       ;; return a java.sql.ResultSet describing all tables and views:
                                                       (.getTables nil nil nil (into-array ["TABLE" "VIEW"]))
                                                       (rs/datafiable-result-set ds opts))]
                 [(keyword TABLE_NAME)
                  (map (comp keyword :COLUMN_NAME) (rs/datafiable-result-set (.getColumns md nil nil TABLE_NAME nil) ds opts)
                       )
                  ])))))))
(to-clj (expand (:body (json-fetch "https://toot.cat/users/plexus")))
        clojure-prefixes)

(defn pg-coerce [val]
  (cond
    (instance? java.time.ZonedDateTime val)
    (.toOffsetDateTime val)
    #_[:raw (strs (sql-kw 'timestamp-with-time-zone)
                (sql-str

                 (.toLocalDate ^java.time.ZonedDateTime val) " "
                 (let [t (.toLocalTime ^java.time.ZonedDateTime val)]
                   (format "%d:%02d:%02d" (.getHour t)
                           (.getMinute t) (.getSecond t)))
                 (.getZone ^java.time.ZonedDateTime val)))]
    :else
    val))

(defn insert-sql [entity]
  (let [{:rdf/keys [type]} entity
        cols (get table-columns type)
        props (seq (select-keys entity cols))]
    (into [(sql 'insert-into type
                (cons :rdf/props
                      (map key props))
                'values
                (repeat (inc (count props)) '?)
                'on-conflict [:raw "(\"rdf/id\")"]
                'do
                'update-set
                (into [:commas]
                      (map (fn [[k]]
                             [k '= '?]))
                      props))]
          (cons
           (json/encode (apply dissoc entity :rdf/type (map key props)))
           (concat
            (map (comp pg-coerce val) props)
            (map (comp pg-coerce val) props))))))

(let [ds (jdbc/get-datasource (pg-url "souk"))]
  (jdbc/execute! ds (insert-sql (assoc repl-sessions.json-ld-stuff/plexus-profile
                                       :activitystreams/name "John Doe")))
  )

(defrecord MyMapResultSetBuilder [^java.sql.ResultSet rs rsmeta cols]
  rs/RowBuilder
  (->row [this] (transient {}))
  (column-count [this] (count cols))
  (with-column [this row i]
    (rs/with-column-value this row (nth cols (dec i))
      (if (= java.sql.Types/TIMESTAMP_WITH_TIMEZONE (.getColumnType rsmeta i))
        (.getObject rs ^Integer i ^Class java.time.OffsetDateTime)
        (rs/read-column-by-index (.getObject rs ^Integer i) rsmeta i))))
  (with-column-value [this row col v]
    (assoc! row col v))
  (row! [this row] (persistent! row))
  rs/ResultSetBuilder
  (->rs [this] (transient []))
  (with-row [this mrs row]
    (conj! mrs row))
  (rs! [this mrs] (persistent! mrs)))

(defn my-builder
  [rs opts]
  (let [rsmeta (.getMetaData rs)
        cols   (rs/get-unqualified-column-names rsmeta opts)]
    (def rs rs)
    (def meta rsmeta)
    (def cols cols)
    (->MyMapResultSetBuilder rs rsmeta cols)))
(.getColumnType meta 3)

(let [ds (jdbc/get-datasource (pg-url "souk"))]
  (jdbc/execute-one! ds [(sql 'select '* 'from :activitystreams/Person)]

                     {:builder-fn my-builder})
  )


(pg-coerce (java.time.ZonedDateTime/parse "2017-04-11T00:00Z"))

{:activitystreams/followers "@id",
 :activitystreams/published "xsd:dateTime",
 :mastodon/devices "@id",
 :json-ld/type nil,
 :activitystreams/outbox "@id",
 :activitystreams/following "@id",
 :activitystreams/endpoints "@id",
 :activitystreams/name nil,
 :activitystreams/icon "@id",
 :security/publicKey "@id",
 :mastodon/featured "@id",
 :activitystreams/manuallyApprovesFollowers nil,
 :activitystreams/summary nil,
 :activitystreams/image "@id",
 :activitystreams/tag "@id",
 :mastodon/discoverable nil,
 :json-ld/id nil,
 :activitystreams/preferredUsername nil,
 :activitystreams/url "@id",
 :ldp/inbox "@id",
 :activitystreams/attachment "@id",
 :mastodon/featuredTags "@id"}
