(ns compojure-intro.handler
  #_(:use compojure.core)
  (:require [clojure.java.io :as io]
            ;[compojure.handler :as handler]
            [compojure.route :as route]
            [compojure-intro.db :as db]
            [compojure.api.sweet :refer :all]
            [ring.util.http-response :refer :all]
            [ring.middleware.format :as format]))

(defapi app
  (GET* "/" [] (-> "public/index.html"
                  (io/resource)
                  (slurp)
                  (ok)
                  (content-type "text/html")))
  (GET* "/ping" [] (ok {:ping "pong" :now (java.util.Date.)}))
  (POST* "/echo" {body :body-params} (ok body))

  ;; the beer api!
  (context "/api/beers" []
    (GET* "/" [] (ok (db/all)))
    (PUT* "/" {body :body-params} (db/insert! body))
    (context "/:name" [name]
      (GET* "/" [] (ok (db/get-by-name name)))
      (DELETE* "/" [] (ok (db/remove-by-name! name)))
      (POST* "/" {body :body-params} (ok (db/update-by-name! name)))))

  (route/resources "/")
  (route/not-found "Not Found"))

#_(def app
    (-> app-routes
        handler/site
        #_(format/wrap-restful-format :formats [:json-kw])))
