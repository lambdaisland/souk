(ns lambdaisland.souk.components.router
  "Reitit routes and router"
  (:require
   [lambdaisland.souk.util.dev-router :as dev-router]
   [muuntaja.core :as muuntaja]
   [reitit.dev.pretty :as pretty]
   [reitit.ring :as reitit-ring]
   [reitit.ring.coercion :as reitit-coercion]
   [reitit.ring.middleware.muuntaja :as reitit-muuntaja]
   [reitit.ring.middleware.parameters :as reitit-parameters]))

(defn routes [opts]
  [["/"
    {:get
     {:handler
      (fn [req]
        {:status 200
         :body "OK!"})}}]])

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
