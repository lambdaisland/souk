(ns user)

(try
  (alter-var-root #'*print-namespace-maps* (constantly false))
  (catch Exception _))
(try
  (set! *print-namespace-maps* false)
  (catch Exception _))

(defmacro jit [sym]
  `(requiring-resolve '~sym))

(def sys-id :lambdaisland/souk-dev)

(defn go []
  ((jit lambdaisland.webbing.dev/register!) sys-id 'lambdaisland.souk.setup/dev-setup)
  ((jit lambdaisland.webbing.dev/start!) {:sys-id sys-id}))

(defn stop! []
  ((jit lambdaisland.webbing.dev/stop!) {:sys-id sys-id}))

(defn restart []
  ((jit lambdaisland.webbing.dev/restart) sys-id))

(defn reset []
  (stop!)
  ((jit clojure.tools.namespace.repl/set-refresh-dirs) "src" "test")
  ((jit clojure.tools.namespace.repl/refresh) :after `restart))

(defn reset-all []
  (stop!)
  ((jit clojure.tools.namespace.repl/set-refresh-dirs) "src" "test")
  ((jit clojure.tools.namespace.repl/refresh-all) :after `restart))

(def portal-instance (atom nil))

(defn portal
  "Open a Portal window and register a tap handler for it. The result can be
  treated like an atom."
  []
  (let [p ((jit portal.api/open) @portal-instance)]
    (reset! portal-instance p)
    (add-tap (jit portal.api/submit))
    p))

(defn system []
  ((jit k16.gx.beta.system/values) sys-id))

(defn value [k]
  (get ((jit k16.gx.beta.system/values) sys-id) k))

(defn browse []
  ((jit clojure.java.browse/browse-url) "http://localhost:9400/live"))

(comment

  (value :http/server)
)
