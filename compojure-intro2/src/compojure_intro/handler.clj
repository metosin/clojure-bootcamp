(ns compojure-intro.handler
  (:use compojure.core)
  (:require [compojure.handler :as handler]
            [compojure.route :as route]
            [ring.util.http-response :refer :all]
            [ring.middleware.format :as format]))

(defroutes app-routes
  (GET "/ping" [] (ok {:ping "pong" :now (java.util.Date.)}))
  (POST "/echo" {body :body-params} (ok body))
  (route/resources "/")
  (route/not-found "Not Found"))

(def app
  (-> app-routes
      handler/site
      (format/wrap-restful-format :formats [:json-kw])))
