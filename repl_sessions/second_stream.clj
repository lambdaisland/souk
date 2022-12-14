(ns repl-sessions.second-stream
  (:require [lambdaisland.souk.db :as db]
            [lambdaisland.souk.activitypub :as ap]
            [lambdaisland.souk.sql :as sql]
            [next.jdbc :as jdbc]))

(let [{:keys [schema ds]} (user/value :storage/db)
      table :activitystreams/Actor
      person (ap/GET "https://toot.cat/users/plexus/")]

  (jdbc/execute! ds
                 (db/insert-sql table
                                (select-keys person (get schema table))
                                (apply dissoc person :rdf/type (get schema table))))
  #_[(select-keys person (get schema table))
     (apply dissoc person :rdf/type (get schema table))]
  )

(count
 {:rdf/type :activitystreams/Person,
  :rdf/id "https://toot.cat/users/plexus",
  :activitystreams/published
  "2017-04-11T00:00Z",
  :activitystreams/outbox "https://toot.cat/users/plexus/outbox",
  :activitystreams/name "Arne Brasseur",
  :activitystreams/summary
  "<p>Founder of Gaiwan.co — creator of Kaocha — drinks tea and writes Clojure, in that order.</p><p>Alts: <span class=\"h-card\"><a href=\"https://mastodon.social/@plexus\" class=\"u-url mention\">@<span>plexus</span></a></span> / <span class=\"h-card\"><a href=\"https://toot.berlin/@plexus\" class=\"u-url mention\">@<span>plexus</span></a></span></p>",
  :activitystreams/preferredUsername "plexus",
  :activitystreams/url "https://toot.cat/@plexus",
  :ldp/inbox "https://toot.cat/users/plexus/inbox"})

(jdbc/execute! (:ds (user/value :storage/db)) [(sql/sql 'drop-table :activitystreams/Actor)])

(ap/GET "https://plexus.osrx.chat/users/plexus")
(ap/GET "https://plexus.osrx.chat/users/plexus/outbox")
(ap/GET "https://plexus.osrx.chat/users/plexus/outbox?page=true")

{:rdf/id "https://plexus.osrx.chat/users/plexus/outbox",
 :rdf/type :activitystreams/OrderedCollection,
 :activitystreams/totalItems 1,
 :activitystreams/first
 "https://plexus.osrx.chat/users/plexus/outbox?page=true",
 :activitystreams/last
 "https://plexus.osrx.chat/users/plexus/outbox?min_id=0&page=true"}


{:rdf/type :activitystreams/Note,
 :rdf/id "https://plexus.osrx.chat/users/plexus/statuses/109495602955086656",
 :activitystreams/inReplyTo nil,
 :activitystreams/published #inst "2022-12-11T14:51:48Z"
 :activitystreams/to ["https://www.w3.org/ns/activitystreams#Public"],
 :activitystreams/sensitive false,
 :activitystreams/cc ["https://plexus.osrx.chat/users/plexus/followers"],
 :activitystreams/attributedTo "https://plexus.osrx.chat/users/plexus",
 :activitystreams/summary nil,
 :activitystreams/tag [],
 :ostatus/conversation "https://www.w3.org/ns/activitystreams#tagplexus.osrx.chat,2022-12-11",
 :activitystreams/replies {:rdf/id
                           "https://plexus.osrx.chat/users/plexus/statuses/109495602955086656/replies",
                           :rdf/type :activitystreams/Collection,
                           :activitystreams/first
                           {:rdf/type :activitystreams/CollectionPage,
                            :activitystreams/next
                            "https://plexus.osrx.chat/users/plexus/statuses/109495602955086656/replies?only_other_accounts=true&page=true",
                            :activitystreams/partOf
                            "https://plexus.osrx.chat/users/plexus/statuses/109495602955086656/replies",
                            :activitystreams/items []}},
 :ostatus/inReplyToAtomUri nil,
 :ostatus/atomUri "https://plexus.osrx.chat/users/plexus/statuses/109495602955086656",
 :activitystreams/url "https://plexus.osrx.chat/@plexus/109495602955086656",
 :activitystreams/attachment [],
 :activitystreams/content {"en" "<p>Hello, world!</p>"}}
