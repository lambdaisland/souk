(ns lambdaisland.souk.activitypub
  (:require [lambdaisland.souk.json-ld :as ld]))

(set! *print-namespace-maps* false)

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
   "rdfs" "http://www.w3.org/2000/01/rdf-schema#"})

(defn GET [url]
  (ld/internalize (ld/expand (:body (ld/json-get url))) common-prefixes))
