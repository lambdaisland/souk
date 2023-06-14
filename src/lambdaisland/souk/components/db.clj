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

(defn start! [{:keys [props]}]
  {:schema (:schema props)
   :ds (doto (ComboPooledDataSource.)
         (.setDriverClass "com.impossibl.postgres.jdbc.PGDriver")
         (.setJdbcUrl (:url props)))})

(defn stop! [{ds :value}]
  )

(def component
  {:gx/start {:gx/processor #'start!}
   :gx/stop {:gx/processor #'stop!}})
