(ns lambdaisland.souk.components.template)

(defn start! [{:keys [props]}]
  )

(defn stop! [{:keys [value]}]
  )

(def component
  {:gx/start {:gx/processor #'start!
              :gx/props-schema [:map]}
   :gx/stop {:gx/processor #'stop!}})
