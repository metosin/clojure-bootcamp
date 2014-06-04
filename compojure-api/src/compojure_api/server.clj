(ns compojure-api.server
  (:require [ring.adapter.jetty :refer [run-jetty]]
            [compojure-api.handler :refer :all]))

(defn run [& [port]]
  (let [port (Integer/parseInt (or port "8080") 10)]
    (run-jetty app {:port port}))
  "App running...")
