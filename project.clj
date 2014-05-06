(defproject metosin/clojure-bootcamp "0.2.0-SNAPSHOT"
  :description "Metosin training: Clojure-bootcamp"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.5.1"]]
  :profiles {:dev {:dependencies [[midje "1.5.1"]]
                   :plugins [[lein-midje "3.1.1"]
                             [lein2-eclipse "2.0.0"]
                             [lein-idea "1.0.1"]]}})
