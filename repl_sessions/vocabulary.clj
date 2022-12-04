(ns repl-sessions.vocabulary
  (:require [lambdaisland.souk.json-ld :as ld]
            [lambdaisland.souk.activitypub :refer :all]))

(ld/expand (:body (ld/json-get "https://raw.githubusercontent.com/gobengo/activitystreams2-spec-scraped/master/data/activitystreams-vocabulary/1528589057.json")))

(let [{:owl/keys [imports]} (GET "https://raw.githubusercontent.com/gobengo/activitystreams2-spec-scraped/master/data/activitystreams-vocabulary/1528589057.json")]
  (for [[k {:owl/keys [members]}] imports
        {:rdf/keys [id]} members]))

{:rdf/id :activitystreams/Follow,
 :rdf/type :owl/Class,
 :schema/workExample {:rdf/id "https://www.w3.org/TR/activitystreams-vocabulary/#ex15-jsonld",
                      :rdf/type "https://schema.org/CreativeWork",
                      :schema/mainEntity {:rdf/type :activitystreams/Follow,
                                          :activitystreams/actor {:rdf/type :activitystreams/Person, :activitystreams/name "Sally"},
                                          :activitystreams/object {:rdf/type :activitystreams/Person, :activitystreams/name "John"},
                                          :activitystreams/summary "Sally followed John"},
                      :activitystreams/name "Example 17"},
 :rdfs/comment
 "Indicates that the actor is \"following\" the object. Following is defined in the sense typically used within Social systems in which the actor is interested in any activity performed by or on the object. The target and origin typically have no defined meaning.",
 :rdfs/subClassOf {:rdf/type :activitystreams/Link,
                   :activitystreams/href "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-activity",
                   :activitystreams/name "Activity"},
 :owl/disjointWith [],
 :activitystreams/name "Follow",
 :activitystreams/url "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-follow"}

{"@id" "https://www.w3.org/TR/activitystreams-vocabulary/",
 "@type" {"@value" "http://www.w3.org/2002/07/owl#Ontology"},
 "http://www.w3.org/2002/07/owl#imports"
 {"@container" "@index",
  "@value"
  {"activityTypes"
   {"@type" {"@value" "http://www.w3.org/2002/07/owl#Ontology"},
    "http://www.w3.org/2002/07/owl#members"
    {"@value"
     [{"id" "as:Accept",
       "type" "owl:Class",
       "example"
       [{"id" "https://www.w3.org/TR/activitystreams-vocabulary/#ex7a-jsonld",
         "type" "https://schema.org/CreativeWork",
         "mainEntity"
         {"type" "Accept",
          "actor" {"type" "Person", "name" "Sally"},
          "object"
          {"type" "Invite",
           "actor" "http://john.example.org",
           "object" {"type" "Event", "name" "Going-Away Party for Jim"}},
          "summary" "Sally accepted an invitation to a party"},
         "name" "Example 9"}
        {"id" "https://www.w3.org/TR/activitystreams-vocabulary/#ex7b-jsonld",
         "type" "https://schema.org/CreativeWork",
         "mainEntity"
         {"type" "Accept",
          "actor" {"type" "Person", "name" "Sally"},
          "object" {"type" "Person", "name" "Joe"},
          "summary" "Sally accepted Joe into the club",
          "target" {"type" "Group", "name" "The Club"}},
         "name" "Example 10"}],
       "notes"
       "Indicates that the actor accepts the object. The target property can be used in certain circumstances to indicate the context into which the object has been accepted.",
       "subClassOf"
       {"type" "Link",
        "href"
        "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-activity",
        "name" "Activity"},
       "disjointWith" [],
       "name" "Accept",
       "url" "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-accept"}
      {"id" "as:TentativeAccept",
       "type" "owl:Class",
       "example"
       {"id" "https://www.w3.org/TR/activitystreams-vocabulary/#ex8-jsonld",
        "type" "https://schema.org/CreativeWork",
        "mainEntity"
        {"type" "TentativeAccept",
         "actor" {"type" "Person", "name" "Sally"},
         "object"
         {"type" "Invite",
          "actor" "http://john.example.org",
          "object" {"type" "Event", "name" "Going-Away Party for Jim"}},
         "summary" "Sally tentatively accepted an invitation to a party"},
        "name" "Example 11"},
       "notes"
       "A specialization of Accept indicating that the acceptance is tentative.",
       "subClassOf"
       {"type" "Link",
        "href" "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-accept",
        "name" "Accept"},
       "disjointWith" [],
       "name" "TentativeAccept",
       "url"
       "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-tentativeaccept"}
      {"id" "as:Add",
       "type" "owl:Class",
       "example"
       [{"id" "https://www.w3.org/TR/activitystreams-vocabulary/#ex9-jsonld",
         "type" "https://schema.org/CreativeWork",
         "mainEntity"
         {"type" "Add",
          "actor" {"type" "Person", "name" "Sally"},
          "object" "http://example.org/abc",
          "summary" "Sally added an object"},
         "name" "Example 12"}
        {"id" "https://www.w3.org/TR/activitystreams-vocabulary/#ex10-jsonld",
         "type" "https://schema.org/CreativeWork",
         "mainEntity"
         {"type" "Add",
          "actor" {"type" "Person", "name" "Sally"},
          "object"
          {"type" "Image",
           "name" "A picture of my cat",
           "url" "http://example.org/img/cat.png"},
          "origin" {"type" "Collection", "name" "Camera Roll"},
          "summary"
          "Sally added a picture of her cat to her cat picture collection",
          "target" {"type" "Collection", "name" "My Cat Pictures"}},
         "name" "Example 13"}],
       "notes"
       "Indicates that the actor has added the object to the target. If the target property is not explicitly specified, the target would need to be determined implicitly by context. The origin can be used to identify the context from which the object originated.",
       "subClassOf"
       {"type" "Link",
        "href"
        "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-activity",
        "name" "Activity"},
       "disjointWith" [],
       "name" "Add",
       "url" "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-add"}
      {"id" "as:Arrive",
       "type" "owl:Class",
       "example"
       {"id" "https://www.w3.org/TR/activitystreams-vocabulary/#ex11-jsonld",
        "type" "https://schema.org/CreativeWork",
        "mainEntity"
        {"type" "Arrive",
         "actor" {"type" "Person", "name" "Sally"},
         "location" {"type" "Place", "name" "Work"},
         "origin" {"type" "Place", "name" "Home"},
         "summary" "Sally arrived at work"},
        "name" "Example 14"},
       "notes"
       "An IntransitiveActivity that indicates that the actor has arrived at the location. The origin can be used to identify the context from which the actor originated. The target typically has no defined meaning.",
       "subClassOf"
       {"type" "Link",
        "href"
        "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-intransitiveactivity",
        "name" "IntransitiveActivity"},
       "disjointWith" [],
       "name" "Arrive",
       "url" "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-arrive"}
      {"id" "as:Create",
       "type" "owl:Class",
       "example"
       {"id" "https://www.w3.org/TR/activitystreams-vocabulary/#ex12-jsonld",
        "type" "https://schema.org/CreativeWork",
        "mainEntity"
        {"type" "Create",
         "actor" {"type" "Person", "name" "Sally"},
         "object"
         {"type" "Note",
          "content" "This is a simple note",
          "name" "A Simple Note"},
         "summary" "Sally created a note"},
        "name" "Example 15"},
       "notes" "Indicates that the actor has created the object.",
       "subClassOf"
       {"type" "Link",
        "href"
        "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-activity",
        "name" "Activity"},
       "disjointWith" [],
       "name" "Create",
       "url" "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-create"}
      {"id" "as:Delete",
       "type" "owl:Class",
       "example"
       {"id" "https://www.w3.org/TR/activitystreams-vocabulary/#ex13-jsonld",
        "type" "https://schema.org/CreativeWork",
        "mainEntity"
        {"type" "Delete",
         "actor" {"type" "Person", "name" "Sally"},
         "object" "http://example.org/notes/1",
         "origin" {"type" "Collection", "name" "Sally's Notes"},
         "summary" "Sally deleted a note"},
        "name" "Example 16"},
       "notes"
       "Indicates that the actor has deleted the object. If specified, the origin indicates the context from which the object was deleted.",
       "subClassOf"
       {"type" "Link",
        "href"
        "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-activity",
        "name" "Activity"},
       "disjointWith" [],
       "name" "Delete",
       "url" "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-delete"}
      {"id" "as:Follow",
       "type" "owl:Class",
       "example"
       {"id" "https://www.w3.org/TR/activitystreams-vocabulary/#ex15-jsonld",
        "type" "https://schema.org/CreativeWork",
        "mainEntity"
        {"type" "Follow",
         "actor" {"type" "Person", "name" "Sally"},
         "object" {"type" "Person", "name" "John"},
         "summary" "Sally followed John"},
        "name" "Example 17"},
       "notes"
       "Indicates that the actor is \"following\" the object. Following is defined in the sense typically used within Social systems in which the actor is interested in any activity performed by or on the object. The target and origin typically have no defined meaning.",
       "subClassOf"
       {"type" "Link",
        "href"
        "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-activity",
        "name" "Activity"},
       "disjointWith" [],
       "name" "Follow",
       "url" "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-follow"}
      {"id" "as:Ignore",
       "type" "owl:Class",
       "example"
       {"id" "https://www.w3.org/TR/activitystreams-vocabulary/#ex16-jsonld",
        "type" "https://schema.org/CreativeWork",
        "mainEntity"
        {"type" "Ignore",
         "actor" {"type" "Person", "name" "Sally"},
         "object" "http://example.org/notes/1",
         "summary" "Sally ignored a note"},
        "name" "Example 18"},
       "notes"
       "Indicates that the actor is ignoring the object. The target and origin typically have no defined meaning.",
       "subClassOf"
       {"type" "Link",
        "href"
        "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-activity",
        "name" "Activity"},
       "disjointWith" [],
       "name" "Ignore",
       "url" "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-ignore"}
      {"id" "as:Join",
       "type" "owl:Class",
       "example"
       {"id" "https://www.w3.org/TR/activitystreams-vocabulary/#ex17-jsonld",
        "type" "https://schema.org/CreativeWork",
        "mainEntity"
        {"type" "Join",
         "actor" {"type" "Person", "name" "Sally"},
         "object" {"type" "Group", "name" "A Simple Group"},
         "summary" "Sally joined a group"},
        "name" "Example 19"},
       "notes"
       "Indicates that the actor has joined the object. The target and origin typically have no defined meaning.",
       "subClassOf"
       {"type" "Link",
        "href"
        "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-activity",
        "name" "Activity"},
       "disjointWith" [],
       "name" "Join",
       "url" "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-join"}
      {"id" "as:Leave",
       "type" "owl:Class",
       "example"
       [{"id" "https://www.w3.org/TR/activitystreams-vocabulary/#ex18-jsonld",
         "type" "https://schema.org/CreativeWork",
         "mainEntity"
         {"type" "Leave",
          "actor" {"type" "Person", "name" "Sally"},
          "object" {"type" "Place", "name" "Work"},
          "summary" "Sally left work"},
         "name" "Example 20"}
        {"id" "https://www.w3.org/TR/activitystreams-vocabulary/#ex19-jsonld",
         "type" "https://schema.org/CreativeWork",
         "mainEntity"
         {"type" "Leave",
          "actor" {"type" "Person", "name" "Sally"},
          "object" {"type" "Group", "name" "A Simple Group"},
          "summary" "Sally left a group"},
         "name" "Example 21"}],
       "notes"
       "Indicates that the actor has left the object. The target and origin typically have no meaning.",
       "subClassOf"
       {"type" "Link",
        "href"
        "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-activity",
        "name" "Activity"},
       "disjointWith" [],
       "name" "Leave",
       "url" "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-leave"}
      {"id" "as:Like",
       "type" "owl:Class",
       "example"
       {"id" "https://www.w3.org/TR/activitystreams-vocabulary/#ex20-jsonld",
        "type" "https://schema.org/CreativeWork",
        "mainEntity"
        {"type" "Like",
         "actor" {"type" "Person", "name" "Sally"},
         "object" "http://example.org/notes/1",
         "summary" "Sally liked a note"},
        "name" "Example 22"},
       "notes"
       "Indicates that the actor likes, recommends or endorses the object. The target and origin typically have no defined meaning.",
       "subClassOf"
       {"type" "Link",
        "href"
        "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-activity",
        "name" "Activity"},
       "disjointWith" [],
       "name" "Like",
       "url" "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-like"}
      {"id" "as:Offer",
       "type" "owl:Class",
       "example"
       {"id" "https://www.w3.org/TR/activitystreams-vocabulary/#ex21-jsonld",
        "type" "https://schema.org/CreativeWork",
        "mainEntity"
        {"type" "Offer",
         "actor" {"type" "Person", "name" "Sally"},
         "object"
         {"type" "http://www.types.example/ProductOffer", "name" "50% Off!"},
         "summary" "Sally offered 50% off to Lewis",
         "target" {"type" "Person", "name" "Lewis"}},
        "name" "Example 23"},
       "notes"
       "Indicates that the actor is offering the object. If specified, the target indicates the entity to which the object is being offered.",
       "subClassOf"
       {"type" "Link",
        "href"
        "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-activity",
        "name" "Activity"},
       "disjointWith" [],
       "name" "Offer",
       "url" "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-offer"}
      {"id" "as:Invite",
       "type" "owl:Class",
       "example"
       {"id" "https://www.w3.org/TR/activitystreams-vocabulary/#ex24-jsonld",
        "type" "https://schema.org/CreativeWork",
        "mainEntity"
        {"type" "Invite",
         "actor" {"type" "Person", "name" "Sally"},
         "object" {"type" "Event", "name" "A Party"},
         "summary" "Sally invited John and Lisa to a party",
         "target"
         [{"type" "Person", "name" "John"} {"type" "Person", "name" "Lisa"}]},
        "name" "Example 24"},
       "notes"
       "A specialization of Offer in which the actor is extending an invitation for the object to the target.",
       "subClassOf"
       {"type" "Link",
        "href" "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-offer",
        "name" "Offer"},
       "disjointWith" [],
       "name" "Invite",
       "url" "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-invite"}
      {"id" "as:Reject",
       "type" "owl:Class",
       "example"
       {"id" "https://www.w3.org/TR/activitystreams-vocabulary/#ex26-jsonld",
        "type" "https://schema.org/CreativeWork",
        "mainEntity"
        {"type" "Reject",
         "actor" {"type" "Person", "name" "Sally"},
         "object"
         {"type" "Invite",
          "actor" "http://john.example.org",
          "object" {"type" "Event", "name" "Going-Away Party for Jim"}},
         "summary" "Sally rejected an invitation to a party"},
        "name" "Example 25"},
       "notes"
       "Indicates that the actor is rejecting the object. The target and origin typically have no defined meaning.",
       "subClassOf"
       {"type" "Link",
        "href"
        "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-activity",
        "name" "Activity"},
       "disjointWith" [],
       "name" "Reject",
       "url" "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-reject"}
      {"id" "as:TentativeReject",
       "type" "owl:Class",
       "example"
       {"id" "https://www.w3.org/TR/activitystreams-vocabulary/#ex27-jsonld",
        "type" "https://schema.org/CreativeWork",
        "mainEntity"
        {"type" "TentativeReject",
         "actor" {"type" "Person", "name" "Sally"},
         "object"
         {"type" "Invite",
          "actor" "http://john.example.org",
          "object" {"type" "Event", "name" "Going-Away Party for Jim"}},
         "summary" "Sally tentatively rejected an invitation to a party"},
        "name" "Example 26"},
       "notes"
       "A specialization of Reject in which the rejection is considered tentative.",
       "subClassOf"
       {"type" "Link",
        "href" "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-reject",
        "name" "Reject"},
       "disjointWith" [],
       "name" "TentativeReject",
       "url"
       "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-tentativereject"}
      {"id" "as:Remove",
       "type" "owl:Class",
       "example"
       [{"id" "https://www.w3.org/TR/activitystreams-vocabulary/#ex28-jsonld",
         "type" "https://schema.org/CreativeWork",
         "mainEntity"
         {"type" "Remove",
          "actor" {"type" "Person", "name" "Sally"},
          "object" "http://example.org/notes/1",
          "summary" "Sally removed a note from her notes folder",
          "target" {"type" "Collection", "name" "Notes Folder"}},
         "name" "Example 27"}
        {"id" "https://www.w3.org/TR/activitystreams-vocabulary/#ex29-jsonld",
         "type" "https://schema.org/CreativeWork",
         "mainEntity"
         {"type" "Remove",
          "actor" {"type" "http://example.org/Role", "name" "The Moderator"},
          "object" {"type" "Person", "name" "Sally"},
          "origin" {"type" "Group", "name" "A Simple Group"},
          "summary" "The moderator removed Sally from a group"},
         "name" "Example 28"}],
       "notes"
       "Indicates that the actor is removing the object. If specified, the origin indicates the context from which the object is being removed.",
       "subClassOf"
       {"type" "Link",
        "href"
        "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-activity",
        "name" "Activity"},
       "disjointWith" [],
       "name" "Remove",
       "url" "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-remove"}
      {"id" "as:Undo",
       "type" "owl:Class",
       "example"
       {"id" "https://www.w3.org/TR/activitystreams-vocabulary/#ex32-jsonld",
        "type" "https://schema.org/CreativeWork",
        "mainEntity"
        {"type" "Undo",
         "actor" "http://sally.example.org",
         "object"
         {"type" "Offer",
          "actor" "http://sally.example.org",
          "object" "http://example.org/posts/1",
          "target" "http://john.example.org"},
         "summary" "Sally retracted her offer to John"},
        "name" "Example 29"},
       "notes"
       "Indicates that the actor is undoing the object. In most cases, the object will be an Activity describing some previously performed action (for instance, a person may have previously \"liked\" an article but, for whatever reason, might choose to undo that like at some later point in time). The target and origin typically have no defined meaning.",
       "subClassOf"
       {"type" "Link",
        "href"
        "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-activity",
        "name" "Activity"},
       "disjointWith" [],
       "name" "Undo",
       "url" "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-undo"}
      {"id" "as:Update",
       "type" "owl:Class",
       "example"
       {"id" "https://www.w3.org/TR/activitystreams-vocabulary/#ex33-jsonld",
        "type" "https://schema.org/CreativeWork",
        "mainEntity"
        {"type" "Update",
         "actor" {"type" "Person", "name" "Sally"},
         "object" "http://example.org/notes/1",
         "summary" "Sally updated her note"},
        "name" "Example 30"},
       "notes"
       "Indicates that the actor has updated the object. Note, however, that this vocabulary does not define a mechanism for describing the actual set of modifications made to object. The target and origin typically have no defined meaning.",
       "subClassOf"
       {"type" "Link",
        "href"
        "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-activity",
        "name" "Activity"},
       "disjointWith" [],
       "name" "Update",
       "url" "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-update"}
      {"id" "as:View",
       "type" "owl:Class",
       "example"
       {"id" "https://www.w3.org/TR/activitystreams-vocabulary/#ex161-jsonld",
        "type" "https://schema.org/CreativeWork",
        "mainEntity"
        {"type" "View",
         "actor" {"type" "Person", "name" "Sally"},
         "object"
         {"type" "Article",
          "name" "What You Should Know About Activity Streams"},
         "summary" "Sally read an article"},
        "name" "Example 31"},
       "notes" "Indicates that the actor has viewed the object.",
       "subClassOf"
       {"type" "Link",
        "href"
        "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-activity",
        "name" "Activity"},
       "disjointWith" [],
       "name" "View",
       "url" "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-view"}
      {"id" "as:Listen",
       "type" "owl:Class",
       "example"
       {"id" "https://www.w3.org/TR/activitystreams-vocabulary/#ex163-jsonld",
        "type" "https://schema.org/CreativeWork",
        "mainEntity"
        {"type" "Listen",
         "actor" {"type" "Person", "name" "Sally"},
         "object" "http://example.org/music.mp3",
         "summary" "Sally listened to a piece of music"},
        "name" "Example 32"},
       "notes" "Indicates that the actor has listened to the object.",
       "subClassOf"
       {"type" "Link",
        "href"
        "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-activity",
        "name" "Activity"},
       "disjointWith" [],
       "name" "Listen",
       "url" "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-listen"}
      {"id" "as:Read",
       "type" "owl:Class",
       "example"
       {"id" "https://www.w3.org/TR/activitystreams-vocabulary/#ex164-jsonld",
        "type" "https://schema.org/CreativeWork",
        "mainEntity"
        {"type" "Read",
         "actor" {"type" "Person", "name" "Sally"},
         "object" "http://example.org/posts/1",
         "summary" "Sally read a blog post"},
        "name" "Example 33"},
       "notes" "Indicates that the actor has read the object.",
       "subClassOf"
       {"type" "Link",
        "href"
        "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-activity",
        "name" "Activity"},
       "disjointWith" [],
       "name" "Read",
       "url" "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-read"}
      {"id" "as:Move",
       "type" "owl:Class",
       "example"
       {"id" "https://www.w3.org/TR/activitystreams-vocabulary/#ex168-jsonld",
        "type" "https://schema.org/CreativeWork",
        "mainEntity"
        {"type" "Move",
         "actor" {"type" "Person", "name" "Sally"},
         "object" "http://example.org/posts/1",
         "origin" {"type" "Collection", "name" "List A"},
         "summary" "Sally moved a post from List A to List B",
         "target" {"type" "Collection", "name" "List B"}},
        "name" "Example 34"},
       "notes"
       "Indicates that the actor has moved object from origin to target. If the origin or target are not specified, either can be determined by context.",
       "subClassOf"
       {"type" "Link",
        "href"
        "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-activity",
        "name" "Activity"},
       "disjointWith" [],
       "name" "Move",
       "url" "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-move"}
      {"id" "as:Travel",
       "type" "owl:Class",
       "example"
       {"id" "https://www.w3.org/TR/activitystreams-vocabulary/#ex169-jsonld",
        "type" "https://schema.org/CreativeWork",
        "mainEntity"
        {"type" "Travel",
         "actor" {"type" "Person", "name" "Sally"},
         "origin" {"type" "Place", "name" "Work"},
         "summary" "Sally went home from work",
         "target" {"type" "Place", "name" "Home"}},
        "name" "Example 35"},
       "notes"
       "Indicates that the actor is traveling to target from origin. Travel is an IntransitiveObject whose actor specifies the direct object. If the target or origin are not specified, either can be determined by context.",
       "subClassOf"
       {"type" "Link",
        "href"
        "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-intransitiveactivity",
        "name" "IntransitiveActivity"},
       "disjointWith" [],
       "name" "Travel",
       "url" "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-travel"}
      {"id" "as:Announce",
       "type" "owl:Class",
       "example"
       {"id" "https://www.w3.org/TR/activitystreams-vocabulary/#ex170-jsonld",
        "type" "https://schema.org/CreativeWork",
        "mainEntity"
        {"type" "Announce",
         "actor"
         {"id" "http://sally.example.org", "type" "Person", "name" "Sally"},
         "object"
         {"type" "Arrive",
          "actor" "http://sally.example.org",
          "location" {"type" "Place", "name" "Work"}},
         "summary" "Sally announced that she had arrived at work"},
        "name" "Example 36"},
       "notes"
       "Indicates that the actor is calling the target's attention the object. The origin typically has no defined meaning.",
       "subClassOf"
       {"type" "Link",
        "href"
        "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-activity",
        "name" "Activity"},
       "disjointWith" [],
       "name" "Announce",
       "url" "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-announce"}
      {"id" "as:Block",
       "type" "owl:Class",
       "example"
       {"id" "https://www.w3.org/TR/activitystreams-vocabulary/#ex173-jsonld",
        "type" "https://schema.org/CreativeWork",
        "mainEntity"
        {"type" "Block",
         "actor" "http://sally.example.org",
         "object" "http://joe.example.org",
         "summary" "Sally blocked Joe"},
        "name" "Example 37"},
       "notes"
       "Indicates that the actor is blocking the object. Blocking is a stronger form of Ignore. The typical use is to support social systems that allow one user to block activities or content of other users. The target and origin typically have no defined meaning.",
       "subClassOf"
       {"type" "Link",
        "href" "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-ignore",
        "name" "Ignore"},
       "disjointWith" [],
       "name" "Block",
       "url" "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-block"}
      {"id" "as:Flag",
       "type" "owl:Class",
       "example"
       {"id" "https://www.w3.org/TR/activitystreams-vocabulary/#ex174-jsonld",
        "type" "https://schema.org/CreativeWork",
        "mainEntity"
        {"type" "Flag",
         "actor" "http://sally.example.org",
         "object" {"type" "Note", "content" "An inappropriate note"},
         "summary" "Sally flagged an inappropriate note"},
        "name" "Example 38"},
       "notes"
       "Indicates that the actor is \"flagging\" the object. Flagging is defined in the sense common to many social platforms as reporting content as being inappropriate for any number of reasons.",
       "subClassOf"
       {"type" "Link",
        "href"
        "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-activity",
        "name" "Activity"},
       "disjointWith" [],
       "name" "Flag",
       "url" "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-flag"}
      {"id" "as:Dislike",
       "type" "owl:Class",
       "example"
       {"id" "https://www.w3.org/TR/activitystreams-vocabulary/#ex175-jsonld",
        "type" "https://schema.org/CreativeWork",
        "mainEntity"
        {"type" "Dislike",
         "actor" "http://sally.example.org",
         "object" "http://example.org/posts/1",
         "summary" "Sally disliked a post"},
        "name" "Example 39"},
       "notes" "Indicates that the actor dislikes the object.",
       "subClassOf"
       {"type" "Link",
        "href"
        "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-activity",
        "name" "Activity"},
       "disjointWith" [],
       "name" "Dislike",
       "url" "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-dislike"}
      {"id" "as:Question",
       "type" "owl:Class",
       "example"
       [{"id" "https://www.w3.org/TR/activitystreams-vocabulary/#ex55a-jsonld",
         "type" "https://schema.org/CreativeWork",
         "mainEntity"
         {"type" "Question",
          "name" "What is the answer?",
          "oneOf"
          [{"type" "Note", "name" "Option A"}
           {"type" "Note", "name" "Option B"}]},
         "name" "Example 40"}
        {"id" "https://www.w3.org/TR/activitystreams-vocabulary/#ex55b-jsonld",
         "type" "https://schema.org/CreativeWork",
         "mainEntity"
         {"type" "Question",
          "closed" "2016-05-10T00:00:00Z",
          "name" "What is the answer?"},
         "name" "Example 41"}],
       "notes"
       "Represents a question being asked. Question objects are an extension of IntransitiveActivity. That is, the Question object is an Activity, but the direct object is the question itself and therefore it would not contain an object property. Either of the anyOf and oneOf properties MAY be used to express possible answers, but a Question object MUST NOT have both properties.",
       "subClassOf"
       {"type" "Link",
        "href"
        "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-intransitiveactivity",
        "name" "IntransitiveActivity"},
       "disjointWith" [],
       "name" "Question",
       "url"
       "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-question"}]}},
   "actorTypes"
   {"@type" {"@value" "http://www.w3.org/2002/07/owl#Ontology"},
    "http://www.w3.org/2002/07/owl#members"
    {"@value"
     [{"id" "as:Application",
       "type" "owl:Class",
       "example"
       {"id" "https://www.w3.org/TR/activitystreams-vocabulary/#ex34-jsonld",
        "type" "https://schema.org/CreativeWork",
        "mainEntity" {"type" "Application", "name" "Exampletron 3000"},
        "name" "Example 42"},
       "notes" "Describes a software application.",
       "subClassOf"
       {"type" "Link",
        "href" "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-object",
        "name" "Object"},
       "disjointWith" [],
       "name" "Application",
       "url"
       "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-application"}
      {"id" "as:Group",
       "type" "owl:Class",
       "example"
       {"id" "https://www.w3.org/TR/activitystreams-vocabulary/#ex37-jsonld",
        "type" "https://schema.org/CreativeWork",
        "mainEntity" {"type" "Group", "name" "Big Beards of Austin"},
        "name" "Example 43"},
       "notes" "Represents a formal or informal collective of Actors.",
       "subClassOf"
       {"type" "Link",
        "href" "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-object",
        "name" "Object"},
       "disjointWith" [],
       "name" "Group",
       "url" "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-group"}
      {"id" "as:Organization",
       "type" "owl:Class",
       "example"
       {"id" "https://www.w3.org/TR/activitystreams-vocabulary/#ex186-jsonld",
        "type" "https://schema.org/CreativeWork",
        "mainEntity" {"type" "Organization", "name" "Example Co."},
        "name" "Example 44"},
       "notes" "Represents an organization.",
       "subClassOf"
       {"type" "Link",
        "href" "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-object",
        "name" "Object"},
       "disjointWith" [],
       "name" "Organization",
       "url"
       "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-organization"}
      {"id" "as:Person",
       "type" "owl:Class",
       "example"
       {"id" "https://www.w3.org/TR/activitystreams-vocabulary/#ex39-jsonld",
        "type" "https://schema.org/CreativeWork",
        "mainEntity" {"type" "Person", "name" "Sally Smith"},
        "name" "Example 45"},
       "notes" "Represents an individual person.",
       "subClassOf"
       {"type" "Link",
        "href" "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-object",
        "name" "Object"},
       "disjointWith" [],
       "name" "Person",
       "url" "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-person"}
      {"id" "as:Service",
       "type" "owl:Class",
       "example"
       {"id" "https://www.w3.org/TR/activitystreams-vocabulary/#ex42-jsonld",
        "type" "https://schema.org/CreativeWork",
        "mainEntity" {"type" "Service", "name" "Acme Web Service"},
        "name" "Example 46"},
       "notes" "Represents a service of any kind.",
       "subClassOf"
       {"type" "Link",
        "href" "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-object",
        "name" "Object"},
       "disjointWith" [],
       "name" "Service",
       "url"
       "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-service"}]}},
   "coreTypes"
   {"@type" {"@value" "http://www.w3.org/2002/07/owl#Ontology"},
    "http://www.w3.org/2002/07/owl#members"
    {"@value"
     [{"id" "as:Object",
       "type" "owl:Class",
       "example"
       {"id" "https://www.w3.org/TR/activitystreams-vocabulary/#ex1-jsonld",
        "type" "https://schema.org/CreativeWork",
        "mainEntity"
        {"id" "http://www.test.example/object/1",
         "type" "Object",
         "name" "A Simple, non-specific object"},
        "name" "Example 1"},
       "notes"
       "Describes an object of any kind. The Object type serves as the base type for most of the other kinds of objects defined in the Activity Vocabulary, including other Core types such as Activity, IntransitiveActivity, Collection and OrderedCollection.",
       "subClassOf"
       {"type" "Link",
        "href" "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-link",
        "name" "Link"},
       "disjointWith"
       {"type" "Link",
        "href" "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-link",
        "name" "Link"},
       "name" "Object",
       "url" "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-object"}
      {"id" "as:Link",
       "type" "owl:Class",
       "example"
       {"id" "https://www.w3.org/TR/activitystreams-vocabulary/#ex2-jsonld",
        "type" "https://schema.org/CreativeWork",
        "mainEntity"
        {"type" "Link",
         "href" "http://example.org/abc",
         "hreflang" "en",
         "mediaType" "text/html",
         "name" "An example link"},
        "name" "Example 2"},
       "notes"
       "A Link is an indirect, qualified reference to a resource identified by a URL. The fundamental model for links is established by [ RFC5988]. Many of the properties defined by the Activity Vocabulary allow values that are either instances of Object or Link. When a Link is used, it establishes a qualified relation connecting the subject (the containing object) to the resource identified by the href. Properties of the Link are properties of the reference as opposed to properties of the resource.",
       "subClassOf"
       {"type" "Link",
        "href" "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-object",
        "name" "Object"},
       "disjointWith"
       {"type" "Link",
        "href" "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-object",
        "name" "Object"},
       "name" "Link",
       "url" "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-link"}
      {"id" "as:Activity",
       "type" "owl:Class",
       "example"
       {"id" "https://www.w3.org/TR/activitystreams-vocabulary/#ex3-jsonld",
        "type" "https://schema.org/CreativeWork",
        "mainEntity"
        {"type" "Activity",
         "actor" {"type" "Person", "name" "Sally"},
         "object" {"type" "Note", "name" "A Note"},
         "summary" "Sally did something to a note"},
        "name" "Example 3"},
       "notes"
       "An Activity is a subtype of Object that describes some form of action that may happen, is currently happening, or has already happened. The Activity type itself serves as an abstract base type for all types of activities. It is important to note that the Activity type itself does not carry any specific semantics about the kind of action being taken.",
       "subClassOf"
       {"type" "Link",
        "href" "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-object",
        "name" "Object"},
       "disjointWith" [],
       "name" "Activity",
       "url" "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-activity"}
      {"id" "as:IntransitiveActivity",
       "type" "owl:Class",
       "example"
       {"id" "https://www.w3.org/TR/activitystreams-vocabulary/#ex182-jsonld",
        "type" "https://schema.org/CreativeWork",
        "mainEntity"
        {"type" "Travel",
         "actor" {"type" "Person", "name" "Sally"},
         "summary" "Sally went to work",
         "target" {"type" "Place", "name" "Work"}},
        "name" "Example 4"},
       "notes"
       "Instances of IntransitiveActivity are a subtype of Activity representing intransitive actions. The object property is therefore inappropriate for these activities.",
       "subClassOf"
       {"type" "Link",
        "href"
        "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-activity",
        "name" "Activity"},
       "disjointWith" [],
       "name" "IntransitiveActivity",
       "url"
       "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-intransitiveactivity"}
      {"id" "as:Collection",
       "type" "owl:Class",
       "example"
       {"id" "https://www.w3.org/TR/activitystreams-vocabulary/#ex5-jsonld",
        "type" "https://schema.org/CreativeWork",
        "mainEntity"
        {"type" "Collection",
         "items"
         [{"type" "Note", "name" "A Simple Note"}
          {"type" "Note", "name" "Another Simple Note"}],
         "summary" "Sally's notes",
         "totalItems" 2},
        "name" "Example 5"},
       "notes"
       "A Collection is a subtype of Object that represents ordered or unordered sets of Object or Link instances. Refer to the Activity Streams 2.0 Core specification for a complete description of the Collection type.",
       "subClassOf"
       {"type" "Link",
        "href" "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-object",
        "name" "Object"},
       "disjointWith" [],
       "name" "Collection",
       "url"
       "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-collection"}
      {"id" "as:OrderedCollection",
       "type" "owl:Class",
       "example"
       {"id" "https://www.w3.org/TR/activitystreams-vocabulary/#ex6-jsonld",
        "type" "https://schema.org/CreativeWork",
        "mainEntity"
        {"type" "OrderedCollection",
         "orderedItems"
         [{"type" "Note", "name" "A Simple Note"}
          {"type" "Note", "name" "Another Simple Note"}],
         "summary" "Sally's notes",
         "totalItems" 2},
        "name" "Example 6"},
       "notes"
       "A subtype of Collection in which members of the logical collection are assumed to always be strictly ordered.",
       "subClassOf"
       {"type" "Link",
        "href"
        "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-collection",
        "name" "Collection"},
       "disjointWith" [],
       "name" "OrderedCollection",
       "url"
       "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-orderedcollection"}
      {"id" "as:CollectionPage",
       "type" "owl:Class",
       "example"
       {"id" "https://www.w3.org/TR/activitystreams-vocabulary/#ex6b-jsonld",
        "type" "https://schema.org/CreativeWork",
        "mainEntity"
        {"id" "http://example.org/foo?page=1",
         "type" "CollectionPage",
         "items"
         [{"type" "Note", "name" "A Simple Note"}
          {"type" "Note", "name" "Another Simple Note"}],
         "partOf" "http://example.org/foo",
         "summary" "Page 1 of Sally's notes"},
        "name" "Example 7"},
       "notes"
       "Used to represent distinct subsets of items from a Collection. Refer to the Activity Streams 2.0 Core for a complete description of the CollectionPage object.",
       "subClassOf"
       {"type" "Link",
        "href"
        "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-collection",
        "name" "Collection"},
       "disjointWith" [],
       "name" "CollectionPage",
       "url"
       "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-collectionpage"}
      {"id" "as:OrderedCollectionPage",
       "type" "owl:Class",
       "example"
       {"id" "https://www.w3.org/TR/activitystreams-vocabulary/#ex6c-jsonld",
        "type" "https://schema.org/CreativeWork",
        "mainEntity"
        {"id" "http://example.org/foo?page=1",
         "type" "OrderedCollectionPage",
         "orderedItems"
         [{"type" "Note", "name" "A Simple Note"}
          {"type" "Note", "name" "Another Simple Note"}],
         "partOf" "http://example.org/foo",
         "summary" "Page 1 of Sally's notes"},
        "name" "Example 8"},
       "notes"
       "Used to represent ordered subsets of items from an OrderedCollection. Refer to the Activity Streams 2.0 Core for a complete description of the OrderedCollectionPage object.",
       "subClassOf"
       {"type" "Link",
        "href"
        "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-orderedcollection",
        "name" "OrderedCollectionCollectionPage"},
       "disjointWith" [],
       "name" "OrderedCollectionPage",
       "url"
       "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-orderedcollectionpage"}]}},
   "objectTypes"
   {"@type" {"@value" "http://www.w3.org/2002/07/owl#Ontology"},
    "http://www.w3.org/2002/07/owl#members"
    {"@value"
     [{"id" "as:Relationship",
       "type" "owl:Class",
       "example"
       {"id" "https://www.w3.org/TR/activitystreams-vocabulary/#ex22-jsonld",
        "type" "https://schema.org/CreativeWork",
        "mainEntity"
        {"type" "Relationship",
         "object" {"type" "Person", "name" "John"},
         "relationship" "http://purl.org/vocab/relationship/acquaintanceOf",
         "subject" {"type" "Person", "name" "Sally"},
         "summary" "Sally is an acquaintance of John"},
        "name" "Example 47"},
       "notes"
       "Describes a relationship between two individuals. The subject and object properties are used to identify the connected individuals. See 5.2 Representing Relationships Between Entities for additional information.",
       "subClassOf"
       {"type" "Link",
        "href" "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-object",
        "name" "Object"},
       "disjointWith" [],
       "name" "Relationship",
       "url"
       "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-relationship"}
      {"id" "as:Article",
       "type" "owl:Class",
       "example"
       {"id" "https://www.w3.org/TR/activitystreams-vocabulary/#ex43-jsonld",
        "type" "https://schema.org/CreativeWork",
        "mainEntity"
        {"type" "Article",
         "attributedTo" "http://sally.example.org",
         "content" "<div>... you will never believe ...</div>",
         "name" "What a Crazy Day I Had"},
        "name" "Example 48"},
       "notes" "Represents any kind of multi-paragraph written work.",
       "subClassOf"
       {"type" "Link",
        "href" "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-object",
        "name" "Object"},
       "disjointWith" [],
       "name" "Article",
       "url" "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-article"}
      {"id" "as:Document",
       "type" "owl:Class",
       "example"
       {"id" "https://www.w3.org/TR/activitystreams-vocabulary/#ex48-jsonld",
        "type" "https://schema.org/CreativeWork",
        "mainEntity"
        {"type" "Document",
         "name" "4Q Sales Forecast",
         "url" "http://example.org/4q-sales-forecast.pdf"},
        "name" "Example 49"},
       "notes" "Represents a document of any kind.",
       "subClassOf"
       {"type" "Link",
        "href" "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-object",
        "name" "Object"},
       "disjointWith" [],
       "name" "Document",
       "url" "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-document"}
      {"id" "as:Audio",
       "type" "owl:Class",
       "example"
       {"id" "https://www.w3.org/TR/activitystreams-vocabulary/#ex49-jsonld",
        "type" "https://schema.org/CreativeWork",
        "mainEntity"
        {"type" "Audio",
         "name" "Interview With A Famous Technologist",
         "url"
         {"type" "Link",
          "href" "http://example.org/podcast.mp3",
          "mediaType" "audio/mp3"}},
        "name" "Example 50"},
       "notes" "Represents an audio document of any kind.",
       "subClassOf"
       {"type" "Link",
        "href"
        "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-document",
        "name" "Document"},
       "disjointWith" [],
       "name" "Audio",
       "url" "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-audio"}
      {"id" "as:Image",
       "type" "owl:Class",
       "example"
       {"id" "https://www.w3.org/TR/activitystreams-vocabulary/#ex50-jsonld",
        "type" "https://schema.org/CreativeWork",
        "mainEntity"
        {"type" "Image",
         "name" "Cat Jumping on Wagon",
         "url"
         [{"type" "Link",
           "href" "http://example.org/image.jpeg",
           "mediaType" "image/jpeg"}
          {"type" "Link",
           "href" "http://example.org/image.png",
           "mediaType" "image/png"}]},
        "name" "Example 51"},
       "notes" "An image document of any kind",
       "subClassOf"
       {"type" "Link",
        "href"
        "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-document",
        "name" "Document"},
       "disjointWith" [],
       "name" "Image",
       "url" "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-image"}
      {"id" "as:Video",
       "type" "owl:Class",
       "example"
       {"id" "https://www.w3.org/TR/activitystreams-vocabulary/#ex51-jsonld",
        "type" "https://schema.org/CreativeWork",
        "mainEntity"
        {"type" "Video",
         "duration" "PT2H",
         "name" "Puppy Plays With Ball",
         "url" "http://example.org/video.mkv"},
        "name" "Example 52"},
       "notes" "Represents a video document of any kind.",
       "subClassOf"
       {"type" "Link",
        "href"
        "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-document",
        "name" "Document"},
       "disjointWith" [],
       "name" "Video",
       "url" "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-video"}
      {"id" "as:Note",
       "type" "owl:Class",
       "example"
       {"id" "https://www.w3.org/TR/activitystreams-vocabulary/#ex52-jsonld",
        "type" "https://schema.org/CreativeWork",
        "mainEntity"
        {"type" "Note",
         "content" "Looks like it is going to rain today. Bring an umbrella!",
         "name" "A Word of Warning"},
        "name" "Example 53"},
       "notes"
       "Represents a short written work typically less than a single paragraph in length.",
       "subClassOf"
       {"type" "Link",
        "href" "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-object",
        "name" "Object"},
       "disjointWith" [],
       "name" "Note",
       "url" "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-note"}
      {"id" "as:Page",
       "type" "owl:Class",
       "example"
       {"id" "https://www.w3.org/TR/activitystreams-vocabulary/#ex53-jsonld",
        "type" "https://schema.org/CreativeWork",
        "mainEntity"
        {"type" "Page",
         "name" "Omaha Weather Report",
         "url" "http://example.org/weather-in-omaha.html"},
        "name" "Example 54"},
       "notes" "Represents a Web Page.",
       "subClassOf"
       {"type" "Link",
        "href"
        "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-document",
        "name" "Document"},
       "disjointWith" [],
       "name" "Page",
       "url" "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-page"}
      {"id" "as:Event",
       "type" "owl:Class",
       "example"
       {"id" "https://www.w3.org/TR/activitystreams-vocabulary/#ex56-jsonld",
        "type" "https://schema.org/CreativeWork",
        "mainEntity"
        {"type" "Event",
         "endTime" "2015-01-01T06:00:00-08:00",
         "name" "Going-Away Party for Jim",
         "startTime" "2014-12-31T23:00:00-08:00"},
        "name" "Example 55"},
       "notes" "Represents any kind of event.",
       "subClassOf"
       {"type" "Link",
        "href" "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-object",
        "name" "Object"},
       "disjointWith" [],
       "name" "Event",
       "url" "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-event"}
      {"id" "as:Place",
       "type" "owl:Class",
       "example"
       [{"id" "https://www.w3.org/TR/activitystreams-vocabulary/#ex57-jsonld",
         "type" "https://schema.org/CreativeWork",
         "mainEntity" {"type" "Place", "name" "Work"},
         "name" "Example 56"}
        {"id" "https://www.w3.org/TR/activitystreams-vocabulary/#ex58-jsonld",
         "type" "https://schema.org/CreativeWork",
         "mainEntity"
         {"type" "Place",
          "latitude" 36.75,
          "longitude" 119.7667,
          "name" "Fresno Area",
          "radius" 15,
          "units" "miles"},
         "name" "Example 57"}],
       "notes"
       "Represents a logical or physical location. See 5.3 Representing Places for additional information.",
       "subClassOf"
       {"type" "Link",
        "href" "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-object",
        "name" "Object"},
       "disjointWith" [],
       "name" "Place",
       "url" "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-place"}
      {"id" "as:Mention",
       "type" "owl:Class",
       "example"
       {"id" "https://www.w3.org/TR/activitystreams-vocabulary/#ex181-jsonld",
        "type" "https://schema.org/CreativeWork",
        "mainEntity"
        {"type" "Mention",
         "href" "http://example.org/joe",
         "name" "Joe",
         "summary" "Mention of Joe by Carrie in her note"},
        "name" "Example 58"},
       "notes" "A specialized Link that represents an @mention.",
       "subClassOf"
       {"type" "Link",
        "href" "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-link",
        "name" "Link"},
       "disjointWith" [],
       "name" "Mention",
       "url" "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-mention"}
      {"id" "as:Profile",
       "type" "owl:Class",
       "example"
       {"id" "https://www.w3.org/TR/activitystreams-vocabulary/#ex184a-jsonld",
        "type" "https://schema.org/CreativeWork",
        "mainEntity"
        {"type" "Profile",
         "describes" {"type" "Person", "name" "Sally Smith"},
         "summary" "Sally's Profile"},
        "name" "Example 59"},
       "notes"
       "A Profile is a content object that describes another Object, typically used to describe Actor Type objects. The describes property is used to reference the object being described by the profile.",
       "subClassOf"
       {"type" "Link",
        "href" "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-object",
        "name" "Object"},
       "disjointWith" [],
       "name" "Profile",
       "url" "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-profile"}
      {"id" "as:Tombstone",
       "type" "owl:Class",
       "example"
       {"id" "https://www.w3.org/TR/activitystreams-vocabulary/#ex184b-jsonld",
        "type" "https://schema.org/CreativeWork",
        "mainEntity"
        {"type" "OrderedCollection",
         "orderedItems"
         [{"id" "http://image.example/1", "type" "Image"}
          {"id" "http://image.example/2",
           "type" "Tombstone",
           "deleted" "2016-03-17T00:00:00Z",
           "formerType" "/Image"}
          {"id" "http://image.example/3", "type" "Image"}],
         "name" "Vacation photos 2016",
         "totalItems" 3},
        "name" "Example 60"},
       "notes"
       "A Tombstone represents a content object that has been deleted. It can be used in Collections to signify that there used to be an object at this position, but it has been deleted.",
       "subClassOf"
       {"type" "Link",
        "href" "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-object",
        "name" "Object"},
       "disjointWith" [],
       "name" "Tombstone",
       "url"
       "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-tombstone"}]}},
   "properties"
   {"@type" {"@value" "http://www.w3.org/2002/07/owl#Ontology"},
    "http://www.w3.org/2002/07/owl#members"
    {"@value"
     [{"range"
       {"type" "owl:Class",
        "unionOf" "https://www.w3.org/TR/activitystreams-vocabulary/anyURI"},
       "example"
       {"id" "https://www.w3.org/TR/activitystreams-vocabulary/#exid-jsonld",
        "type" "https://schema.org/CreativeWork",
        "mainEntity" {"id" "http://example.org/foo", "name" "Foo"},
        "name" "Example 61"},
       "url" "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-id",
       "id" "https://www.w3.org/TR/activitystreams-vocabulary/@id",
       "name" "id",
       "domain"
       {"type" "owl:Class",
        "unionOf"
        [{"type" "Link",
          "href"
          "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-object",
          "name" "Object"}
         {"type" "Link",
          "href" "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-link",
          "name" "Link"}]},
       "isDefinedBy"
       "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-id",
       "type" ["rdf:Property" "owl:ObjectProperty" "owl:FunctionalProperty"],
       "notes"
       "Provides the globally unique identifier for an Object or Link."}
      {"range"
       {"type" "owl:Class",
        "unionOf" "https://www.w3.org/TR/activitystreams-vocabulary/anyURI"},
       "example"
       {"id" "https://www.w3.org/TR/activitystreams-vocabulary/#extype-jsonld",
        "type" "https://schema.org/CreativeWork",
        "mainEntity" {"type" "http://example.org/Foo", "summary" "A foo"},
        "name" "Example 62"},
       "url" "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-type",
       "id" "https://www.w3.org/TR/activitystreams-vocabulary/@type",
       "name" "type",
       "domain"
       {"type" "owl:Class",
        "unionOf"
        [{"type" "Link",
          "href"
          "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-object",
          "name" "Object"}
         {"type" "Link",
          "href" "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-link",
          "name" "Link"}]},
       "isDefinedBy"
       "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-type",
       "type" ["rdf:Property" "owl:ObjectProperty"],
       "notes"
       "Identifies the Object or Link type. Multiple values may be specified."}
      {"range"
       {"type" "owl:Class",
        "unionOf"
        [{"type" "Link",
          "href"
          "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-object",
          "name" "Object"}
         {"type" "Link",
          "href" "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-link",
          "name" "Link"}]},
       "example"
       [{"id" "https://www.w3.org/TR/activitystreams-vocabulary/#ex59-jsonld",
         "type" "https://schema.org/CreativeWork",
         "mainEntity"
         {"type" "Offer",
          "actor" "http://sally.example.org",
          "object" "http://example.org/foo",
          "summary" "Sally offered the Foo object"},
         "name" "Example 63"}
        {"id" "https://www.w3.org/TR/activitystreams-vocabulary/#ex60-jsonld",
         "type" "https://schema.org/CreativeWork",
         "mainEntity"
         {"type" "Offer",
          "actor"
          {"id" "http://sally.example.org",
           "type" "Person",
           "summary" "Sally"},
          "object" "http://example.org/foo",
          "summary" "Sally offered the Foo object"},
         "name" "Example 64"}
        {"id" "https://www.w3.org/TR/activitystreams-vocabulary/#ex61-jsonld",
         "type" "https://schema.org/CreativeWork",
         "mainEntity"
         {"type" "Offer",
          "actor"
          ["http://joe.example.org"
           {"id" "http://sally.example.org", "type" "Person", "name" "Sally"}],
          "object" "http://example.org/foo",
          "summary" "Sally and Joe offered the Foo object"},
         "name" "Example 65"}],
       "url" "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-actor",
       "id" "as:actor",
       "name" "actor",
       "domain"
       {"type" "owl:Class",
        "unionOf"
        {"type" "Link",
         "href"
         "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-activity",
         "name" "Activity"}},
       "isDefinedBy"
       "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-actor",
       "subPropertyOf"
       {"type" "Link",
        "href"
        "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-attributedto",
        "name" "attributedTo"},
       "type" "rdf:Property",
       "notes"
       "Describes one or more entities that either performed or are expected to perform the activity. Any single activity can have multiple actors. The actor MAY be specified using an indirect Link."}
      {"range"
       {"type" "owl:Class",
        "unionOf"
        [{"type" "Link",
          "href"
          "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-object",
          "name" "Object"}
         {"type" "Link",
          "href" "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-link",
          "name" "Link"}]},
       "example"
       {"id" "https://www.w3.org/TR/activitystreams-vocabulary/#ex64-jsonld",
        "type" "https://schema.org/CreativeWork",
        "mainEntity"
        {"type" "Note",
         "attachment"
         {"type" "Image",
          "content" "This is what he looks like.",
          "url" "http://example.org/cat.jpeg"},
         "name" "Have you seen my cat?"},
        "name" "Example 66"},
       "url"
       "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-attachment",
       "id" "as:attachment",
       "name" "attachment",
       "domain"
       {"type" "owl:Class",
        "unionOf"
        {"type" "Link",
         "href" "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-object",
         "name" "Object"}},
       "isDefinedBy"
       "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-attachment",
       "type" ["rdf:Property" "owl:ObjectProperty"],
       "notes"
       "Identifies a resource attached or related to an object that potentially requires special handling. The intent is to provide a model that is at least semantically similar to attachments in email."}
      {"range"
       {"type" "owl:Class",
        "unionOf"
        [{"type" "Link",
          "href" "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-link",
          "name" "Link"}
         {"type" "Link",
          "href"
          "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-object",
          "name" "Object"}]},
       "example"
       [{"id" "https://www.w3.org/TR/activitystreams-vocabulary/#ex65-jsonld",
         "type" "https://schema.org/CreativeWork",
         "mainEntity"
         {"type" "Image",
          "attributedTo" {"type" "Person", "name" "Sally"},
          "name" "My cat taking a nap",
          "url" "http://example.org/cat.jpeg"},
         "name" "Example 67"}
        {"id" "https://www.w3.org/TR/activitystreams-vocabulary/#ex66-jsonld",
         "type" "https://schema.org/CreativeWork",
         "mainEntity"
         {"type" "Image",
          "attributedTo"
          ["http://joe.example.org" {"type" "Person", "name" "Sally"}],
          "name" "My cat taking a nap",
          "url" "http://example.org/cat.jpeg"},
         "name" "Example 68"}],
       "url"
       "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-attributedto",
       "id" "as:attributedTo",
       "name" "attributedTo",
       "domain"
       {"type" "owl:Class",
        "unionOf"
        [{"type" "Link",
          "href" "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-link",
          "name" "Link"}
         {"type" "Link",
          "href"
          "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-object",
          "name" "Object"}]},
       "isDefinedBy"
       "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-attributedto",
       "type" ["rdf:Property" "owl:ObjectProperty"],
       "notes"
       "Identifies one or more entities to which this object is attributed. The attributed entities might not be Actors. For instance, an object might be attributed to the completion of another activity."}
      {"range"
       {"type" "owl:Class",
        "unionOf"
        [{"type" "Link",
          "href"
          "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-object",
          "name" "Object"}
         {"type" "Link",
          "href" "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-link",
          "name" "Link"}]},
       "example"
       {"id" "https://www.w3.org/TR/activitystreams-vocabulary/#ex113-jsonld",
        "type" "https://schema.org/CreativeWork",
        "mainEntity"
        {"type" "Note",
         "audience"
         {"type" "http://example.org/Organization", "name" "ExampleCo LLC"},
         "content"
         "Thursday will be a company-wide holiday. Enjoy your day off!",
         "name" "Holiday announcement"},
        "name" "Example 69"},
       "url" "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-audience",
       "id" "as:audience",
       "name" "audience",
       "domain"
       {"type" "owl:Class",
        "unionOf"
        {"type" "Link",
         "href" "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-object",
         "name" "Object"}},
       "isDefinedBy"
       "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-audience",
       "type" ["rdf:Property" "owl:ObjectProperty"],
       "notes"
       "Identifies one or more entities that represent the total population of entities for which the object can considered to be relevant."}
      {"range"
       {"type" "owl:Class",
        "unionOf"
        [{"type" "Link",
          "href"
          "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-object",
          "name" "Object"}
         {"type" "Link",
          "href" "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-link",
          "name" "Link"}]},
       "example"
       {"id" "https://www.w3.org/TR/activitystreams-vocabulary/#ex68-jsonld",
        "type" "https://schema.org/CreativeWork",
        "mainEntity"
        {"type" "Offer",
         "actor" "http://sally.example.org",
         "bcc" "http://joe.example.org",
         "object" "http://example.org/posts/1",
         "summary" "Sally offered a post to John",
         "target" "http://john.example.org"},
        "name" "Example 70"},
       "url" "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-bcc",
       "id" "as:bcc",
       "name" "bcc",
       "domain"
       {"type" "owl:Class",
        "unionOf"
        {"type" "Link",
         "href" "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-object",
         "name" "Object"}},
       "isDefinedBy"
       "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-bcc",
       "type" ["rdf:Property" "owl:ObjectProperty"],
       "notes"
       "Identifies one or more Objects that are part of the private secondary audience of this Object."}
      {"range"
       {"type" "owl:Class",
        "unionOf"
        [{"type" "Link",
          "href"
          "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-object",
          "name" "Object"}
         {"type" "Link",
          "href" "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-link",
          "name" "Link"}]},
       "example"
       {"id" "https://www.w3.org/TR/activitystreams-vocabulary/#ex69-jsonld",
        "type" "https://schema.org/CreativeWork",
        "mainEntity"
        {"type" "Offer",
         "actor" "http://sally.example.org",
         "bto" "http://joe.example.org",
         "object" "http://example.org/posts/1",
         "summary" "Sally offered a post to John",
         "target" "http://john.example.org"},
        "name" "Example 71"},
       "url" "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-bto",
       "id" "as:bto",
       "name" "bto",
       "domain"
       {"type" "owl:Class",
        "unionOf"
        {"type" "Link",
         "href" "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-object",
         "name" "Object"}},
       "isDefinedBy"
       "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-bto",
       "type" ["rdf:Property" "owl:ObjectProperty"],
       "notes"
       "Identifies an Object that is part of the private primary audience of this Object."}
      {"range"
       {"type" "owl:Class",
        "unionOf"
        [{"type" "Link",
          "href"
          "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-object",
          "name" "Object"}
         {"type" "Link",
          "href" "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-link",
          "name" "Link"}]},
       "example"
       {"id" "https://www.w3.org/TR/activitystreams-vocabulary/#ex70-jsonld",
        "type" "https://schema.org/CreativeWork",
        "mainEntity"
        {"type" "Offer",
         "actor" "http://sally.example.org",
         "cc" "http://joe.example.org",
         "object" "http://example.org/posts/1",
         "summary" "Sally offered a post to John",
         "target" "http://john.example.org"},
        "name" "Example 72"},
       "url" "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-cc",
       "id" "as:cc",
       "name" "cc",
       "domain"
       {"type" "owl:Class",
        "unionOf"
        {"type" "Link",
         "href" "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-object",
         "name" "Object"}},
       "isDefinedBy"
       "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-cc",
       "type" ["rdf:Property" "owl:ObjectProperty"],
       "notes"
       "Identifies an Object that is part of the public secondary audience of this Object."}
      {"range"
       {"type" "owl:Class",
        "unionOf"
        [{"type" "Link",
          "href"
          "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-object",
          "name" "Object"}
         {"type" "Link",
          "href" "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-link",
          "name" "Link"}]},
       "example"
       {"id" "https://www.w3.org/TR/activitystreams-vocabulary/#ex171-jsonld",
        "type" "https://schema.org/CreativeWork",
        "mainEntity"
        {"type" "Collection",
         "items"
         [{"type" "Offer",
           "actor" "http://sally.example.org",
           "context" "http://example.org/contexts/1",
           "object" "http://example.org/posts/1",
           "target" "http://john.example.org"}
          {"type" "Like",
           "actor" "http://joe.example.org",
           "context" "http://example.org/contexts/1",
           "object" "http://example.org/posts/2"}],
         "summary" "Activities in context 1"},
        "name" "Example 73"},
       "url" "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-context",
       "id" "as:context",
       "name" "context",
       "domain"
       {"type" "owl:Class",
        "unionOf"
        {"type" "Link",
         "href" "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-object",
         "name" "Object"}},
       "isDefinedBy"
       "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-context",
       "type" ["rdf:Property" "owl:ObjectProperty"],
       "notes"
       "Identifies the context within which the object exists or an activity was performed. The notion of \"context\" used is intentionally vague. The intended function is to serve as a means of grouping objects and activities that share a common originating context or purpose. An example could be all activities relating to a common project or event."}
      {"range"
       {"type" "owl:Class",
        "unionOf"
        [{"type" "Link",
          "href"
          "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-collectionpage",
          "name" "CollectionPage"}
         {"type" "Link",
          "href" "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-link",
          "name" "Link"}]},
       "example"
       [{"id" "https://www.w3.org/TR/activitystreams-vocabulary/#ex71-jsonld",
         "type" "https://schema.org/CreativeWork",
         "mainEntity"
         {"type" "Collection",
          "current" "http://example.org/collection",
          "items"
          ["http://example.org/posts/1"
           "http://example.org/posts/2"
           "http://example.org/posts/3"],
          "summary" "Sally's blog posts",
          "totalItems" 3},
         "name" "Example 74"}
        {"id" "https://www.w3.org/TR/activitystreams-vocabulary/#ex72-jsonld",
         "type" "https://schema.org/CreativeWork",
         "mainEntity"
         {"type" "Collection",
          "current"
          {"type" "Link",
           "href" "http://example.org/collection",
           "summary" "Most Recent Items"},
          "items"
          ["http://example.org/posts/1"
           "http://example.org/posts/2"
           "http://example.org/posts/3"],
          "summary" "Sally's blog posts",
          "totalItems" 3},
         "name" "Example 75"}],
       "url" "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-current",
       "id" "as:current",
       "name" "current",
       "domain"
       {"type" "owl:Class",
        "unionOf"
        {"type" "Link",
         "href"
         "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-collection",
         "name" "Collection"}},
       "isDefinedBy"
       "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-current",
       "type" ["rdf:Property" "owl:FunctionalProperty"],
       "notes"
       "In a paged Collection, indicates the page that contains the most recently updated member items."}
      {"range"
       {"type" "owl:Class",
        "unionOf"
        [{"type" "Link",
          "href"
          "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-collectionpage",
          "name" "CollectionPage"}
         {"type" "Link",
          "href" "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-link",
          "name" "Link"}]},
       "example"
       [{"id" "https://www.w3.org/TR/activitystreams-vocabulary/#ex73-jsonld",
         "type" "https://schema.org/CreativeWork",
         "mainEntity"
         {"type" "Collection",
          "first" "http://example.org/collection?page=0",
          "summary" "Sally's blog posts",
          "totalItems" 3},
         "name" "Example 76"}
        {"id" "https://www.w3.org/TR/activitystreams-vocabulary/#ex74-jsonld",
         "type" "https://schema.org/CreativeWork",
         "mainEntity"
         {"type" "Collection",
          "first"
          {"type" "Link",
           "href" "http://example.org/collection?page=0",
           "summary" "First Page"},
          "summary" "Sally's blog posts",
          "totalItems" 3},
         "name" "Example 77"}],
       "url" "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-first",
       "id" "as:first",
       "name" "first",
       "domain"
       {"type" "owl:Class",
        "unionOf"
        {"type" "Link",
         "href"
         "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-collection",
         "name" "Collection"}},
       "isDefinedBy"
       "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-first",
       "type" ["rdf:Property" "owl:FunctionalProperty"],
       "notes"
       "In a paged Collection, indicates the furthest preceeding page of items in the collection."}
      {"range"
       {"type" "owl:Class",
        "unionOf"
        [{"type" "Link",
          "href"
          "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-object",
          "name" "Object"}
         {"type" "Link",
          "href" "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-link",
          "name" "Link"}]},
       "example"
       {"id" "https://www.w3.org/TR/activitystreams-vocabulary/#ex75-jsonld",
        "type" "https://schema.org/CreativeWork",
        "mainEntity"
        {"type" "Note",
         "content" "This is all there is.",
         "generator" {"type" "Application", "name" "Exampletron 3000"},
         "summary" "A simple note"},
        "name" "Example 78"},
       "url" "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-generator",
       "id" "as:generator",
       "name" "generator",
       "domain"
       {"type" "owl:Class",
        "unionOf"
        {"type" "Link",
         "href" "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-object",
         "name" "Object"}},
       "isDefinedBy"
       "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-generator",
       "type" ["rdf:Property" "owl:ObjectProperty"],
       "notes"
       "Identifies the entity (e.g. an application) that generated the object."}
      {"range"
       {"type" "owl:Class",
        "unionOf"
        [{"type" "Link",
          "href" "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-image",
          "name" "Image"}
         {"type" "Link",
          "href" "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-link",
          "name" "Link"}]},
       "example"
       [{"id" "https://www.w3.org/TR/activitystreams-vocabulary/#ex77-jsonld",
         "type" "https://schema.org/CreativeWork",
         "mainEntity"
         {"type" "Note",
          "content" "This is all there is.",
          "icon"
          {"type" "Image",
           "height" 16,
           "name" "Note icon",
           "url" "http://example.org/note.png",
           "width" 16},
          "summary" "A simple note"},
         "name" "Example 79"}
        {"id" "https://www.w3.org/TR/activitystreams-vocabulary/#ex78-jsonld",
         "type" "https://schema.org/CreativeWork",
         "mainEntity"
         {"type" "Note",
          "content" "A simple note",
          "icon"
          [{"type" "Image",
            "height" 16,
            "summary" "Note (16x16)",
            "url" "http://example.org/note1.png",
            "width" 16}
           {"type" "Image",
            "height" 32,
            "summary" "Note (32x32)",
            "url" "http://example.org/note2.png",
            "width" 32}],
          "summary" "A simple note"},
         "name" "Example 80"}],
       "url" "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-icon",
       "id" "as:icon",
       "name" "icon",
       "domain"
       {"type" "owl:Class",
        "unionOf"
        {"type" "Link",
         "href" "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-object",
         "name" "Object"}},
       "isDefinedBy"
       "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-icon",
       "type" ["rdf:Property" "owl:ObjectProperty"],
       "notes"
       "Indicates an entity that describes an icon for this object. The image should have an aspect ratio of one (horizontal) to one (vertical) and should be suitable for presentation at a small size."}
      {"range"
       {"type" "owl:Class",
        "unionOf"
        [{"type" "Link",
          "href" "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-image",
          "name" "Image"}
         {"type" "Link",
          "href" "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-link",
          "name" "Link"}]},
       "example"
       [{"id" "https://www.w3.org/TR/activitystreams-vocabulary/#ex80-jsonld",
         "type" "https://schema.org/CreativeWork",
         "mainEntity"
         {"type" "Note",
          "content" "This is all there is.",
          "image"
          {"type" "Image", "name" "A Cat", "url" "http://example.org/cat.png"},
          "name" "A simple note"},
         "name" "Example 81"}
        {"id" "https://www.w3.org/TR/activitystreams-vocabulary/#ex81-jsonld",
         "type" "https://schema.org/CreativeWork",
         "mainEntity"
         {"type" "Note",
          "content" "This is all there is.",
          "image"
          [{"type" "Image",
            "name" "Cat 1",
            "url" "http://example.org/cat1.png"}
           {"type" "Image",
            "name" "Cat 2",
            "url" "http://example.org/cat2.png"}],
          "name" "A simple note"},
         "name" "Example 82"}],
       "url"
       "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-image-term",
       "id" "as:image",
       "name" "image",
       "domain"
       {"type" "owl:Class",
        "unionOf"
        {"type" "Link",
         "href" "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-object",
         "name" "Object"}},
       "isDefinedBy"
       "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-image-term",
       "type" ["rdf:Property" "owl:ObjectProperty"],
       "notes"
       "Indicates an entity that describes an image for this object. Unlike the icon property, there are no aspect ratio or display size limitations assumed."}
      {"range"
       {"type" "owl:Class",
        "unionOf"
        [{"type" "Link",
          "href"
          "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-object",
          "name" "Object"}
         {"type" "Link",
          "href" "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-link",
          "name" "Link"}]},
       "example"
       [{"id" "https://www.w3.org/TR/activitystreams-vocabulary/#ex83-jsonld",
         "type" "https://schema.org/CreativeWork",
         "mainEntity"
         {"type" "Note",
          "content" "This is all there is.",
          "inReplyTo"
          {"type" "Note",
           "content" "What else is there?",
           "summary" "Previous note"},
          "summary" "A simple note"},
         "name" "Example 83"}
        {"id" "https://www.w3.org/TR/activitystreams-vocabulary/#ex84-jsonld",
         "type" "https://schema.org/CreativeWork",
         "mainEntity"
         {"type" "Note",
          "content" "This is all there is.",
          "inReplyTo" "http://example.org/posts/1",
          "summary" "A simple note"},
         "name" "Example 84"}],
       "url" "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-inreplyto",
       "id" "as:inReplyTo",
       "name" "inReplyTo",
       "domain"
       {"type" "owl:Class",
        "unionOf"
        {"type" "Link",
         "href" "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-object",
         "name" "Object"}},
       "isDefinedBy"
       "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-inreplyto",
       "type" ["rdf:Property" "owl:ObjectProperty"],
       "notes"
       "Indicates one or more entities for which this object is considered a response."}
      {"range"
       {"type" "owl:Class",
        "unionOf"
        {"type" "Link",
         "href" "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-object",
         "name" "Object | Link"}},
       "example"
       {"id" "https://www.w3.org/TR/activitystreams-vocabulary/#ex101-jsonld",
        "type" "https://schema.org/CreativeWork",
        "mainEntity"
        {"type" "Listen",
         "actor" {"type" "Person", "name" "Sally"},
         "instrument" {"type" "Service", "name" "Acme Music Service"},
         "object" "http://example.org/foo.mp3",
         "summary"
         "Sally listened to a piece of music on the Acme Music Service"},
        "name" "Example 85"},
       "url"
       "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-instrument",
       "id" "as:instrument",
       "name" "instrument",
       "domain"
       {"type" "owl:Class",
        "unionOf"
        {"type" "Link",
         "href"
         "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-activity",
         "name" "Activity"}},
       "isDefinedBy"
       "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-instrument",
       "type" "rdf:Property",
       "notes"
       "Identifies one or more objects used (or to be used) in the completion of an Activity."}
      {"range"
       {"type" "owl:Class",
        "unionOf"
        [{"type" "Link",
          "href"
          "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-collectionpage",
          "name" "CollectionPage"}
         {"type" "Link",
          "href" "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-link",
          "name" "Link"}]},
       "example"
       [{"id" "https://www.w3.org/TR/activitystreams-vocabulary/#ex87-jsonld",
         "type" "https://schema.org/CreativeWork",
         "mainEntity"
         {"type" "Collection",
          "last" "http://example.org/collection?page=1",
          "summary" "A collection",
          "totalItems" 3},
         "name" "Example 86"}
        {"id" "https://www.w3.org/TR/activitystreams-vocabulary/#ex88-jsonld",
         "type" "https://schema.org/CreativeWork",
         "mainEntity"
         {"type" "Collection",
          "last"
          {"type" "Link",
           "href" "http://example.org/collection?page=1",
           "summary" "Last Page"},
          "summary" "A collection",
          "totalItems" 5},
         "name" "Example 87"}],
       "url" "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-last",
       "id" "as:last",
       "name" "last",
       "domain"
       {"type" "owl:Class",
        "unionOf"
        {"type" "Link",
         "href"
         "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-collection",
         "name" "Collection"}},
       "isDefinedBy"
       "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-last",
       "type" ["rdf:Property" "owl:FunctionalProperty"],
       "notes"
       "In a paged Collection, indicates the furthest proceeding page of the collection."}
      {"range"
       {"type" "owl:Class",
        "unionOf"
        {"type" "Link",
         "href" "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-object",
         "name" "Object | Link"}},
       "example"
       {"id" "https://www.w3.org/TR/activitystreams-vocabulary/#ex89-jsonld",
        "type" "https://schema.org/CreativeWork",
        "mainEntity"
        {"type" "Person",
         "location"
         {"type" "Place",
          "altitude" 90,
          "latitude" 56.78,
          "longitude" 12.34,
          "name"
          "Over the Arabian Sea, east of Socotra Island Nature Sanctuary",
          "units" "m"},
         "name" "Sally"},
        "name" "Example 88"},
       "url" "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-location",
       "id" "as:location",
       "name" "location",
       "domain"
       {"type" "owl:Class",
        "unionOf"
        {"type" "Link",
         "href" "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-object",
         "name" "Object"}},
       "isDefinedBy"
       "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-location",
       "type" ["rdf:Property" "owl:ObjectProperty"],
       "notes"
       "Indicates one or more physical or logical locations associated with the object."}
      {"range"
       {"type" "owl:Class",
        "unionOf"
        [{"type" "Link",
          "href"
          "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-object",
          "name" "Object"}
         {"type" "Link",
          "href" "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-link",
          "name" "Link"}
         {"type" "Link",
          "href"
          "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-object",
          "name" "Object"}
         {"type" "Link",
          "href" "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-link",
          "name" "Link"}]},
       "example"
       [{"id" "https://www.w3.org/TR/activitystreams-vocabulary/#ex91-jsonld",
         "type" "https://schema.org/CreativeWork",
         "mainEntity"
         {"type" "Collection",
          "items"
          [{"type" "Note", "name" "Reminder for Going-Away Party"}
           {"type" "Note", "name" "Meeting 2016-11-17"}],
          "summary" "Sally's notes",
          "totalItems" 2},
         "name" "Example 89"}
        {"id" "https://www.w3.org/TR/activitystreams-vocabulary/#ex92-jsonld",
         "type" "https://schema.org/CreativeWork",
         "mainEntity"
         {"type" "OrderedCollection",
          "orderedItems"
          [{"type" "Note", "name" "Meeting 2016-11-17"}
           {"type" "Note", "name" "Reminder for Going-Away Party"}],
          "summary" "Sally's notes",
          "totalItems" 2},
         "name" "Example 90"}],
       "url" "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-items",
       "id" "as:items",
       "name" "items",
       "domain"
       {"type" "owl:Class",
        "unionOf"
        {"type" "Link",
         "href"
         "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-collection",
         "name" "Collection"}},
       "isDefinedBy"
       "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-items",
       "type" "rdf:Property",
       "notes"
       "Identifies the items contained in a collection. The items might be ordered or unordered."}
      {"range"
       {"type" "owl:Class",
        "unionOf"
        [{"type" "Link",
          "href"
          "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-object",
          "name" "Object"}
         {"type" "Link",
          "href" "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-link",
          "name" "Link"}]},
       "example"
       {"id" "https://www.w3.org/TR/activitystreams-vocabulary/#ex93-jsonld",
        "type" "https://schema.org/CreativeWork",
        "mainEntity"
        {"type" "Question",
         "name" "What is the answer?",
         "oneOf"
         [{"type" "Note", "name" "Option A"}
          {"type" "Note", "name" "Option B"}]},
        "name" "Example 91"},
       "url" "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-oneof",
       "id" "as:oneOf",
       "name" "oneOf",
       "domain"
       {"type" "owl:Class",
        "unionOf"
        {"type" "Link",
         "href"
         "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-question",
         "name" "Question"}},
       "isDefinedBy"
       "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-oneof",
       "type" "rdf:Property",
       "notes"
       "Identifies an exclusive option for a Question. Use of oneOf implies that the Question can have only a single answer. To indicate that a Question can have multiple answers, use anyOf."}
      {"range"
       {"type" "owl:Class",
        "unionOf"
        [{"type" "Link",
          "href"
          "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-object",
          "name" "Object"}
         {"type" "Link",
          "href" "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-link",
          "name" "Link"}]},
       "example"
       {"id" "https://www.w3.org/TR/activitystreams-vocabulary/#ex94-jsonld",
        "type" "https://schema.org/CreativeWork",
        "mainEntity"
        {"type" "Question",
         "anyOf"
         [{"type" "Note", "name" "Option A"}
          {"type" "Note", "name" "Option B"}],
         "name" "What is the answer?"},
        "name" "Example 92"},
       "url" "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-anyof",
       "id" "as:anyOf",
       "name" "anyOf",
       "domain"
       {"type" "owl:Class",
        "unionOf"
        {"type" "Link",
         "href"
         "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-question",
         "name" "Question"}},
       "isDefinedBy"
       "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-anyof",
       "type" "rdf:Property",
       "notes"
       "Identifies an inclusive option for a Question. Use of anyOf implies that the Question can have multiple answers. To indicate that a Question can have only one answer, use oneOf."}
      {"range"
       {"type" "owl:Class",
        "unionOf"
        [{"type" "Link",
          "href"
          "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-object",
          "name" "Object"}
         {"type" "Link",
          "href" "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-link",
          "name" "Link"}
         "xsd:datetime"
         "xsd:boolean"]},
       "example"
       {"id" "https://www.w3.org/TR/activitystreams-vocabulary/#ex94b-jsonld",
        "type" "https://schema.org/CreativeWork",
        "mainEntity"
        {"type" "Question",
         "closed" "2016-05-10T00:00:00Z",
         "name" "What is the answer?"},
        "name" "Example 93"},
       "url" "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-closed",
       "id" "as:closed",
       "name" "closed",
       "domain"
       {"type" "owl:Class",
        "unionOf"
        {"type" "Link",
         "href"
         "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-question",
         "name" "Question"}},
       "isDefinedBy"
       "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-closed",
       "type" "rdf:Property",
       "notes"
       "Indicates that a question has been closed, and answers are no longer accepted."}
      {"range"
       {"type" "owl:Class",
        "unionOf"
        [{"type" "Link",
          "href"
          "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-object",
          "name" "Object"}
         {"type" "Link",
          "href" "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-link",
          "name" "Link"}]},
       "example"
       {"id" "https://www.w3.org/TR/activitystreams-vocabulary/#ex166-jsonld",
        "type" "https://schema.org/CreativeWork",
        "mainEntity"
        {"type" "Move",
         "actor" "http://sally.example.org",
         "object" "http://example.org/posts/1",
         "origin" {"type" "Collection", "name" "List A"},
         "summary" "Sally moved a post from List A to List B",
         "target" {"type" "Collection", "name" "List B"}},
        "name" "Example 94"},
       "url" "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-origin",
       "id" "as:origin",
       "name" "origin",
       "domain"
       {"type" "owl:Class",
        "unionOf"
        {"type" "Link",
         "href"
         "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-activity",
         "name" "Activity"}},
       "isDefinedBy"
       "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-origin",
       "type" "rdf:Property",
       "notes"
       "Describes an indirect object of the activity from which the activity is directed. The precise meaning of the origin is the object of the English preposition \"from\". For instance, in the activity \"John moved an item to List B from List A\", the origin of the activity is \"List A\"."}
      {"range"
       {"type" "owl:Class",
        "unionOf"
        [{"type" "Link",
          "href"
          "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-collectionpage",
          "name" "CollectionPage"}
         {"type" "Link",
          "href" "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-link",
          "name" "Link"}]},
       "example"
       [{"id" "https://www.w3.org/TR/activitystreams-vocabulary/#ex96-jsonld",
         "type" "https://schema.org/CreativeWork",
         "mainEntity"
         {"type" "CollectionPage",
          "items"
          ["http://example.org/posts/1"
           "http://example.org/posts/2"
           "http://example.org/posts/3"],
          "next" "http://example.org/collection?page=2",
          "summary" "Page 2 of Sally's blog posts"},
         "name" "Example 95"}
        {"id" "https://www.w3.org/TR/activitystreams-vocabulary/#ex97-jsonld",
         "type" "https://schema.org/CreativeWork",
         "mainEntity"
         {"type" "CollectionPage",
          "items"
          ["http://example.org/posts/1"
           "http://example.org/posts/2"
           "http://example.org/posts/3"],
          "next"
          {"type" "Link",
           "href" "http://example.org/collection?page=2",
           "name" "Next Page"},
          "summary" "Page 2 of Sally's blog posts"},
         "name" "Example 96"}],
       "url" "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-next",
       "id" "as:next",
       "name" "next",
       "domain"
       {"type" "owl:Class",
        "unionOf"
        {"type" "Link",
         "href"
         "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-collectionpage",
         "name" "CollectionPage"}},
       "isDefinedBy"
       "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-next",
       "type" ["rdf:Property" "owl:FunctionalProperty"],
       "notes" "In a paged Collection, indicates the next page of items."}
      {"range"
       {"type" "owl:Class",
        "unionOf"
        [{"type" "Link",
          "href"
          "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-object",
          "name" "Object"}
         {"type" "Link",
          "href" "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-link",
          "name" "Link"}]},
       "example"
       [{"id" "https://www.w3.org/TR/activitystreams-vocabulary/#ex98-jsonld",
         "type" "https://schema.org/CreativeWork",
         "mainEntity"
         {"type" "Like",
          "actor" "http://sally.example.org",
          "object" "http://example.org/posts/1",
          "summary" "Sally liked a post"},
         "name" "Example 97"}
        {"id" "https://www.w3.org/TR/activitystreams-vocabulary/#ex99-jsonld",
         "type" "https://schema.org/CreativeWork",
         "mainEntity"
         {"type" "Like",
          "actor" "http://sally.example.org",
          "object" {"type" "Note", "content" "A simple note"}},
         "name" "Example 98"}
        {"id" "https://www.w3.org/TR/activitystreams-vocabulary/#ex100-jsonld",
         "type" "https://schema.org/CreativeWork",
         "mainEntity"
         {"type" "Like",
          "actor" "http://sally.example.org",
          "object"
          ["http://example.org/posts/1"
           {"type" "Note",
            "content" "That is a tree.",
            "summary" "A simple note"}],
          "summary" "Sally liked a note"},
         "name" "Example 99"}],
       "url"
       "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-object-term",
       "id" "as:object",
       "name" "object",
       "domain"
       {"type" "owl:Class",
        "unionOf"
        [{"type" "Link",
          "href"
          "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-activity",
          "name" "Activity"}
         {"type" "Link",
          "href"
          "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-relationship",
          "name" "Relationship"}]},
       "isDefinedBy"
       "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-object-term",
       "type" "rdf:Property",
       "notes"
       "When used within an Activity, describes the direct object of the activity. For instance, in the activity \"John added a movie to his wishlist\", the object of the activity is the movie added. When used within a Relationship describes the entity to which the subject is related."}
      {"range"
       {"type" "owl:Class",
        "unionOf"
        [{"type" "Link",
          "href"
          "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-collectionpage",
          "name" "CollectionPage"}
         {"type" "Link",
          "href" "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-link",
          "name" "Link"}]},
       "example"
       [{"id" "https://www.w3.org/TR/activitystreams-vocabulary/#ex104-jsonld",
         "type" "https://schema.org/CreativeWork",
         "mainEntity"
         {"type" "CollectionPage",
          "items"
          ["http://example.org/posts/1"
           "http://example.org/posts/2"
           "http://example.org/posts/3"],
          "prev" "http://example.org/collection?page=1",
          "summary" "Page 1 of Sally's blog posts"},
         "name" "Example 100"}
        {"id" "https://www.w3.org/TR/activitystreams-vocabulary/#ex105-jsonld",
         "type" "https://schema.org/CreativeWork",
         "mainEntity"
         {"type" "CollectionPage",
          "items"
          ["http://example.org/posts/1"
           "http://example.org/posts/2"
           "http://example.org/posts/3"],
          "prev"
          {"type" "Link",
           "href" "http://example.org/collection?page=1",
           "name" "Previous Page"},
          "summary" "Page 1 of Sally's blog posts"},
         "name" "Example 101"}],
       "url" "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-prev",
       "id" "as:prev",
       "name" "prev",
       "domain"
       {"type" "owl:Class",
        "unionOf"
        {"type" "Link",
         "href"
         "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-collectionpage",
         "name" "CollectionPage"}},
       "isDefinedBy"
       "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-prev",
       "type" ["rdf:Property" "owl:FunctionalProperty"],
       "notes" "In a paged Collection, identifies the previous page of items."}
      {"range"
       {"type" "owl:Class",
        "unionOf"
        [{"type" "Link",
          "href" "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-link",
          "name" "Link"}
         {"type" "Link",
          "href"
          "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-object",
          "name" "Object"}]},
       "example"
       {"id" "https://www.w3.org/TR/activitystreams-vocabulary/#ex106-jsonld",
        "type" "https://schema.org/CreativeWork",
        "mainEntity"
        {"type" "Video",
         "duration" "PT2H30M",
         "name" "Cool New Movie",
         "preview"
         {"type" "Video",
          "duration" "PT1M",
          "name" "Trailer",
          "url"
          {"href" "http://example.org/trailer.mkv", "mediaType" "video/mkv"}}},
        "name" "Example 102"},
       "url" "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-preview",
       "id" "as:preview",
       "name" "preview",
       "domain"
       {"type" "owl:Class",
        "unionOf"
        [{"type" "Link",
          "href" "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-link",
          "name" "Link"}
         {"type" "Link",
          "href"
          "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-object",
          "name" "Object"}]},
       "isDefinedBy"
       "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-preview",
       "type" ["rdf:Property" "owl:ObjectProperty"],
       "notes" "Identifies an entity that provides a preview of this object."}
      {"range"
       {"type" "owl:Class",
        "unionOf"
        [{"type" "Link",
          "href"
          "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-object",
          "name" "Object"}
         {"type" "Link",
          "href" "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-link",
          "name" "Link"}]},
       "example"
       {"id" "https://www.w3.org/TR/activitystreams-vocabulary/#ex108-jsonld",
        "type" "https://schema.org/CreativeWork",
        "mainEntity"
        {"type" ["Activity" "http://www.verbs.example/Check"],
         "actor" "http://sally.example.org",
         "object" "http://example.org/flights/1",
         "result"
         {"type" "http://www.types.example/flightstatus", "name" "On Time"},
         "summary" "Sally checked that her flight was on time"},
        "name" "Example 103"},
       "url" "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-result",
       "id" "as:result",
       "name" "result",
       "domain"
       {"type" "owl:Class",
        "unionOf"
        {"type" "Link",
         "href"
         "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-activity",
         "name" "Activity"}},
       "isDefinedBy"
       "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-result",
       "type" "rdf:Property",
       "notes"
       "Describes the result of the activity. For instance, if a particular action results in the creation of a new resource, the result property can be used to describe that new resource."}
      {"range"
       {"type" "owl:Class",
        "unionOf"
        {"type" "Link",
         "href"
         "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-collection",
         "name" "Collection"}},
       "example"
       {"id" "https://www.w3.org/TR/activitystreams-vocabulary/#ex112-jsonld",
        "type" "https://schema.org/CreativeWork",
        "mainEntity"
        {"id" "http://www.test.example/notes/1",
         "type" "Note",
         "content" "I am fine.",
         "replies"
         {"type" "Collection",
          "items"
          {"type" "Note",
           "content" "I am glad to hear it.",
           "inReplyTo" "http://www.test.example/notes/1",
           "summary" "A response to the note"},
          "totalItems" 1},
         "summary" "A simple note"},
        "name" "Example 104"},
       "url" "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-replies",
       "id" "as:replies",
       "name" "replies",
       "domain"
       {"type" "owl:Class",
        "unionOf"
        {"type" "Link",
         "href" "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-object",
         "name" "Object"}},
       "isDefinedBy"
       "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-replies",
       "type" ["rdf:Property" "owl:ObjectProperty" "owl:FunctionalProperty"],
       "notes"
       "Identifies a Collection containing objects considered to be responses to this object."}
      {"range"
       {"type" "owl:Class",
        "unionOf"
        [{"type" "Link",
          "href"
          "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-object",
          "name" "Object"}
         {"type" "Link",
          "href" "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-link",
          "name" "Link"}]},
       "example"
       {"id" "https://www.w3.org/TR/activitystreams-vocabulary/#ex118-jsonld",
        "type" "https://schema.org/CreativeWork",
        "mainEntity"
        {"type" "Image",
         "summary" "Picture of Sally",
         "tag"
         {"id" "http://sally.example.org", "type" "Person", "name" "Sally"},
         "url" "http://example.org/sally.jpg"},
        "name" "Example 105"},
       "url" "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-tag",
       "id" "as:tag",
       "name" "tag",
       "domain"
       {"type" "owl:Class",
        "unionOf"
        {"type" "Link",
         "href" "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-object",
         "name" "Object"}},
       "isDefinedBy"
       "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-tag",
       "type" ["rdf:Property" "owl:ObjectProperty"],
       "notes"
       "One or more \"tags\" that have been associated with an objects. A tag can be any kind of Object. The key difference between attachment and tag is that the former implies association by inclusion, while the latter implies associated by reference."}
      {"range"
       {"type" "owl:Class",
        "unionOf"
        [{"type" "Link",
          "href"
          "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-object",
          "name" "Object"}
         {"type" "Link",
          "href" "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-link",
          "name" "Link"}]},
       "example"
       [{"id" "https://www.w3.org/TR/activitystreams-vocabulary/#ex120-jsonld",
         "type" "https://schema.org/CreativeWork",
         "mainEntity"
         {"type" "Offer",
          "actor" "http://sally.example.org",
          "object" "http://example.org/posts/1",
          "summary" "Sally offered the post to John",
          "target" "http://john.example.org"},
         "name" "Example 106"}
        {"id" "https://www.w3.org/TR/activitystreams-vocabulary/#ex121-jsonld",
         "type" "https://schema.org/CreativeWork",
         "mainEntity"
         {"type" "Offer",
          "actor" "http://sally.example.org",
          "object" "http://example.org/posts/1",
          "summary" "Sally offered the post to John",
          "target" {"type" "Person", "name" "John"}},
         "name" "Example 107"}],
       "url" "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-target",
       "id" "as:target",
       "name" "target",
       "domain"
       {"type" "owl:Class",
        "unionOf"
        {"type" "Link",
         "href"
         "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-activity",
         "name" "Activity"}},
       "isDefinedBy"
       "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-target",
       "type" "rdf:Property",
       "notes"
       "Describes the indirect object, or target, of the activity. The precise meaning of the target is largely dependent on the type of action being described but will often be the object of the English preposition \"to\". For instance, in the activity \"John added a movie to his wishlist\", the target of the activity is John's wishlist. An activity can have more than one target."}
      {"range"
       {"type" "owl:Class",
        "unionOf"
        [{"type" "Link",
          "href"
          "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-object",
          "name" "Object"}
         {"type" "Link",
          "href" "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-link",
          "name" "Link"}]},
       "example"
       {"id" "https://www.w3.org/TR/activitystreams-vocabulary/#ex123-jsonld",
        "type" "https://schema.org/CreativeWork",
        "mainEntity"
        {"type" "Offer",
         "actor" "http://sally.example.org",
         "object" "http://example.org/posts/1",
         "summary" "Sally offered the post to John",
         "target" "http://john.example.org",
         "to" "http://joe.example.org"},
        "name" "Example 108"},
       "url" "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-to",
       "id" "as:to",
       "name" "to",
       "domain"
       {"type" "owl:Class",
        "unionOf"
        {"type" "Link",
         "href" "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-object",
         "name" "Object"}},
       "isDefinedBy"
       "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-to",
       "type" ["rdf:Property" "owl:ObjectProperty"],
       "notes"
       "Identifies an entity considered to be part of the public primary audience of an Object"}
      {"range"
       {"type" "owl:Class",
        "unionOf"
        ["xsd:anyuri"
         {"type" "Link",
          "href" "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-link",
          "name" "Link"}]},
       "example"
       [{"id" "https://www.w3.org/TR/activitystreams-vocabulary/#ex124-jsonld",
         "type" "https://schema.org/CreativeWork",
         "mainEntity"
         {"type" "Document",
          "name" "4Q Sales Forecast",
          "url" "http://example.org/4q-sales-forecast.pdf"},
         "name" "Example 109"}
        {"id" "https://www.w3.org/TR/activitystreams-vocabulary/#ex125-jsonld",
         "type" "https://schema.org/CreativeWork",
         "mainEntity"
         {"type" "Document",
          "name" "4Q Sales Forecast",
          "url"
          {"type" "Link", "href" "http://example.org/4q-sales-forecast.pdf"}},
         "name" "Example 110"}
        {"id" "https://www.w3.org/TR/activitystreams-vocabulary/#ex126-jsonld",
         "type" "https://schema.org/CreativeWork",
         "mainEntity"
         {"type" "Document",
          "name" "4Q Sales Forecast",
          "url"
          [{"type" "Link",
            "href" "http://example.org/4q-sales-forecast.pdf",
            "mediaType" "application/pdf"}
           {"type" "Link",
            "href" "http://example.org/4q-sales-forecast.html",
            "mediaType" "text/html"}]},
         "name" "Example 111"}],
       "url" "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-url",
       "id" "as:url",
       "name" "url",
       "domain"
       {"type" "owl:Class",
        "unionOf"
        {"type" "Link",
         "href" "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-object",
         "name" "Object"}},
       "isDefinedBy"
       "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-url",
       "type" ["rdf:Property" "owl:ObjectProperty"],
       "notes" "Identifies one or more links to representations of the object"}
      {"range" {"type" "owl:Class", "unionOf" "xsd:float"},
       "example"
       {"id" "https://www.w3.org/TR/activitystreams-vocabulary/#ex127-jsonld",
        "type" "https://schema.org/CreativeWork",
        "mainEntity"
        {"type" "Place",
         "accuracy" 94.5,
         "latitude" 36.75,
         "longitude" 119.7667,
         "name" "Liu Gu Lu Cun, Pingdu, Qingdao, Shandong, China"},
        "name" "Example 112"},
       "url" "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-accuracy",
       "id" "as:accuracy",
       "name" "accuracy",
       "domain"
       {"type" "owl:Class",
        "unionOf"
        {"type" "Link",
         "href" "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-place",
         "name" "Place"}},
       "isDefinedBy"
       "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-accuracy",
       "type" ["rdf:Property" "owl:FunctionalProperty"],
       "notes"
       "Indicates the accuracy of position coordinates on a Place objects. Expressed in properties of percentage. e.g. \"94.0\" means \"94.0% accurate\"."}
      {"range" {"type" "owl:Class", "unionOf" "xsd:float"},
       "example"
       {"id" "https://www.w3.org/TR/activitystreams-vocabulary/#ex129-jsonld",
        "type" "https://schema.org/CreativeWork",
        "mainEntity"
        {"type" "Place",
         "altitude" 15,
         "latitude" 36.75,
         "longitude" 119.7667,
         "name" "Fresno Area",
         "units" "miles"},
        "name" "Example 113"},
       "url" "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-altitude",
       "id" "as:altitude",
       "name" "altitude",
       "domain"
       {"type" "owl:Class",
        "unionOf"
        {"type" "Link",
         "href" "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-object",
         "name" "Object"}},
       "isDefinedBy"
       "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-altitude",
       "type" ["rdf:Property" "owl:ObjectProperty" "owl:FunctionalProperty"],
       "notes"
       "Indicates the altitude of a place. The measurement units is indicated using the units property. If units is not specified, the default is assumed to be \"m\" indicating meters."}
      {"range" {"type" "owl:Class", "unionOf" ["xsd:string" "rdf:langstring"]},
       "example"
       [{"id" "https://www.w3.org/TR/activitystreams-vocabulary/#ex130-jsonld",
         "type" "https://schema.org/CreativeWork",
         "mainEntity"
         {"type" "Note",
          "content" "A <em>simple</em> note",
          "summary" "A simple note"},
         "name" "Example 114"}
        {"id" "https://www.w3.org/TR/activitystreams-vocabulary/#ex131-jsonld",
         "type" "https://schema.org/CreativeWork",
         "mainEntity"
         {"type" "Note",
          "contentMap"
          {"en" "A <em>simple</em> note",
           "es" "Una nota <em>sencilla</em>",
           "zh-hans" "一段<em>简单的</em>笔记"},
          "summary" "A simple note"},
         "name" "Example 115"}
        {"id"
         "https://www.w3.org/TR/activitystreams-vocabulary/#ex130b-jsonld",
         "type" "https://schema.org/CreativeWork",
         "mainEntity"
         {"type" "Note",
          "content" "## A simple note\nA simple markdown `note`",
          "mediaType" "text/markdown",
          "summary" "A simple note"},
         "name" "Example 116"}],
       "url" "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-content",
       "id" "as:content",
       "name" "content",
       "domain"
       {"type" "owl:Class",
        "unionOf"
        {"type" "Link",
         "href" "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-object",
         "name" "Object"}},
       "isDefinedBy"
       "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-content",
       "type" ["rdf:Property" "owl:ObjectProperty"],
       "notes"
       "The content or textual representation of the Object encoded as a JSON string. By default, the value of content is HTML. The mediaType property can be used in the object to indicate a different content type. The content MAY be expressed using multiple language-tagged values."}
      {"range" {"type" "owl:Class", "unionOf" ["xsd:string" "rdf:langstring"]},
       "example"
       [{"id" "https://www.w3.org/TR/activitystreams-vocabulary/#ex132-jsonld",
         "type" "https://schema.org/CreativeWork",
         "mainEntity" {"type" "Note", "name" "A simple note"},
         "name" "Example 117"}
        {"id" "https://www.w3.org/TR/activitystreams-vocabulary/#ex133-jsonld",
         "type" "https://schema.org/CreativeWork",
         "mainEntity"
         {"type" "Note",
          "nameMap"
          {"en" "A simple note",
           "es" "Una nota sencilla",
           "zh-hans" "一段简单的笔记"}},
         "name" "Example 118"}],
       "url" "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-name",
       "id" "as:name",
       "name" "name",
       "domain"
       {"type" "owl:Class",
        "unionOf"
        [{"type" "Link",
          "href"
          "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-object",
          "name" "Object"}
         {"type" "Link",
          "href" "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-link",
          "name" "Link"}]},
       "isDefinedBy"
       "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-name",
       "type" ["rdf:Property" "owl:ObjectProperty"],
       "notes"
       "A simple, human-readable, plain-text name for the object. HTML markup MUST NOT be included. The name MAY be expressed using multiple language-tagged values."}
      {"range" {"type" "owl:Class", "unionOf" "xsd:duration"},
       "example"
       {"id" "https://www.w3.org/TR/activitystreams-vocabulary/#ex134-jsonld",
        "type" "https://schema.org/CreativeWork",
        "mainEntity"
        {"type" "Video",
         "duration" "PT2H",
         "name" "Birds Flying",
         "url" "http://example.org/video.mkv"},
        "name" "Example 119"},
       "url" "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-duration",
       "id" "as:duration",
       "name" "duration",
       "domain"
       {"type" "owl:Class",
        "unionOf"
        {"type" "Link",
         "href" "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-object",
         "name" "Object"}},
       "isDefinedBy"
       "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-duration",
       "type" ["rdf:Property" "owl:ObjectProperty" "owl:FunctionalProperty"],
       "notes"
       "When the object describes a time-bound resource, such as an audio or video, a meeting, etc, the duration property indicates the object's approximate duration. The value MUST be expressed as an xsd:duration as defined by [ xmlschema11-2], section 3.3.6 (e.g. a period of 5 seconds is represented as \"PT5S\")."}
      {"range" {"type" "owl:Class", "unionOf" "xsd:nonnegativeinteger"},
       "example"
       {"id" "https://www.w3.org/TR/activitystreams-vocabulary/#ex136-jsonld",
        "type" "https://schema.org/CreativeWork",
        "mainEntity"
        {"type" "Link",
         "height" 100,
         "href" "http://example.org/image.png",
         "width" 100},
        "name" "Example 120"},
       "url" "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-height",
       "id" "as:height",
       "name" "height",
       "domain"
       {"type" "owl:Class",
        "unionOf"
        {"type" "Link",
         "href" "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-link",
         "name" "Link"}},
       "isDefinedBy"
       "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-height",
       "type" ["rdf:Property" "owl:FunctionalProperty"],
       "notes"
       "On a Link, specifies a hint as to the rendering height in device-independent pixels of the linked resource."}
      {"range" {"type" "owl:Class", "unionOf" "xsd:anyuri"},
       "example"
       {"id" "https://www.w3.org/TR/activitystreams-vocabulary/#ex137-jsonld",
        "type" "https://schema.org/CreativeWork",
        "mainEntity"
        {"type" "Link",
         "href" "http://example.org/abc",
         "mediaType" "text/html",
         "name" "Previous"},
        "name" "Example 121"},
       "url" "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-href",
       "id" "as:href",
       "name" "href",
       "domain"
       {"type" "owl:Class",
        "unionOf"
        {"type" "Link",
         "href" "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-link",
         "name" "Link"}},
       "isDefinedBy"
       "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-href",
       "type" ["rdf:Property" "owl:FunctionalProperty"],
       "notes" "The target resource pointed to by a Link."}
      {"range" {"type" "owl:Class", "unionOf" []},
       "example"
       {"id" "https://www.w3.org/TR/activitystreams-vocabulary/#ex138-jsonld",
        "type" "https://schema.org/CreativeWork",
        "mainEntity"
        {"type" "Link",
         "href" "http://example.org/abc",
         "hreflang" "en",
         "mediaType" "text/html",
         "name" "Previous"},
        "name" "Example 122"},
       "url" "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-hreflang",
       "id" "as:hreflang",
       "name" "hreflang",
       "domain"
       {"type" "owl:Class",
        "unionOf"
        {"type" "Link",
         "href" "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-link",
         "name" "Link"}},
       "isDefinedBy"
       "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-hreflang",
       "type" ["rdf:Property" "owl:FunctionalProperty"],
       "notes"
       "Hints as to the language used by the target resource. Value MUST be a [BCP47] Language-Tag."}
      {"range"
       {"type" "owl:Class",
        "unionOf"
        [{"type" "Link",
          "href" "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-link",
          "name" "Link"}
         {"type" "Link",
          "href"
          "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-collection",
          "name" "Collection"}]},
       "example"
       {"id" "https://www.w3.org/TR/activitystreams-vocabulary/#ex139-jsonld",
        "type" "https://schema.org/CreativeWork",
        "mainEntity"
        {"id" "http://example.org/collection?page=1",
         "type" "CollectionPage",
         "items"
         [{"type" "Note", "name" "Pizza Toppings to Try"}
          {"type" "Note", "name" "Thought about California"}],
         "partOf" "http://example.org/collection",
         "summary" "Page 1 of Sally's notes"},
        "name" "Example 123"},
       "url" "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-partof",
       "id" "as:partOf",
       "name" "partOf",
       "domain"
       {"type" "owl:Class",
        "unionOf"
        {"type" "Link",
         "href"
         "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-collectionpage",
         "name" "CollectionPage"}},
       "isDefinedBy"
       "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-partof",
       "type" ["rdf:Property" "owl:FunctionalProperty"],
       "notes"
       "Identifies the Collection to which a CollectionPage objects items belong."}
      {"range" {"type" "owl:Class", "unionOf" "xsd:float"},
       "example"
       {"id" "https://www.w3.org/TR/activitystreams-vocabulary/#ex140-jsonld",
        "type" "https://schema.org/CreativeWork",
        "mainEntity"
        {"type" "Place",
         "latitude" 36.75,
         "longitude" 119.7667,
         "name" "Fresno Area",
         "radius" 15,
         "units" "miles"},
        "name" "Example 124"},
       "url" "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-latitude",
       "id" "as:latitude",
       "name" "latitude",
       "domain"
       {"type" "owl:Class",
        "unionOf"
        {"type" "Link",
         "href" "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-place",
         "name" "Place"}},
       "isDefinedBy"
       "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-latitude",
       "type" ["rdf:Property" "owl:FunctionalProperty"],
       "notes" "The latitude of a place"}
      {"range" {"type" "owl:Class", "unionOf" "xsd:float"},
       "example"
       {"id" "https://www.w3.org/TR/activitystreams-vocabulary/#ex141-jsonld",
        "type" "https://schema.org/CreativeWork",
        "mainEntity"
        {"type" "Place",
         "latitude" 36.75,
         "longitude" 119.7667,
         "name" "Fresno Area",
         "radius" 15,
         "units" "miles"},
        "name" "Example 125"},
       "url" "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-longitude",
       "id" "as:longitude",
       "name" "longitude",
       "domain"
       {"type" "owl:Class",
        "unionOf"
        {"type" "Link",
         "href" "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-place",
         "name" "Place"}},
       "isDefinedBy"
       "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-longitude",
       "type" ["rdf:Property" "owl:FunctionalProperty"],
       "notes" "The longitude of a place"}
      {"range" {"type" "owl:Class", "unionOf" []},
       "example"
       {"id" "https://www.w3.org/TR/activitystreams-vocabulary/#ex142-jsonld",
        "type" "https://schema.org/CreativeWork",
        "mainEntity"
        {"type" "Link",
         "href" "http://example.org/abc",
         "hreflang" "en",
         "mediaType" "text/html",
         "name" "Next"},
        "name" "Example 126"},
       "url" "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-mediatype",
       "id" "as:mediaType",
       "name" "mediaType",
       "domain"
       {"type" "owl:Class",
        "unionOf"
        [{"type" "Link",
          "href" "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-link",
          "name" "Link"}
         {"type" "Link",
          "href"
          "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-object",
          "name" "Object"}]},
       "isDefinedBy"
       "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-mediatype",
       "type" ["rdf:Property" "owl:ObjectProperty" "owl:FunctionalProperty"],
       "notes"
       "When used on a Link, identifies the MIME media type of the referenced resource. When used on an Object, identifies the MIME media type of the value of the content property. If not specified, the content property is assumed to contain text/html content."}
      {"range" {"type" "owl:Class", "unionOf" "xsd:datetime"},
       "example"
       {"id" "https://www.w3.org/TR/activitystreams-vocabulary/#ex144-jsonld",
        "type" "https://schema.org/CreativeWork",
        "mainEntity"
        {"type" "Event",
         "endTime" "2015-01-01T06:00:00-08:00",
         "name" "Going-Away Party for Jim",
         "startTime" "2014-12-31T23:00:00-08:00"},
        "name" "Example 127"},
       "url" "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-endtime",
       "id" "as:endTime",
       "name" "endTime",
       "domain"
       {"type" "owl:Class",
        "unionOf"
        {"type" "Link",
         "href" "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-object",
         "name" "Object"}},
       "isDefinedBy"
       "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-endtime",
       "type" ["rdf:Property" "owl:ObjectProperty" "owl:FunctionalProperty"],
       "notes"
       "The date and time describing the actual or expected ending time of the object. When used with an Activity object, for instance, the endTime property specifies the moment the activity concluded or is expected to conclude."}
      {"range" {"type" "owl:Class", "unionOf" "xsd:datetime"},
       "example"
       {"id" "https://www.w3.org/TR/activitystreams-vocabulary/#ex145-jsonld",
        "type" "https://schema.org/CreativeWork",
        "mainEntity"
        {"type" "Note",
         "content" "Fish swim.",
         "published" "2014-12-12T12:12:12Z",
         "summary" "A simple note"},
        "name" "Example 128"},
       "url" "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-published",
       "id" "as:published",
       "name" "published",
       "domain"
       {"type" "owl:Class",
        "unionOf"
        {"type" "Link",
         "href" "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-object",
         "name" "Object"}},
       "isDefinedBy"
       "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-published",
       "type" ["rdf:Property" "owl:ObjectProperty" "owl:FunctionalProperty"],
       "notes" "The date and time at which the object was published"}
      {"range" {"type" "owl:Class", "unionOf" "xsd:datetime"},
       "example"
       {"id" "https://www.w3.org/TR/activitystreams-vocabulary/#ex146-jsonld",
        "type" "https://schema.org/CreativeWork",
        "mainEntity"
        {"type" "Event",
         "endTime" "2015-01-01T06:00:00-08:00",
         "name" "Going-Away Party for Jim",
         "startTime" "2014-12-31T23:00:00-08:00"},
        "name" "Example 129"},
       "url" "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-starttime",
       "id" "as:startTime",
       "name" "startTime",
       "domain"
       {"type" "owl:Class",
        "unionOf"
        {"type" "Link",
         "href" "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-object",
         "name" "Object"}},
       "isDefinedBy"
       "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-starttime",
       "type" ["rdf:Property" "owl:ObjectProperty" "owl:FunctionalProperty"],
       "notes"
       "The date and time describing the actual or expected starting time of the object. When used with an Activity object, for instance, the startTime property specifies the moment the activity began or is scheduled to begin."}
      {"range" {"type" "owl:Class", "unionOf" "xsd:float"},
       "example"
       {"id" "https://www.w3.org/TR/activitystreams-vocabulary/#ex147-jsonld",
        "type" "https://schema.org/CreativeWork",
        "mainEntity"
        {"type" "Place",
         "latitude" 36.75,
         "longitude" 119.7667,
         "name" "Fresno Area",
         "radius" 15,
         "units" "miles"},
        "name" "Example 130"},
       "url" "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-radius",
       "id" "as:radius",
       "name" "radius",
       "domain"
       {"type" "owl:Class",
        "unionOf"
        {"type" "Link",
         "href" "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-place",
         "name" "Place"}},
       "isDefinedBy"
       "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-radius",
       "type" ["rdf:Property" "owl:FunctionalProperty"],
       "notes"
       "The radius from the given latitude and longitude for a Place. The units is expressed by the units property. If units is not specified, the default is assumed to be \"m\" indicating \"meters\"."}
      {"range" {"type" "owl:Class", "unionOf" []},
       "example"
       {"id" "https://www.w3.org/TR/activitystreams-vocabulary/#ex149-jsonld",
        "type" "https://schema.org/CreativeWork",
        "mainEntity"
        {"type" "Link",
         "href" "http://example.org/abc",
         "hreflang" "en",
         "mediaType" "text/html",
         "name" "Preview",
         "rel" ["canonical" "preview"]},
        "name" "Example 131"},
       "url" "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-rel",
       "id" "as:rel",
       "name" "rel",
       "domain"
       {"type" "owl:Class",
        "unionOf"
        {"type" "Link",
         "href" "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-link",
         "name" "Link"}},
       "isDefinedBy"
       "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-rel",
       "type" "rdf:Property",
       "notes"
       "A link relation associated with a Link. The value MUST conform to both the [HTML5] and [RFC5988] \"link relation\" definitions. In the [HTML5], any string not containing the \"space\" U+0020, \"tab\" (U+0009), \"LF\" (U+000A), \"FF\" (U+000C), \"CR\" (U+000D) or \",\" (U+002C) characters can be used as a valid link relation."}
      {"range" {"type" "owl:Class", "unionOf" "xsd:nonnegativeinteger"},
       "example"
       {"id" "https://www.w3.org/TR/activitystreams-vocabulary/#ex150-jsonld",
        "type" "https://schema.org/CreativeWork",
        "mainEntity"
        {"type" "OrderedCollectionPage",
         "orderedItems"
         [{"type" "Note", "name" "Density of Water"}
          {"type" "Note", "name" "Air Mattress Idea"}],
         "startIndex" 0,
         "summary" "Page 1 of Sally's notes"},
        "name" "Example 132"},
       "url"
       "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-startindex",
       "id" "as:startIndex",
       "name" "startIndex",
       "domain"
       {"type" "owl:Class",
        "unionOf"
        {"type" "Link",
         "href"
         "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-orderedcollectionpage",
         "name" "OrderedCollectionPage"}},
       "isDefinedBy"
       "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-startindex",
       "type" ["rdf:Property" "owl:FunctionalProperty"],
       "notes"
       "A non-negative integer value identifying the relative position within the logical view of a strictly ordered collection."}
      {"range" {"type" "owl:Class", "unionOf" ["xsd:string" "rdf:langstring"]},
       "example"
       [{"id" "https://www.w3.org/TR/activitystreams-vocabulary/#ex152-jsonld",
         "type" "https://schema.org/CreativeWork",
         "mainEntity"
         {"type" "Note",
          "name" "Cane Sugar Processing",
          "summary" "A simple <em>note</em>"},
         "name" "Example 133"}
        {"id" "https://www.w3.org/TR/activitystreams-vocabulary/#ex153-jsonld",
         "type" "https://schema.org/CreativeWork",
         "mainEntity"
         {"type" "Note",
          "name" "Cane Sugar Processing",
          "summaryMap"
          {"en" "A simple <em>note</em>",
           "es" "Una <em>nota</em> sencilla",
           "zh-hans" "一段<em>简单的</em>笔记"}},
         "name" "Example 134"}],
       "url" "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-summary",
       "id" "as:summary",
       "name" "summary",
       "domain"
       {"type" "owl:Class",
        "unionOf"
        {"type" "Link",
         "href" "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-object",
         "name" "Object"}},
       "isDefinedBy"
       "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-summary",
       "type" ["rdf:Property" "owl:ObjectProperty"],
       "notes"
       "A natural language summarization of the object encoded as HTML. Multiple language tagged summaries MAY be provided."}
      {"range" {"type" "owl:Class", "unionOf" "xsd:nonnegativeinteger"},
       "example"
       {"id" "https://www.w3.org/TR/activitystreams-vocabulary/#ex156-jsonld",
        "type" "https://schema.org/CreativeWork",
        "mainEntity"
        {"type" "Collection",
         "items"
         [{"type" "Note", "name" "Which Staircase Should I Use"}
          {"type" "Note", "name" "Something to Remember"}],
         "summary" "Sally's notes",
         "totalItems" 2},
        "name" "Example 135"},
       "url"
       "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-totalitems",
       "id" "as:totalItems",
       "name" "totalItems",
       "domain"
       {"type" "owl:Class",
        "unionOf"
        {"type" "Link",
         "href"
         "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-collection",
         "name" "Collection"}},
       "isDefinedBy"
       "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-totalitems",
       "type" ["rdf:Property" "owl:FunctionalProperty"],
       "notes"
       "A non-negative integer specifying the total number of objects contained by the logical view of the collection. This number might not reflect the actual number of items serialized within the Collection object instance."}
      {"range"
       {"type" "owl:Class",
        "unionOf"
        ["https://www.w3.org/TR/activitystreams-vocabulary/cm"
         "https://www.w3.org/TR/activitystreams-vocabulary/feet"
         "https://www.w3.org/TR/activitystreams-vocabulary/inches"
         "https://www.w3.org/TR/activitystreams-vocabulary/km"
         "https://www.w3.org/TR/activitystreams-vocabulary/m"
         "https://www.w3.org/TR/activitystreams-vocabulary/miles"
         "xsd:anyuri"]},
       "example"
       {"id" "https://www.w3.org/TR/activitystreams-vocabulary/#ex157-jsonld",
        "type" "https://schema.org/CreativeWork",
        "mainEntity"
        {"type" "Place",
         "latitude" 36.75,
         "longitude" 119.7667,
         "name" "Fresno Area",
         "radius" 15,
         "units" "miles"},
        "name" "Example 136"},
       "url" "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-units",
       "id" "as:units",
       "name" "units",
       "domain"
       {"type" "owl:Class",
        "unionOf"
        {"type" "Link",
         "href" "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-place",
         "name" "Place"}},
       "isDefinedBy"
       "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-units",
       "type" ["rdf:Property" "owl:FunctionalProperty"],
       "notes"
       "Specifies the measurement units for the radius and altitude properties on a Place object. If not specified, the default is assumed to be \"m\" for \"meters\"."}
      {"range" {"type" "owl:Class", "unionOf" "xsd:datetime"},
       "example"
       {"id" "https://www.w3.org/TR/activitystreams-vocabulary/#ex158-jsonld",
        "type" "https://schema.org/CreativeWork",
        "mainEntity"
        {"type" "Note",
         "content" "Mush it up so it does not have the same shape as the can.",
         "name" "Cranberry Sauce Idea",
         "updated" "2014-12-12T12:12:12Z"},
        "name" "Example 137"},
       "url" "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-updated",
       "id" "as:updated",
       "name" "updated",
       "domain"
       {"type" "owl:Class",
        "unionOf"
        {"type" "Link",
         "href" "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-object",
         "name" "Object"}},
       "isDefinedBy"
       "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-updated",
       "type" ["rdf:Property" "owl:ObjectProperty" "owl:FunctionalProperty"],
       "notes" "The date and time at which the object was updated"}
      {"range" {"type" "owl:Class", "unionOf" "xsd:nonnegativeinteger"},
       "example"
       {"id" "https://www.w3.org/TR/activitystreams-vocabulary/#ex159-jsonld",
        "type" "https://schema.org/CreativeWork",
        "mainEntity"
        {"type" "Link",
         "height" 100,
         "href" "http://example.org/image.png",
         "width" 100},
        "name" "Example 138"},
       "url" "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-width",
       "id" "as:width",
       "name" "width",
       "domain"
       {"type" "owl:Class",
        "unionOf"
        {"type" "Link",
         "href" "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-link",
         "name" "Link"}},
       "isDefinedBy"
       "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-width",
       "type" ["rdf:Property" "owl:FunctionalProperty"],
       "notes"
       "On a Link, specifies a hint as to the rendering width in device-independent pixels of the linked resource."}
      {"range"
       {"type" "owl:Class",
        "unionOf"
        [{"type" "Link",
          "href" "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-link",
          "name" "Link"}
         {"type" "Link",
          "href"
          "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-object",
          "name" "Object"}]},
       "example"
       {"id" "https://www.w3.org/TR/activitystreams-vocabulary/#ex22a-jsonld",
        "type" "https://schema.org/CreativeWork",
        "mainEntity"
        {"type" "Relationship",
         "object" {"type" "Person", "name" "John"},
         "relationship" "http://purl.org/vocab/relationship/acquaintanceOf",
         "subject" {"type" "Person", "name" "Sally"},
         "summary" "Sally is an acquaintance of John's"},
        "name" "Example 139"},
       "url" "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-subject",
       "id" "as:subject",
       "name" "subject",
       "domain"
       {"type" "owl:Class",
        "unionOf"
        {"type" "Link",
         "href"
         "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-relationship",
         "name" "Relationship"}},
       "isDefinedBy"
       "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-subject",
       "type" ["rdf:Property" "owl:FunctionalProperty"],
       "notes"
       "On a Relationship object, the subject property identifies one of the connected individuals. For instance, for a Relationship object describing \"John is related to Sally\", subject would refer to John."}
      {"range"
       {"type" "owl:Class",
        "unionOf"
        {"type" "Link",
         "href" "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-object",
         "name" "Object"}},
       "example"
       {"id" "https://www.w3.org/TR/activitystreams-vocabulary/#ex22c-jsonld",
        "type" "https://schema.org/CreativeWork",
        "mainEntity"
        {"type" "Relationship",
         "object" {"type" "Person", "name" "John"},
         "relationship" "http://purl.org/vocab/relationship/acquaintanceOf",
         "subject" {"type" "Person", "name" "Sally"},
         "summary" "Sally is an acquaintance of John's"},
        "name" "Example 140"},
       "url"
       "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-relationship-term",
       "id" "as:relationship",
       "name" "relationship",
       "domain"
       {"type" "owl:Class",
        "unionOf"
        {"type" "Link",
         "href"
         "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-relationship",
         "name" "Relationship"}},
       "isDefinedBy"
       "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-relationship-term",
       "type" "rdf:Property",
       "notes"
       "On a Relationship object, the relationship property identifies the kind of relationship that exists between subject and object."}
      {"range"
       {"type" "owl:Class",
        "unionOf"
        {"type" "Link",
         "href" "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-object",
         "name" "Object"}},
       "example"
       {"id" "https://www.w3.org/TR/activitystreams-vocabulary/#ex185-jsonld",
        "type" "https://schema.org/CreativeWork",
        "mainEntity"
        {"type" "Profile",
         "describes" {"type" "Person", "name" "Sally"},
         "summary" "Sally's profile",
         "url" "http://sally.example.org"},
        "name" "Example 141"},
       "url" "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-describes",
       "id" "as:describes",
       "name" "describes",
       "domain"
       {"type" "owl:Class",
        "unionOf"
        {"type" "Link",
         "href"
         "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-profile",
         "name" "Profile"}},
       "isDefinedBy"
       "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-describes",
       "type" ["rdf:Property" "owl:FunctionalProperty"],
       "notes"
       "On a Profile object, the describes property identifies the object described by the Profile."}
      {"range"
       {"type" "owl:Class",
        "unionOf"
        {"type" "Link",
         "href" "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-object",
         "name" "Object"}},
       "example"
       {"id" "https://www.w3.org/TR/activitystreams-vocabulary/#ex185b-jsonld",
        "type" "https://schema.org/CreativeWork",
        "mainEntity"
        {"type" "Tombstone",
         "formerType" "/Image",
         "summary" "This image has been deleted",
         "url" "http://example.org/image/2"},
        "name" "Example 142"},
       "url"
       "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-formertype",
       "id" "as:formerType",
       "name" "formerType",
       "domain"
       {"type" "owl:Class",
        "unionOf"
        {"type" "Link",
         "href"
         "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-tombstone",
         "name" "Tombstone"}},
       "isDefinedBy"
       "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-formertype",
       "type" "rdf:Property",
       "notes"
       "On a Tombstone object, the formerType property identifies the type of the object that was deleted."}
      {"range" {"type" "owl:Class", "unionOf" "xsd:datetime"},
       "example"
       {"id" "https://www.w3.org/TR/activitystreams-vocabulary/#ex185c-jsonld",
        "type" "https://schema.org/CreativeWork",
        "mainEntity"
        {"type" "Tombstone",
         "deleted" "2016-05-03T00:00:00Z",
         "summary" "This image has been deleted"},
        "name" "Example 143"},
       "url" "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-deleted",
       "id" "as:deleted",
       "name" "deleted",
       "domain"
       {"type" "owl:Class",
        "unionOf"
        {"type" "Link",
         "href"
         "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-tombstone",
         "name" "Tombstone"}},
       "isDefinedBy"
       "https://www.w3.org/TR/activitystreams-vocabulary/#dfn-deleted",
       "type" ["rdf:Property" "owl:FunctionalProperty"],
       "notes"
       "On a Tombstone object, the deleted property is a timestamp for when the object was deleted."}]}}}}}
