(ns lambdaisland.souk.db
  (:require
   [charred.api :as json]
   [lambdaisland.souk.activitypub :as activitypub]
   [lambdaisland.souk.sql :as sql]
   [next.jdbc.result-set :as rs]
   [next.jdbc :as jdbc]))

(defn pg-coerce [val]
  (cond
    (instance? java.time.ZonedDateTime val)
    (.toOffsetDateTime val)
    :else
    val))

(defn upsert-sql [table entity props]
  (into [(sql/sql 'insert-into table
                  (cons :rdf/props
                        (map key entity))
                  'values
                  (repeat (inc (count entity)) '?)
                  'on-conflict [:raw "(\"rdf/id\")"]
                  'do
                  'update-set
                  (into [:bare-list]
                        (map (fn [[k]]
                               [k '= '?]))
                        entity))]
        (cons
         (json/write-json-str props)
         (concat
          (map (comp pg-coerce val) entity)
          (map (comp pg-coerce val) entity)))))

(defn upsert! [{:keys [ds schema] :as conn} entity]
  (let [rdf-type (:rdf/type entity)
        {:keys [table properties]} (get schema rdf-type)
        known-props (keys properties)
        json-props (apply dissoc entity known-props)
        _
        (assert table (str "Don't know how to store "
                           (if rdf-type rdf-type (str "entity " entity))))
        entity-props (into {}
                           (map (fn [[k v]]
                                  [k
                                   (let [type (get properties k)]
                                     (case type
                                       'rdf/iri
                                       (activitypub/kw->iri
                                        (if (map? v)
                                          (do
                                            (upsert! conn v)
                                            (:rdf/id v))
                                          v))
                                       v))]))
                           (select-keys entity known-props))]
    (jdbc/execute! ds (upsert-sql table entity-props json-props))))

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
    (->MyMapResultSetBuilder rs rsmeta cols)))

(defn retrieve [{:keys [ds schema] :as conn} type iri]
  (let [{:keys [table]} (get schema type)]
    (let [{:rdf/keys [props] :as result}
          (jdbc/execute-one! ds [(sql/sql 'select '* 'from table 'where :rdf/id '= '?)
                                 (str iri)]
                             {:builder-fn my-builder})]
      (when result
        (merge (dissoc result :rdf/props)
               (doto (json/read-json props :key-fn keyword) prn))))))

;; (upsert!
;;  (user/value :storage/db)
;;  p)

;; p

;; (retrieve  (user/value :storage/db) :activitystreams/Actor "http://example.com/users/amber30")
