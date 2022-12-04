(ns lambdaisland.souk.setup
  (:require [lambdaisland.webbing.config :as config]
            [clojure.java.io :as io]))

(def project 'lambdaisland/souk)
(def start-keys #{:http/server})

(def schemas
  {:settings [[:port int?]
              [:dev/reload-routes? boolean?]]
   :secrets []})

(defn proj-resource [path]
  (io/resource (str project "/" path)))

(defn prod-setup []
  {:schemas schemas
   :keys    start-keys
   :sources {:config   [(proj-resource "config.edn")]
             :secrets  [(config/cli-args)
                        (config/env)]
             :settings [(config/cli-args)
                        (config/env)
                        (config/default-value)
                        (proj-resource "settings-prod.edn")
                        (proj-resource "settings.edn")]}})

(defn dev-setup []
  (let [local-file (io/file "config.local.edn")
        local-config (when (.exists local-file)
                       (read-string (slurp local-file)))]
    {:schemas schemas
     :keys    (:dev/start-keys local-config start-keys)
     :sources {:config   [(proj-resource "config.edn")
                          (dissoc local-config :dev/start-keys)]
               :secrets   [(config/dotenv)
                           (config/env)
                           (config/default-value)]
               :settings [(config/dotenv)
                          (config/env)
                          (config/default-value)
                          (proj-resource "settings-dev.edn")
                          (proj-resource "settings.edn")]}}))
