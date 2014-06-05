(ns workflow.util
  (:import [org.bson.types ObjectId]))

(defn create-id []
  (str (ObjectId.)))

(defn ->id [in]
  (ObjectId. in))
