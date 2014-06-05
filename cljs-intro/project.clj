(defproject cljs-intro "0.1.0-SNAPSHOT"
  :description "Metosin Clojure Bootcamp - CLJS intro"
  :license {:name "Eclipse Public License" :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [org.clojure/clojurescript "0.0-2227" :scope "provided"]
                 [org.clojure/core.async "0.1.303.0-886421-alpha"]
                 [http-kit "2.1.18"]
                 [ring/ring-core "1.3.0"]
                 [ring/ring-devel "1.3.0"]
                 [compojure "1.1.8"]
                 [hiccup "1.0.5"]
                 [metosin/ring-http-response "0.4.0"]
                 [ring-middleware-format "0.3.2" :exclusions [ring]]
                 [javax.servlet/servlet-api "2.5"]
                 [prismatic/dommy "0.1.2" :scope "provided"]
                 [cljs-ajax "0.2.4" :scope "provided"]]
  :source-paths ["src/clj" "src/cljs"]
  :profiles {:dev {:dependencies [[midje "1.6.3"]]
                   ; :cljsbuild {:builds {:client {:notify-command ["growlnotify" "-n" "cljsbuild" "-m"]}}}
                   :plugins [[lein-cljsbuild "1.0.3"]]}
             :uberjar {:source-paths ["src/clj" "src/main"]
                       :main cljs-intro.main
                       :aot [cljs-intro.main]
                       :hooks [leiningen.cljsbuild]
                       :cljsbuild {:builds {:client {:compiler {:optimizations :advanced
                                                                :elide-asserts true
                                                                :pretty-print false}}}}}}
  :cljsbuild {:builds {:client {:source-paths ["src/cljs"]
                                :compiler {:output-to "resources/public/intro.js"
                                           :output-dir "target/js/out"
                                           :optimizations :whitespace
                                           :pretty-print true}}}}
  :repl-options {:init-ns cljs-intro.server.server}
  :uberjar-name "cljs-intro.jar"
  :min-lein-version "2.3.4")
