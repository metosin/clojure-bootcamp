(defproject compojure-intro "0.1.0-SNAPSHOT"
  :description "Compojure sample app"
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [compojure "1.1.8"]]
  :plugins [[lein-ring "0.8.10"]]
  :ring {:handler compojure-intro.handler/app}
  :profiles {:dev {:dependencies [[javax.servlet/servlet-api "2.5"]
                                  [ring-mock "0.1.5"]]}})
