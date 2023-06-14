(ns lambdaisland.souk.activitypub
  "Interact with ActivityPub instances"
  (:require
   [clojure.string :as str]
   [lambdaisland.souk.json-ld :as ld]
   [lambdaisland.uri :as uri]))

(def common-prefixes
  {"dcterms" "http://purl.org/dc/terms/"
   "ldp" "http://www.w3.org/ns/ldp#"
   "schema" "http://schema.org/"
   "vcard" "http://www.w3.org/2006/vcard/ns#"
   "mastodon" "http://joinmastodon.org/ns#"
   "security" "https://w3id.org/security#"
   "activitystreams" "https://www.w3.org/ns/activitystreams#"
   "xsd" "http://www.w3.org/2001/XMLSchema#"
   "owl" "http://www.w3.org/2002/07/owl#"
   "rdfs" "http://www.w3.org/2000/01/rdf-schema#"
   "ostatus" "http://ostatus.org#"})

(def default-context
  ["https://www.w3.org/ns/activitystreams"
   "https://w3id.org/security/v1"
   {"identityKey"               {"@type" "@id" "@id" "toot:identityKey"}
    "EncryptedMessage"          "toot:EncryptedMessage"
    "Ed25519Key"                "toot:Ed25519Key"
    "devices"                   {"@type" "@id" "@id" "toot:devices"}
    "manuallyApprovesFollowers" "as:manuallyApprovesFollowers"
    "schema"                    "http://schema.org#"
    "PropertyValue"             "schema:PropertyValue"
    "Curve25519Key"             "toot:Curve25519Key"
    "claim"                     {"@type" "@id" "@id" "toot:claim"}
    "value"                     "schema:value"
    "movedTo"                   {"@id" "as:movedTo" "@type" "@id"}
    "discoverable"              "toot:discoverable"
    "messageType"               "toot:messageType"
    "messageFranking"           "toot:messageFranking"
    "cipherText"                "toot:cipherText"
    "toot"                      "http://joinmastodon.org/ns#"
    "alsoKnownAs"               {"@id" "as:alsoKnownAs" "@type" "@id"}
    "featured"                  {"@id" "toot:featured" "@type" "@id"}
    "featuredTags"              {"@id" "toot:featuredTags" "@type" "@id"}
    "Ed25519Signature"          "toot:Ed25519Signature"
    "focalPoint"                {"@container" "@list" "@id" "toot:focalPoint"}
    "fingerprintKey"            {"@type" "@id" "@id" "toot:fingerprintKey"}
    "Device"                    "toot:Device"
    "publicKeyBase64"           "toot:publicKeyBase64"
    "deviceId"                  "toot:deviceId"
    "suspended"                 "toot:suspended"}])

(defn GET [url]
  (ld/internalize (ld/expand (:body (ld/json-get url))) common-prefixes))

(defn kw->iri [kw]
  (if (string? kw)
    kw
    (let [prefix (namespace kw)
          base (get common-prefixes prefix)]
      (assert base (str "Base IRI not found for prefix: " prefix))
      (str base (name kw)))))

(defn externalize [v]
  (ld/externalize v default-context common-prefixes))

(defn parse-user-resource [origin resource]
  (let [origin (uri/uri origin)
        iri    (uri/uri resource)]
    (cond
      (#{"http" "https"} (:scheme iri))
      (if (and (= (:scheme origin) (:scheme iri))
               (= (:host origin) (:host iri)))
        (if-let [[_ _ u] (re-find #"^/(u|users)/([^/]+)$" (:path iri))]
          {:domain   (:host iri)
           :username u}))
      (#{"acct"} (:scheme iri))
      (let [[username domain] (str/split (:path iri) #"@")]
        {:domain   domain
         :username username}))))
