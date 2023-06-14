(ns lambdaisland.souk.components.router
  "Reitit routes and router"
  (:require
   [lambdaisland.souk.activitypub :as activitypub]
   [lambdaisland.souk.db :as db]
   [lambdaisland.souk.util.dev-router :as dev-router]
   [lambdaisland.uri :as uri]
   [muuntaja.core :as muuntaja]
   [reitit.dev.pretty :as pretty]
   [reitit.ring :as reitit-ring]
   [reitit.ring.coercion :as reitit-coercion]
   [reitit.ring.middleware.muuntaja :as reitit-muuntaja]
   [reitit.ring.middleware.parameters :as reitit-parameters]))

(defn db-conn [req]
  (get-in req [:souk/ctx :storage/db]))

(defn origin [req]
  (get-in req [:souk/ctx :instance/origin]))

(defn routes [opts]
  [["/"
    {:get
     {:handler
      (fn [req]
        {:status 200
         :body   "OK!"})}}]
   ["/.well-known/webfinger"
    {:get
     {:handler
      (fn [{:keys [query-params] :as req}]
        (tap> req)
        (let [{:strs [resource]}        query-params
              _ (tap> resource)
              {:keys [domain username]} (doto (activitypub/parse-user-resource
                                               (origin req)
                                               resource)
                                          tap>)]
          (if-let [user (db/retrieve (db-conn req)
                                     :activitystreams/Actor
                                     (assoc (uri/uri (origin req))
                                            :path (str "/users/" username))
                                     )]
            {:status 200
             :body
             {:subject resource
              :aliases [(:activitystreams/url user)
                        (:rdf/id user)
                        (str "acct:" domain "@" username)]
              :links
              [{:rel  "http://webfinger.net/rel/profile-page"
                :type "text/html"
                :href (:activitystreams/url user)}
               {:rel  "self"
                :type "application/activity+json"
                :href (:rdf/id user)}]}}
            {:status 404})))}}]
   ["/users/:user-id"
    {:get
     {:handler
      (fn [{:keys [path-params] :as req}]
        {:status 200
         :body   (activitypub/externalize
                  (db/retrieve
                   (db-conn req)
                   :activitystreams/Actor
                   (assoc (uri/uri (origin req))
                          :path (str "/users/" (:user-id path-params)))))})}}]])

(defn wrap-request-context [handler ctx]
  (fn [req]
    (handler
     (assoc req :souk/ctx ctx))))

(defn create-router
  [props]
  (reitit.ring/router
   (into [] (remove nil? (routes props)))
   {:exception pretty/exception
    :data {:muuntaja muuntaja/instance
           :middleware [;; ↓↓↓ request passes through middleware top-to-bottom ↓↓↓
                        reitit-parameters/parameters-middleware
                        reitit-muuntaja/format-negotiate-middleware
                        reitit-muuntaja/format-response-middleware
                        reitit-muuntaja/format-request-middleware
                        reitit-coercion/coerce-response-middleware
                        reitit-coercion/coerce-request-middleware
                        [wrap-request-context props]
                        ;; ↑↑↑ response passes through middleware bottom-to-top ↑↑↑
                        ]}}))

(defn start! [{:keys [props]}]
  (if (:dev-router? props)
    (dev-router/dev-router #(create-router props))
    (create-router props)))

(def component
  {:gx/start {:gx/processor start!}})
