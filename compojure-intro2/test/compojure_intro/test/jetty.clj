(ns compojure-intro.test.jetty
  (:require [ring.adapter.jetty :as jetty]
            [clj-http.client :as http]
            [compojure-intro.handler :as handler]))

(let [server (atom nil)
      port   6789]

  (defn http [method uri & [request]]
    (http/request
      (merge request {:url (str "http://localhost:" port uri)
                      :throw-exceptions false
                      :coerce :always
                      :method method
                      :as :json})))

  (defn start-jetty []
    (when-not @server
      (swap! server
             (constantly
               (jetty/run-jetty (var handler/app) {:port port :join? false})))))

  (defn stop-jetty []
    (when @server
      (.stop @server)
      (swap! server (constantly nil)))))

