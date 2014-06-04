(ns monger-intro.core
  (:require [monger.core :as m]
            [monger.collection :as mc]))

;;
;; have a local mongodb running at default settings
;;

(def conn (m/connect))
(def db   (m/get-db conn "clojure-bootcamp"))

(mc/insert db :beers {:name "kukko" :tags [:green]})
(mc/insert db :beers {:name "futu" :tags [:sweet]})

(->> (mc/find-maps db :beers)
     (map println)
     (doall))

(mc/remove db :beers)
