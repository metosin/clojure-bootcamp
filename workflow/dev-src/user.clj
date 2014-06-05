(ns user
  (:require [clojure.tools.namespace.repl :as ns-tools]
            [workflow.main :as main]
            [system :refer [system]]))

(defn init []
  (swap! system (constantly {})))

(defn start []
  (swap! system main/start))

(defn stop []
  (swap! system (fn [s] (when s (main/stop s)))))

(defn go []
  (init)
  (start))

(defn reset []
  (stop)
  (ns-tools/refresh :after 'user/go))
