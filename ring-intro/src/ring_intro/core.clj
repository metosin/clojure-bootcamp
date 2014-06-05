(ns ring-intro.core
  (:require [ring.adapter.jetty :as jetty]))

(defn handler [request]
  {:status 200
   :headers {"Content-Type" "text/html"}
   :body "dunno what to respond"})

(defn start []
  (jetty/run-jetty (var handler) {:port 3000 :join? false}))
