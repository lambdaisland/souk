(ns first-experiments
  (:require
   [clojure.string :as str]
   [hato.client :as hato])
  (:import
   (com.apicatalog.jsonld JsonLd)
   (com.apicatalog.jsonld.document JsonDocument)))

(set! *warn-on-reflection* true)
#_
(:body
 (hato/get #_"https://toot.cat/.well-known/webfinger?resource=acct:plexus@toot.cat"
           #_"https://toot.cat/users/plexus"
           #_"https://www.w3.org/ns/activitystreams"
           {:headers {"Accept" "application/json"}
            :as :stream}))

(defn json-fetch [url]
  (hato/get url
            {:headers {"Accept" "application/json"}
             :http-client {:redirect-policy :normal}
             :as :json-string-keys}))

(defn expand-context
  ([new-context]
   (expand-context {} new-context))
  ([current-context new-context]
   (cond
     (string? new-context)
     (do (println '->> new-context)
         (expand-context current-context
                         (get (:body (json-fetch new-context)) "@context")))
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

(defn apply-context [v ctx]
  (into {}
        (map (fn [[k v]]
               (let [attr (get ctx k)
                     k (get-in ctx [k "@id"] k)
                     v (cond
                         (map? v)
                         (apply-context v ctx)

                         (= "@type" k)
                         (get-in ctx [v "@id"] v)

                         :else
                         v)]
                 [k (if attr
                      (assoc (dissoc attr "@id") "@value" v)
                      v)])))
        v))

(let [user (:body (json-fetch "https://toot.cat/users/plexus"))
      ctx (expand-context {} (get user "@context"))]
  (apply-context (dissoc user "@context") ctx)
  )

(:body (json-fetch "https://www.w3.org/ns/activitystreams"))

(def clojure-prefixes
  {"http://purl.org/dc/terms/" "org.purl.dc"
   "http://www.w3.org/ns/ldp#" "org.w3.ldp"
   "http://schema.org#" "org.schema"
   "http://www.w3.org/2006/vcard/ns#" "org.w3.vcard"
   "http://joinmastodon.org/ns#" "org.joinmastodon"
   "https://w3id.org/security#" "org.w3id.security"
   "https://www.w3.org/ns/activitystreams#" "org.w3.activitystreams"
   "http://www.w3.org/2001/XMLSchema#" "org.w3.xmlns"})

(json-fetch "https://toot.cat/users/plexus")

(def context (expand-context "https://toot.cat/users/plexus"))
(keep (comp #(get % "@id") val) context)
"https://toot.cat/@plexus/109403085288497274"

(expand-context "https://www.w3.org/ns/activitystreams#Image")

#_(.get (com.apicatalog.jsonld.JsonLd/expand "https://toot.cat/users/plexus"))

;; JsonLd.expand("https://w3c.github.io/json-ld-api/tests/expand/0001-in.jsonld")
;;       .ordered()
;;       .get();

(def ^JsonDocument json-doc
  (JsonDocument/of
   ^java.io.InputStream
   (:body
    (hato/get "https://toot.cat/users/plexus"
              {:headers {"Accept" "application/json"}
               :as :stream}))))

(def expanded (.get (JsonLd/expand json-doc)))
(def flattened (.get (JsonLd/flatten json-doc)))
(def rdf (.get (JsonLd/toRdf json-doc)))

(defn to-clj [v]
  (cond
    (instance? java.util.List v)
    (into [] (map to-clj) v)

    (instance? java.util.Map v)
    (update-vals v to-clj)

    (instance? jakarta.json.JsonString v)
    (.getString ^jakarta.json.JsonString v)

    (instance? jakarta.json.JsonValue v)
    (let [t (.getValueType ^jakarta.json.JsonValue v)]
      (cond
        (= t jakarta.json.JsonValue$ValueType/TRUE) true
        (= t jakarta.json.JsonValue$ValueType/FALSE) false)
      )

    :else v
    ))

(to-clj expanded)

;; (clojure.reflect/reflect jakarta.json.JsonValue)
;; (clojure.reflect/reflect rdf)

(clojure.walk/postwalk
 (fn [v]
   (if (map? v)
     (update-keys v (fn [k]
                      (case k
                        "@id" :json-ld/id
                        "@type" :json-ld/type
                        "@value" :json-ld/value
                        (if-let [kw (and (string? k)
                                         (some (fn [[url ns]]
                                                 (when (.startsWith k url)
                                                   (keyword ns
                                                            (subs k (.length url)))))
                                               clojure-prefixes))]
                          kw
                          k))))
     v))
 (to-clj expanded))

(set! *print-namespace-maps* false)
