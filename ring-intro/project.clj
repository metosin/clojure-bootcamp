(defproject ring-intro "0.1.0-SNAPSHOT"
  :description "Sample Ring project"
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [ring "1.3.0"]]
  :profiles {:dev {:dependencies [[ring-mock "0.1.5"]]}})
