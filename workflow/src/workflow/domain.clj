(ns workflow.domain
  (:require [monger.core :as mg]
            [monger.collection :as mc]
            [ring.swagger.schema :refer :all]
            [workflow.util :refer :all]
            [system :refer [get-system]]))

(defn db []
  (:db (get-system)))

(defn conn []
  (:mongo (get-system)))

(defn apply-fixtures []
  (mg/drop-db (conn) "workflow")
  (mc/insert (db) :stuff {:_id (create-id) :name "juho"}))

#_(apply-fixtures)

(defn get-stuff []
  (mc/find-maps (db) :stuff))

(defmodel Result {:result Long})

