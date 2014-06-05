(ns workflow.main
  (:require [org.httpkit.server :refer [run-server]]
            [monger.core :as mc]))

;; This would be in env module
(defn dev? []
  true)

(defn resolve-handler []
  ;; Require is done inside this function to
  ;; prevent eager compilation during AOT compilation
  ;; resolve returns a var so if you redefine it, server will see the changes
  (require 'workflow.handler)
  (resolve 'workflow.handler/app))

(defn start [system & [{:keys [db-name port]}]]
  (let [conn (mc/connect)]
    (assoc system
           :mongo conn
           :db (mc/get-db conn (or db-name "workflow"))
           :http-kit (run-server (resolve-handler) {:port (or port 3000) :join? false}))))

(defn stop [{:keys [http-kit mongo] :as system}]
  (if http-kit
    (http-kit))
  (if mongo
    (mc/disconnect mongo))
  {})
