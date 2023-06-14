(ns lambdaisland.souk.components.db-schema
  (:require
   [aero.core :as aero]
   [lambdaisland.glogc :as log]
   [lambdaisland.souk.sql :as sql]
   [lambdaisland.uri :as uri]
   [next.jdbc :as jdbc]
   [next.jdbc.result-set :as rs]))

(set! *warn-on-reflection* true)

(def default-properties
  [[:rdf/id 'text 'primary-key]
   [:rdf/type 'text]
   [:rdf/props 'jsonb 'default "{}"]
   [:meta/created-at 'timestamp-with-time-zone 'default [:fn 'now] 'not-null]
   [:meta/updated-at 'timestamp-with-time-zone]])

(def set-ts-trigger-def "CREATE OR REPLACE FUNCTION trigger_set_timestamp()\nRETURNS TRIGGER AS $$\nBEGIN\n  NEW.\"meta/updated-at\" = NOW();\n  RETURN NEW;\nEND;\n$$ LANGUAGE plpgsql;")

(def pg-types
  '{text text
    rdf/iri text
    datetime timestamp-with-time-zone})

(defn jdbc-url->db-name [url]
  (-> url
      uri/uri
      :path
      uri/uri
      :path
      (subs 1)))

(defn table-columns
  ([ds]
   (table-columns ds nil))
  ([ds opts]
   (with-open [conn (jdbc/get-connection ds opts)]
     (let [md     (.getMetaData conn)
           cols   #(rs/datafiable-result-set
                    (.getColumns md nil nil % nil) ds)
           tables #(rs/datafiable-result-set
                    (.getTables md nil nil nil
                                (into-array ["TABLE" "VIEW"])) ds opts)]
       (into {}
             (map (fn [{:pg_class/keys [TABLE_NAME]}]
                    [(keyword TABLE_NAME)
                     (into #{} (map (comp keyword :COLUMN_NAME)) (cols TABLE_NAME))]))
             (tables))))))

(defn create-table! [ds table-name columns]
  (log/info :table/creating {:name table-name :columns columns})
  (jdbc/execute! ds [(sql/sql 'create-table 'if-not-exists table-name
                              (concat
                               default-properties
                               columns))])
  (jdbc/execute! ds [(sql/sql 'drop-trigger 'if-exists :set-timestamp
                              'on table-name)]))

(defn create-table-sql [table properties]
  [(sql/sql 'create-table table properties)])

(defn add-columns-sql [table properties]
  [(sql/sql 'alter-table table
            (into [:bare-list]
                  (map (fn [[col type]]
                         ['add col type]))
                  properties))])

(defn migrate-tables! [url schemas]
  (doseq [[table {:keys [properties store-as]}] schemas
          :when (not store-as)]
    (let [ds (jdbc/get-datasource url)
          table-cols (table-columns ds nil)
          all-props (concat
                     default-properties
                     (for [[column type] properties]
                       [column (get pg-types type)]))]
      (if (contains? table-cols table)
        (when-let [new-props (seq (remove (fn [[col]]
                                            (get-in table-cols [table col]))
                                          all-props))]
          (jdbc/execute! ds (add-columns-sql table new-props))
          (log/info :table/altered {:table table :new-props (map first new-props)}))
        (do
          (jdbc/execute! ds (create-table-sql table all-props))
          (jdbc/execute! ds [(sql/sql 'create-trigger :set-timestamp
                                      'before-update
                                      'on table
                                      'for-each-row
                                      'execute-procedure [:fn 'trigger_set_timestamp])])
          (log/info :table/created {:table table :properties (map first all-props)}))))))

(defn start! [{{:keys [url admin-url schemas]} :props}]
  (try
    (jdbc/execute! (jdbc/get-datasource admin-url)
                   [(sql/sql ['create-database [:ident (jdbc-url->db-name url)]])])
    (log/info :database/created {:url url})
    (catch Exception e
      ;; as a poor-man's CREATE IF NOT EXISTS, we swallow this particular error,
      ;; and rethrow anything else.
      (when-not (re-find #"database.*already exists" (.getMessage e))
        (throw e))))
  (let [ds (jdbc/get-datasource url)]
    (jdbc/execute! ds [set-ts-trigger-def])
    (let [schemas (apply merge (map aero/read-config schemas))
          _ (migrate-tables! url schemas)]
      (into {}
            (map (fn [[type {:keys [properties store-as]}]]
                   [type
                    (cond-> {:table (or store-as type)
                             :properties
                             (into {:rdf/id 'rdf/iri
                                    :rdf/type 'rdf/iri}
                                   (or properties
                                       (get-in schemas [store-as :properties])))})]))
            schemas))))

(def component
  {:gx/start {:gx/processor #'start!}})

(user/value :storage/schema)
