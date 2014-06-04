(defproject compojure-api "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [metosin/compojure-api "0.11.5"]
                 [metosin/ring-http-response "0.4.0"]
                 [metosin/ring-swagger-ui "2.0.16-2"]
                 [ring/ring-jetty-adapter "1.3.0"]]
  :ring {:handler compojure-api.handler/app}
  :main compojure-api.main
  :uberjar-name "server.jar"
  :min-lein-version "2.0.0"
  :profiles {:uberjar {:resource-paths ["swagger-ui"]}
             :dev {:dependencies [[javax.servlet/servlet-api "2.5"]]
                   :plugins [[lein-ring "0.8.10"]]}})
