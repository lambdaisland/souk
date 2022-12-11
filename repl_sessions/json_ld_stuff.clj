(ns repl-sessions.json-ld-stuff
  (:require
   [cheshire.core :as json]
   [clojure.string :as str]
   [hato.client :as hato])
  (:import (org.jsoup Jsoup)))

(set! *print-namespace-maps* false)

(def json-ld-spec "https://www.w3.org/TR/json-ld11/")

(def doc (Jsoup/parse ^String (slurp json-ld-spec)))

(def examples
  (for [example (.select doc ".example")
        :let [title (first (.select example ".example-title"))
              pre (first (.select example "pre"))]
        :when (and title pre)]
    (do
      (doseq [comment (.select pre ".comment")]
        (.remove comment))
      [(str/replace (.text title) #"^: " "")
       (try
         (json/parse-string (.text (first (.select example "pre")))
                            )
         (catch Exception e
           e))])))

(defn example [title]
  (get (into {} examples) title))

(defn json-fetch [url]
  (hato/get url
            {:headers {"Accept" "application/json"}
             :http-client {:redirect-policy :normal}
             :as :json-string-keys}))

(def expand-context
  (memoize
   (fn
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
              new-context))))))

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
  (into {}
        (map (fn [[k v]]
               (let [attr (get ctx k)
                     k (get-in ctx [k "@id"] k)
                     v (cond
                         (map? v)
                         (apply-context v ctx)

                         (= "@type" k)
                         (expand-id v ctx)

                         :else
                         v)]
                 (prn [k v])
                 [k (if attr
                      (if (= "@id" (get attr "@type"))
                        (assoc attr "@id" (expand-id v ctx))
                        (assoc (dissoc (cond-> attr
                                         (contains? attr "@type")
                                         (update "@type" expand-id ctx))
                                       "@id") "@value" v))
                      v)])))
        v))

(defn expand [json-ld]
  (let [ctx (expand-context {} (get json-ld "@context"))]
    (apply-context (dissoc json-ld "@context") ctx)))

(def clojure-prefixes
  {"dcterms" "http://purl.org/dc/terms/"
   "ldp" "http://www.w3.org/ns/ldp#"
   "schema" "http://schema.org#"
   "vcard" "http://www.w3.org/2006/vcard/ns#"
   "mastodon" "http://joinmastodon.org/ns#"
   "security" "https://w3id.org/security#"
   "activitystreams" "https://www.w3.org/ns/activitystreams#"
   "xsd" "http://www.w3.org/2001/XMLSchema#"})

(defn to-clj [json-ld prefixes]
  (let [shorten #(if (string? %)
                   (or (some (fn [[ns url]]
                               (when (.startsWith % url)
                                 (keyword ns
                                          (subs % (.length url)))))
                             prefixes)
                       %)
                   %)]
    (clojure.walk/postwalk
     (fn [v]
       (if (map? v)
         (-> v
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
                              (cond
                                (and (= "@id" (:rdf/type v))
                                     (contains? v :rdf/id))
                                (:rdf/id v)
                                (contains? v :rdf/value)
                                (case (:rdf/type v)
                                  :xsd/dateTime
                                  (java.time.ZonedDateTime/parse (:rdf/value v))
                                  (:rdf/value v))
                                :else
                                v)
                              (string? v)
                              (shorten v)
                              :else
                              v))))
         v))
     (expand json-ld))))

(defn compact [entity context prefix-map]
  (let [ctx (into {} (map (juxt (comp #(get % "@id") val) key))
                  (expand-context context))
        kw->iri (fn [k]
                  (let [iri (str (get prefix-map (namespace k))
                                 (name k))]
                    (if-let [n (get ctx iri)]
                      n
                      (if-let [n (some (fn [[k v]]
                                         (when (.startsWith iri k)
                                           (str v ":" (subs iri (count k)))))
                                       ctx)]
                        n
                        k))))
        expand-kv (fn expand-kv [[k v]]
                    (cond
                      (not (keyword? k))
                      [k v]
                      (= :rdf/id k)
                      ["@id" v]
                      (= :rdf/type k)
                      ["@type" (kw->iri v)]
                      :else
                      [(kw->iri k)
                       (cond
                         (map? v)
                         (into {} (map expand-kv) v)
                         (sequential? v)
                         (map #(if (map? %)
                                 (into {} (map expand-kv) %)
                                 %) v)
                         :else v)]))]
    (into {"@context" context}
          (map expand-kv)
          (dissoc entity :rdf/id :rdf/type))))


(comment
  (println (json/encode (assoc (expand (example "Referencing a JSON-LD context"))
                               "@context" (expand-context {} (get (example "Referencing a JSON-LD context") "@context")))))
  (println (json/encode (assoc (expand (example "Referencing a JSON-LD context"))
                               "@context" {"foaf" "http://xmlns.com/foaf/0.1/"})))


  (println (json/encode(example "Referencing a JSON-LD context")))

  (to-clj (example "Referencing a JSON-LD context")
          (update-vals (expand-context (get (example "Referencing a JSON-LD context") "@context"))
                       #(get % "@id")))

  (def raw-profile (:body (json-fetch "https://toot.cat/users/plexus")))
  (def profile-ctx (expand-context (get raw-profile "@context")))
  (def expanded-profile (expand raw-profile))
  (def plexus-profile
    (to-clj expanded-profile clojure-prefixes))

  (get raw-profile "@context")

  (expand-context "https://www.w3.org/ns/activitystreams")

  (compact plexus-profile
           ["https://w3id.org/security/v1"
            "https://www.w3.org/ns/activitystreams"
            {"toot" "http://joinmastodon.org/ns#",}]
           clojure-prefixes)




  (update-vals (to-clj (expand )
                       clojure-prefixes)
               #(or (:rdf/type %)
                    %))
  (to-clj (expand (:body (json-fetch "https://toot.cat/users/plexus/followers?page=1")))
          clojure-prefixes)

    (->> "https://www.w3.org/ns/activitystreams#OrderedCollectionPage"
         json-fetch
         :body
         (#(get % "@context"))
         vals
         (keep #(get % "@type"))
         (into #{}))

    (to-clj (expand (:body (json-fetch "https://toot.cat/users/plexus/collections/featured",)))
            clojure-prefixes)


  (example "Loading a relative context")
  (to-clj (expand (example "In-line context definition"))
          {"org.schema" "http://schema.org/"}))

"Values of @id are interpreted as IRI"
"IRIs can be relative"
"IRI as a key"
"Term expansion from context definition"
"Type coercion"
"Identifying a node"
"Specifying the type for a node"
"Specifying multiple types for a node"
"Using a term to specify the type"
"Referencing Objects on the Web"
"Embedding Objects"
"Using multiple contexts"
"Describing disconnected nodes with @graph"
"Embedded contexts within node objects"
"Combining external and local contexts"
"Setting @version in context"
"Using a default vocabulary"
"Using the null keyword to ignore data"
"Using a default vocabulary relative to a previous default vocabulary"
"Use a relative IRI reference as node identifier"
"Setting the document base in a document"
"Using \"#\" as the vocabulary mapping"
"Using \"#\" as the vocabulary mapping (expanded)"
"Prefix expansion"
"Using vocabularies"
"Expanded document used to illustrate compact IRI creation"
"Compact IRI generation context (1.0)"
"Compact IRI generation term selection (1.0)"
"Compact IRI generation context (1.1)"
"Compact IRI generation term selection (1.1)"
"Aliasing keywords"
"IRI expansion within a context"
"Using a term to define the IRI of another term within a context"
"Using a compact IRI as a term"
"Illegal Aliasing of a compact IRI to a different IRI"
"Associating context definitions with IRIs"
"Illegal circular definition of terms within a context"
"Defining an @context within a term definition"
"Defining an @context within a term definition used on @type"
"Expansion using embedded and scoped contexts"
"Expansion using embedded and scoped contexts (embedding equivalent)"
"Marking a context to not propagate"
"A remote context to be imported in a type-scoped context"
"Sourcing a context in a type-scoped context and setting it to propagate"
"Result of sourcing a context in a type-scoped context and setting it to propagate"
"Sourcing a context to modify @vocab and a term definition"
"Result of sourcing a context to modify @vocab and a term definition"
"A protected term definition can generally not be overridden"
"A protected @context with an exception"
"Overriding permitted if both definitions are identical"
"overriding permitted in property scoped context"
"Expanded term definition with type coercion"
"Expanded value with type"
"Example demonstrating the context-sensitivity for @type"
"Example demonstrating the context-sensitivity for @type (statements)"
"JSON Literal"
"Expanded term definition with types"
"Term expansion for values, not identifiers"
"Terms not expanded when document-relative"
"Term definitions using IRIs and compact IRIs"
"Setting the default language of a JSON-LD document"
"Clearing default language"
"Expanded term definition with language"
"Language map expressing a property in three languages"
"Overriding default language using an expanded value"
"Removing language information using an expanded value"
"Setting the default base direction of a JSON-LD document"
"Clearing default base direction"
"Expanded term definition with language and direction"
"Overriding default language and default base direction using an expanded value"
"Multiple values with no inherent order"
"Using an expanded form to set multiple values"
"Multiple array values of different types"
"An ordered collection of values in JSON-LD"
"Specifying that a collection is ordered in the context"
"Coordinates expressed in GeoJSON"
"Coordinates expressed in JSON-LD"
"An unordered collection of values in JSON-LD"
"Specifying that a collection is unordered in the context"
"Setting @container: @set on @type"
"Nested properties"
"Nested properties folded into containing object"
"Defining property nesting - Expanded Input"
"Defining property nesting - Context"
"Defining property nesting"
"Referencing node objects"
"Embedding a node object as property value of another node object"
"Referencing an unidentified node"
"Specifying a local blank node identifier"
"Indexing data in JSON-LD"
"Indexing data using @none"
"Property-based data indexing"
"Indexing languaged-tagged strings in JSON-LD"
"Indexing languaged-tagged strings in JSON-LD with @set representation"
"Indexing languaged-tagged strings using @none for no language"
"Indexing data in JSON-LD by node identifiers"
"Indexing data in JSON-LD by node identifiers with @set representation"
"Indexing data in JSON-LD by node identifiers using @none"
"Indexing data in JSON-LD by type"
"Indexing data in JSON-LD by type with @set representation"
"Indexing data in JSON-LD by type using @none"
"Included Blocks"
"Flattened form for included blocks"
"Describing disconnected nodes with @included"
"A document with children linking to their parent"
"A person and its children using a reverse property"
"Using @reverse to define reverse properties"
"Identifying and making statements about a graph"
"Using @graph to explicitly express the default graph"
"Context needs to be duplicated if @graph is not used"
"Implicitly named graph"
"Indexing graph data in JSON-LD"
"Indexing graphs using @none for no index"
"Referencing named graphs using an id map"
"Referencing named graphs using an id map with @none"
"Sample JSON-LD document to be expanded"
"Expanded form for the previous example"
"Sample expanded JSON-LD document"
"Sample context"
"Compact form of the sample document once sample context has been applied"
"Compacting using a default vocabulary"
"Compacting using a base IRI"
"Coercing Values to Strings"
"Using Arrays for Lists"
"Reversing Node Relationships"
"Indexing language-tagged strings"
"Forcing Object Values"
"Indexing language-tagged strings and @set"
"Term Selection"
"Sample JSON-LD document to be flattened"
"Flattened and compacted form for the previous example"
"Sample library frame"
"Flattened library objects"
"Framed library objects"
"Referencing a JSON-LD context from a JSON document via an HTTP Link Header"
"Specifying an alternate location via an HTTP Link Header"
"Embedding JSON-LD in HTML"
"Combining multiple JSON-LD script elements into a single dataset"
"Using the document base URL to establish the default base IRI"
"Embedding JSON-LD containing HTML in HTML"
"Targeting a specific script element by id"
"Illegal Unconnected Node"
"Linked Data Dataset"
"Sample JSON-LD document"
"Flattened and expanded form for the previous example"
"Turtle representation of expanded/flattened document"
"A set of statements serialized in Turtle"
"The same set of statements serialized in JSON-LD"
"Embedding in Turtle"
"Same embedding example in JSON-LD"
"JSON-LD using native data types for numbers and boolean values"
"Same example in Turtle using typed literals"
"A list of values in Turtle"
"Same example with a list of values in JSON-LD"
"RDFa fragment that describes three people"
"Same description in JSON-LD (context shared among node objects)"
"HTML that describes a book using microdata"
"Same book description in JSON-LD (avoiding contexts)"
"HTTP Request with profile requesting an expanded document"
"HTTP Request with profile requesting a compacted document"
"HTTP Request with profile requesting a compacted document with a reference to a compaction context"
