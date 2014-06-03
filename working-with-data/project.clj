(defproject working-with-data "0.1.0-SNAPSHOT"
  :description "Metosin Clojure Bootcamp - Working with data"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [org.apache.poi/poi-ooxml "3.9"]]
  :profiles {:dev {:dependencies [[midje "1.6.3"]]
                   :plugins [[lein-midje "3.1.3"]
                             [lein2-eclipse "2.0.0"]
                             [lein-idea "1.0.1"]]}}
  :repl-options {:init-ns working-with-data})
