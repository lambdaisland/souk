(ns repl-sessions.second-stream
  (:require [lambdaisland.souk.db :as db]
            [lambdaisland.souk.activitypub :as ap]
            [lambdaisland.souk.sql :as sql]
            [next.jdbc :as jdbc]))

(let [{:keys [schema ds]} (user/value :storage/db)
      table :activitystreams/Actor
      person (ap/GET "https://toot.cat/users/plexus/")]
  #_
  (jdbc/execute! ds
                 (db/insert-sql table
                                (select-keys person (get schema table))
                                (apply dissoc person :rdf/type (get schema table))))
  [(select-keys person (get schema table))
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
