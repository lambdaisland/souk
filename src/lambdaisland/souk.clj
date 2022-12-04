(ns lambdaisland.souk
  (:require [lambdaisland.webbing.prod :as prod]
            [lambdaisland.souk.setup :as setup]))

(defn -main [& _]
  (prod/go (setup/prod-setup)))
