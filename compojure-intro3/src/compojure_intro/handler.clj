(ns compojure-intro.handler
  (:use compojure.core)
  (:require [compojure.handler :as handler]
            [compojure.route :as route]
            [compojure-intro.db :as db]
            [ring.util.http-response :refer :all]
            [ring.middleware.format :as format]))

(defroutes app-routes
  (GET "/ping" [] (ok {:ping "pong" :now (java.util.Date.)}))
  (POST "/echo" {body :body-params} (ok body))

  ;; the beer api!
  (context "/api/beers" []
    (GET "/" [] (ok (db/all)))
    (PUT "/" {body :body-params} (db/insert! body))
    (context "/:name" [name]
      (GET "/" [] (ok (db/get-by-name name)))
      (DELETE "/" [] (ok (db/remove-by-name! name)))
      (POST "/" {body :body-params} (ok (db/update-by-name! name)))))

  (route/resources "/")
  (route/not-found "Not Found"))

(def app
  (-> app-routes
      handler/site
      (format/wrap-restful-format :formats [:json-kw])))
