(defproject workflow "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [metosin/compojure-api "0.11.5"]
                 [metosin/ring-http-response "0.4.0"]
                 [metosin/ring-swagger-ui "2.0.16-2"]
                 [http-kit "2.1.18"]
                 [com.novemberain/monger "2.0.0-rc1"]]
  :uberjar-name "server.jar"
  :profiles {:uberjar {:resource-paths ["swagger-ui"]}
             :dev {:source-paths ["dev-src"]
                   :dependencies [[javax.servlet/servlet-api "2.5"]
                                  [org.clojure/tools.namespace "0.2.4"]
                                  [midje "1.6.3"]]}})
