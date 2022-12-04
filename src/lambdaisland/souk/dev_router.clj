(ns lambdaisland.souk.dev-router
  "Reitit router wrapper that auto-rebuilds, for REPL workflows."
  (:require [reitit.core :as reitit]))

(defn dev-router
  "Given a function which builds a reitit router, returns a router which rebuilds
  the router on every call. This makes sure redefining routes in a REPL works as
  expected. Should only every be used in development mode, since it completely
  undoes all of reitit's great performance."
  [new-router]
  (reify reitit/Router
    (router-name [_] (reitit/router-name (new-router)))
    (routes [_] (reitit/routes (new-router)))
    (compiled-routes [_] (reitit/compiled-routes (new-router)))
    (options [_] (reitit/options (new-router)))
    (route-names [_] (reitit/route-names (new-router)))
    (match-by-path [_ path] (reitit/match-by-path (new-router) path))
    (match-by-name [_ name] (reitit/match-by-name (new-router) name))
    (match-by-name [_ name path-params] (reitit/match-by-name (new-router) name path-params))))
