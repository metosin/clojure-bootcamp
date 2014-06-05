(ns cljs-core-async.server.server
  (:require [clojure.java.io :as io]
            [org.httpkit.server :as httpkit]
            [ring.util.http-response :refer [ok content-type header] :as resp]
            [compojure.core :refer :all]
            [compojure.route :as route]
            [compojure.handler :as handler]
            [ring.middleware.format :refer [wrap-restful-format]]
            [cljs-core-async.server.env :as env]))

(defn index [_]
  (-> (with-open [in (-> "public/index.html" io/resource io/input-stream)]
        (slurp in))
      (ok)
      (content-type "text/html; charset=UTF-8")))

(defroutes app-routes
  (GET  "/" [] index)
  (POST "/question" [question] (ok {:message (str "[" question "]")}))
  (route/resources "/")
  (route/not-found "Not found"))

(def app (-> app-routes
             (handler/api)
             (wrap-restful-format :formats [:edn :json])))

(defn run [& [port]]
  (let [port (Integer/parseInt (or port "8080") 10)]
    (httpkit/run-server (if env/dev? (var app) app) {:port port, :join? false}))
  "App running...")
