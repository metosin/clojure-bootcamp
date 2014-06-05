(ns compojure-intro.db
  (:require [monger.core :as m]
            [monger.json]
            [monger.collection :as mc]))

(let [conn (m/connect)
      db   (m/get-db conn "clojure-bootcamp")
      coll :beers]

  (defn all []
    (mc/find-maps db coll))

  (defn insert! [body]
    (mc/insert-and-return db coll body))

  (defn get-by-name [name]
    (mc/find-one-as-map db coll {:name name}))

  (defn remove-by-name! [name]
    (mc/remove db coll {:name name})
    nil)

  (defn update-by-name! [name body]
    (mc/update db coll {:name name} body)
    nil)

  (defn remove-all! []
    (mc/remove db coll)
    nil))

(comment
  (insert! {:name "futu"})
  (insert! {:name "olvi"})
  (update-by-name! "olvi" {:name "olvi" :taste "good"})
  (get-by-name "olvi")
  (remove-by-name! "olvi")
  (all)
  (remove-all!))
