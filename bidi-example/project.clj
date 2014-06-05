(defproject bidi-example "0.1.0-SNAPSHOT"
  :description "Metosin Clojure Bootcamp - bidi example"
  :license {:name "Eclipse Public License" :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [http-kit "2.1.18"]
                 [ring/ring-core "1.3.0"]
                 [bidi "1.10.2"]
                 [metosin/ring-http-response "0.4.0"]
                 [ring-middleware-format "0.3.2" :exclusions [ring]]]
  :profiles {:uberjar {:main bidi-example.main
                       :aot [bidi-example.main]}}
  :repl-options {:init-ns bidi-example.server}
  :uberjar-name "bidi-example.jar"
  :min-lein-version "2.3.4")
