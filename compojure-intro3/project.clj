(defproject compojure-intro3 "0.1.0-SNAPSHOT"
  :description "Compojure sample app, part3"
  :dependencies [[org.clojure/clojure "1.6.0"]
                 #_[compojure "1.1.8"]
                 [ring-middleware-format "0.3.2"]
                 [com.novemberain/monger "2.0.0-rc1"]
                 [metosin/ring-http-response "0.4.0"]
                 [metosin/compojure-api "0.11.5"]
                 [metosin/ring-swagger-ui "2.0.16-2"]
                 [clj-http "0.7.9"]]
  :ring {:handler compojure-intro.handler/app
         :reload-paths ["src"]} ; don't rerun tests
  :profiles {:dev {:dependencies [[javax.servlet/servlet-api "2.5"]
                                  [ring/ring-jetty-adapter "1.3.0"]
                                  [midje "1.6.3"]
                                  [ring-mock "0.1.5"]]
                   :plugins [[lein-midje "3.1.3"]
                             [lein-ring "0.8.10"]]}})
