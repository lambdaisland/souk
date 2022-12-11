(ns repl-sessions.webfinger
  (:require
   [cheshire.core :as json]
   [clojure.string :as str]
   [hato.client :as hato]
   [lambdaisland.uri :as uri]))

;; all of these work
"acct:plexus@toot.cat"
"http://toot.cat/@plexus"
"https://toot.cat/@plexus"
"http://toot.cat/users/plexus"
"https://toot.cat/users/plexus"

;; rel seems to be ignored

(defn fetch-webfinger [domain resource]
  (-> (uri/uri "https:///.well-known/webfinger")
      (assoc :host domain)
      (uri/assoc-query :resource resource)
      str
      (hato/get {:as :json})
      :body))

(fetch-webfinger "toot.cat" "acct:plexus@toot.cat")

{:subject "acct:plexus@toot.cat",
 :aliases ["https://toot.cat/@plexus" "https://toot.cat/users/plexus"],
 :links
 [{:rel "http://webfinger.net/rel/profile-page",
   :type "text/html",
   :href "https://toot.cat/@plexus"}
  {:rel "self",
   :type "application/activity+json",
   :href "https://toot.cat/users/plexus"}
  {:rel "http://ostatus.org/schema/1.0/subscribe",
   :template "https://toot.cat/authorize_interaction?uri={uri}"}]}
