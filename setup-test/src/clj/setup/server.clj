(ns setup.server
  (:require [clojure.java.io :as io]
            [clojure.string :as s]
            [org.httpkit.server :as httpkit]
            [ring.util.http-response :refer [ok content-type header] :as resp]
            [compojure.core :refer :all]
            [compojure.route :as route]))

(defroutes app
  (GET "/" [] (-> "public/index.html" io/resource io/input-stream ok))
  (GET "/ping" [] (ok "pong\n"))
  (route/resources "/")
  (route/not-found "Not found"))

(defn run [& args]
  (httpkit/run-server (var app) {:port 8080 :join? false})
  "App running...")
