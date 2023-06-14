(ns lambdaisland.souk.factories
  (:require
   [clojure.string :as str]
   [lambdaisland.facai :as f]
   [lambdaisland.faker :as faker]
   [lambdaisland.uri :as uri]))

(defn fake [faker]
  (fn []
    (faker/fake faker)))

(def now #(java.time.Instant/now))

(def ^:dynamic *origin* "http://example.com")

(defn local-uri [& parts]
  (str (uri/join *origin* (str "/" (str/join "/" parts)))))

(f/defactory Person
  {:rdf/type                  :activitystreams/Person
   :souk/origin               (fn [] *origin*)
   :activitystreams/name      (fake #{[:simpsons :characters] [:tolkien :characters]})
   :activitystreams/summary   (fake [:lorem :sentence])
   :activitystreams/published now}

  :after-build
  (fn [ctx]
    (f/update-result
     ctx
     (fn [{:activitystreams/keys [name] :as res}]
       (let [username (str (str/lower-case (first (str/split name #" ")))
                           (rand-int 100))]
         (assoc res
                :rdf/id (local-uri "users" username)
                :activitystreams/preferredUsername username
                :activitystreams/url (local-uri "u" username)
                :ldp/inbox (local-uri "users" username "inbox")
                :activitystreams/outbox (local-uri "users" username "outbox")))))))

(f/defactory Note
  {:rdf/id (local-uri "/notes/" (rand-int 999999999))
   :rdf/type :activitystreams/Note
   :activitystreams/attributedTo Person
   :activitystreams/content [:lorem :sentence]})

(binding [*origin* "https://dev.squid.casa"]
  (Person))

(Note)

(binding [*origin* "https://dev.squid.casa"]
  (lambdaisland.souk.db/upsert!
   (user/value :storage/db)
   (Person)
   ))

@plexus@toot.cat
https://toot.cat/users/plexus
https://toot.cat/@plexus
acct:plexus@toot.cat
