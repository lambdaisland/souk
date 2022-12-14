(ns lambdaisland.souk.components.db
  (:require [lambdaisland.souk.sql :as sql]
            [next.jdbc :as jdbc]
            [next.jdbc.date-time :as jdbc-date-time]
            [next.jdbc.plan]
            [next.jdbc.result-set :as rs]
            [next.jdbc.sql :as nsql]
            [cheshire.core :as json]
            [lambdaisland.glogc :as log])
  (:import (com.mchange.v2.c3p0 ComboPooledDataSource)))

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
          (map (comp pg-coerce val) entity)
          (map (comp pg-coerce val) entity)))))

(defn start! [{:keys [props schema]}]
  (let [ds (doto (ComboPooledDataSource.)
             (.setDriverClass "com.impossibl.postgres.jdbc.PGDriver")
             (.setJdbcUrl (:url props)))]
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
