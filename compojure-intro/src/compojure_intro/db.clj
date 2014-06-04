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
  (let [id (swap! ids inc)
        x (assoc x :id id)]
    (swap! data assoc id x)
    x))

(defn by-id [id]
  (@data id))

(defn all []
  (vals @data))

(defn delete! [id]
  (swap! data dissoc :id id)
  nil)

(defn update! [{:keys [id] :as x}]
  {:pre [id]}
  (swap! data assoc id x)
  x)

(defn delete-all! []
  (reset! data {})
  (reset! ids 0))

;;
;; Sample usage
;;

(do
  (add! {:name "kukko" :tags [:green :good]})
  (add! {:name "sandels" :tags [:yellow]})
  (delete-all!))
