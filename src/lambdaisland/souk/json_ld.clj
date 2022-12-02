(ns lambdaisland.souk.json-ld
  (:require [hato.client :as hato]
            [clojure.string :as str]
            [clojure.walk :as walk]))

(defn json-get [url]
  (hato/get url
            {:headers {"Accept" "application/json"}
             :http-client {:redirect-policy :normal}
             :as :json-string-keys}))

(def context-cache (atom {})) ;; url -> context

(defn fetch-context [url]
  (if-let [ctx (get @context-cache url)]
    ctx
    (get
     (swap! context-cache
            (fn [cache]
              (assoc cache url (get (:body (json-get url)) "@context"))))
     url)))

(defn expand-context
  ([new-context]
   (expand-context {} new-context))
  ([current-context new-context]
   (cond
     (string? new-context)
     (expand-context current-context (fetch-context new-context))

     (sequential? new-context)
     (reduce expand-context current-context new-context)

     (map? new-context)
     (into current-context
           (map
            (fn [[k v]]
              (let [id (if (map? v) (get v "@id" v) v)
                    [prefix suffix] (str/split id #":")]
                (if-let [base (get (merge current-context new-context) prefix)]
                  [k (assoc (if (map? v) v {})
                            "@id" (str (if (map? base) (get base "@id") base)
                                       suffix))]
                  [k (if (map? v) v {"@id" v})]))))
           new-context))))

(defn expand-id [id ctx]
  (if (string? id)
    (if-let [t (get-in ctx [id "@id"])]
      t
      (if (str/includes? id ":")
        (let [[prefix suffix] (str/split id #":")]
          (if-let [prefix-url (get-in ctx [prefix "@id"])]
            (str prefix-url suffix)
            id))
        id))
    id))

(defn apply-context [v ctx]
  (cond
    (map? v)
    (into {}
          (map (fn [[k v]]
                 (let [attr (get ctx k)
                       k (get-in ctx [k "@id"] k)
                       v (apply-context v ctx)]
                   [k (if attr
                        (cond
                          (and (#{"@id" "@type"} k) (string? v))
                          (expand-id v ctx)
                          (and (= "@id" (get attr "@type")) (string? v))
                          (assoc attr "@id" (expand-id v ctx))
                          :else
                          (assoc (dissoc (cond-> attr
                                           (contains? attr "@type")
                                           (update "@type" expand-id ctx))
                                         "@id")
                                 "@value" v))
                        v)])))
          v)

    (sequential? v)
    (into (empty v) (map #(apply-context % ctx)) v)

    (and (string? v) (str/includes? v ":"))
    (expand-id v ctx)

    :else
    v))

(defn expand [json-ld]
  (let [ctx (expand-context {} (get json-ld "@context"))]
    (apply-context (dissoc json-ld "@context") ctx)))

(defn internalize [v prefixes]
  (let [shorten #(if (and (string? %) (str/includes? % "://"))
                   (or (some (fn [[ns url]]
                               (when (.startsWith % url)
                                 (keyword ns
                                          (subs % (.length url)))))
                             prefixes)
                       %)
                   %)]
    (cond
      (sequential? v)
      (into (empty v) (map #(internalize % prefixes)) v)

      (map? v)
      (-> v
          (cond-> (contains? v "@type")
            (doto prn)
            (contains? v "@type")
            (update "@type" shorten))
          (update-keys (fn [k]
                         (case k
                           "@id" :rdf/id
                           "@type" :rdf/type
                           "@value" :rdf/value
                           (if-let [kw (shorten k)]
                             kw
                             k))))
          (update-vals (fn [v]
                         (cond
                           (map? v)
                           (let [{id "@id" type "@type" value "@value"} v]
                             (cond
                               (and (= "@id" type) (contains? v "@id"))
                               id
                               (contains? v "@value")
                               (case type
                                 "http://www.w3.org/2001/XMLSchema#dateTime"
                                 (java.time.ZonedDateTime/parse value)
                                 (internalize value prefixes))
                               :else
                               (internalize v prefixes)))

                           (string? v)
                           (shorten v)

                           :else
                           (internalize v prefixes)))))
      :else
      v)))

;; (compact
;;  (expand (:body (json-get "https://toot.cat/users/plexus")))
;;  common-prefixes)

;; "name"
;; "profileName"
