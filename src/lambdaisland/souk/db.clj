(ns lambdaisland.souk.db
  (:require [lambdaisland.souk.sql :as sql]
            [next.jdbc :as jdbc]
            [next.jdbc.date-time :as jdbc-date-time]
            [next.jdbc.plan]
            [next.jdbc.result-set :as rs]
            [next.jdbc.sql :as nsql]
            [cheshire.core :as json]
            [lambdaisland.glogc :as log])
  (:import (com.mchange.v2.c3p0 ComboPooledDataSource)))

(def default-properties
  [[:rdf/id 'text 'primary-key]
   [:rdf/type 'text]
   [:rdf/props 'jsonb 'default "{}"]
   [:meta/created-at 'timestamp-with-time-zone 'default [:fn 'now] 'not-null]
   [:meta/updated-at 'timestamp-with-time-zone]])

(def set-ts-trigger-def "CREATE OR REPLACE FUNCTION trigger_set_timestamp()\nRETURNS TRIGGER AS $$\nBEGIN\n  NEW.\"meta/updated-at\" = NOW();\n  RETURN NEW;\nEND;\n$$ LANGUAGE plpgsql;")

(def tables
  [[:activitystreams/Actor
    [[:activitystreams/name 'text 'not-null]
     [:activitystreams/preferredUsername 'text 'not-null]
     [:activitystreams/url 'text 'not-null]
     [:activitystreams/summary 'text]
     [:ldp/inbox 'text 'not-null]
     [:activitystreams/outbox 'text 'not-null]
     [:activitystreams/published 'timestamp-with-time-zone]]]])

(defn create-table! [ds table-name columns]
  (log/info :table/creating {:name table-name :columns columns})
  (jdbc/execute! ds [(sql/sql 'create-table 'if-not-exists table-name
                              (concat
                               default-properties
                               columns))])
  (jdbc/execute! ds [(sql/sql 'drop-trigger 'if-exists :set-timestamp
                              'on table-name)])
  (jdbc/execute! ds [(sql/sql 'create-trigger  :set-timestamp
                              'before-update
                              'on table-name
                              'for-each-row
                              'execute-procedure [:fn 'trigger_set_timestamp])]))

(defn pg-coerce [val]
  (cond
    (instance? java.time.ZonedDateTime val)
    (.toOffsetDateTime val)
    :else
    val))

(defn insert-sql [table entity props]
  (into [(sql/sql 'insert-into table
                  (cons :rdf/props
                        (map key entity))
                  'values
                  (repeat (inc (count entity)) '?)
                  'on-conflict [:raw "(\"rdf/id\")"]
                  'do
                  'update-set
                  (into [:commas]
                        (map (fn [[k]]
                               [k '= '?]))
                        entity))]
        (cons
         (json/encode props)
         (concat
          (map (comp pg-coerce val) props)
          (map (comp pg-coerce val) props)))))

(defn start! [{:keys [props]}]
  (let [ds (doto (ComboPooledDataSource.)
             (.setDriverClass "com.impossibl.postgres.jdbc.PGDriver")
             (.setJdbcUrl (:url props)))]
    (jdbc/execute! ds [set-ts-trigger-def])
    (doseq [[table columns] tables]
      (create-table! ds table columns))
    (let [table-columns
          (into {}
                (with-open [con (jdbc/get-connection ds {})]
                  (let [md (.getMetaData con)]
                    (doall
                     (for [{:keys [pg_class/TABLE_NAME]}
                           (-> md
                               (.getTables nil nil nil (into-array ["TABLE" "VIEW"]))
                               (rs/datafiable-result-set ds {}))]
                       [(keyword TABLE_NAME)
                        (map (comp keyword :COLUMN_NAME)
                             (rs/datafiable-result-set (.getColumns md nil nil TABLE_NAME nil) ds {}))])))))]
      {:schema table-columns
       :ds ds})))

(defn stop! [{ds :value}]
  #_(.close ds))

;; cpds.setUser("dbuser");
;; cpds.setPassword("dbpassword");
(def component
  {:gx/start {:gx/processor #'start!}
   :gx/stop {:gx/processor #'stop!}})
