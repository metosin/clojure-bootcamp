(ns compojure-intro.server
  (:require [ring.adapter.jetty :as jetty]
            [compojure-intro.handler :as handler]))

(defn run [& args]
  (let [port (Integer/parseInt (or (first args) "8080"))]
    (jetty/run-jetty (var handler/app)
                     {:port port :join? false})))
