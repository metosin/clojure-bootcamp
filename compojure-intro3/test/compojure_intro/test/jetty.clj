(ns compojure-intro.test.jetty
  (:require [ring.adapter.jetty :as jetty]
            [clj-http.client :as http]
            [midje.sweet :refer :all]
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

  (defn post-json [uri body]
    (http :post uri
          {:content-type :json
           :form-params body}))

  (defn start-jetty []
    (when-not @server
      (swap! server
             (constantly
               (jetty/run-jetty (var handler/app) {:port port :join? false})))))

  (defn stop-jetty []
    (when @server
      (.stop @server)
      (swap! server (constantly nil)))))

(defmacro jetty-facts [name & body]
  `(facts ~name
          (background
            (before :contents (start-jetty))
            (after  :contents (stop-jetty)))
          ~@body))
