(ns ring-intro.core
  (:require [ring.adapter.jetty :as jetty]))

(defn handler [request]
  (throw (UnsupportedOperationException. "fail.")))

(defn start []
  (jetty/run-jetty (var handler) {:port 3000 :join? false}))
