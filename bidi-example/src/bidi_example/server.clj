(ns bidi-example.server
  (:require [clojure.java.io :as io]
            [clojure.pprint :refer [pprint]]
            [org.httpkit.server :as httpkit]
            [ring.util.http-response :refer [ok content-type header] :as resp]
            [ring.middleware.format :refer [wrap-restful-format]]
            [bidi.bidi :as bidi]))

(defn serve-index [_]
  (-> "<html><h1>Hello</h1></html>"
      (ok)
      (content-type "text/html; charset=UTF-8")))

(defn serve-ping [req]
  (ok {:id (-> req :route-params :name)}))

(def routes ["/" {""              {:get serve-index}
                  ["ping/" :name] {:get serve-ping}}])

(def app (-> routes
             (bidi/make-handler)
             (wrap-restful-format :formats [:edn :json])))

(defn run [& [port]]
  (let [port (Integer/parseInt (or port "8080") 10)]
    (httpkit/run-server (var app) {:port port, :join? false})
    (println (format "App running at port %d..." port))))
