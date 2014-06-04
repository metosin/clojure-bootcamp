(ns compojure-intro.db)

;;
;; The Evil Global State (in memory)
;;

(defonce data   (atom {}))
(defonce ids    (atom 0))

;;
;; Public api
;;

(defn add! [x]
  {:pre [(map? x)]}
  (let [id (swap! ids inc)]
    (swap! data assoc id (assoc x :id id))
    (@data id)))

(defn by-id [id]
  (@data id))

(defn all []
  (vals @data))

; delete!
; update!

(defn delete-all! []
  (reset! data {})
  (reset! ids 0))

;;
;; Sample usage
;;

(comment

  (add! {:name "kukko" :tags [:green :good]})
  (add! {:name "sandels" :tags [:yellow]}))

(all)

(delete-all!)
