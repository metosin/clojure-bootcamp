(ns compojure-api.main
  (:require [ring.adapter.jetty :refer [run-jetty]]
            [compojure-api.handler :refer :all])
  (:gen-class))

(defn -main
  "Simple Jetty main for Heroku"
  [& [port]]
  (run-jetty app {:port (Integer. (or port 8080))}))
