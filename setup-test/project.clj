(defproject setup-test "0.1.0-SNAPSHOT"
  :description "Test training setup, depend on half of the internet to populate local .m2 caches"
  :license {:name "Eclipse Public License" :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [prismatic/schema "0.2.2"]
                 [http-kit "2.1.18"]
                 [ring/ring-core "1.2.2"]
                 [ring/ring-devel "1.2.2"]
                 [compojure "1.1.8"]
                 [metosin/ring-http-response "0.4.0"]
                 [org.clojure/clojurescript "0.0-2227" :scope "provided"]
                 ; pull these in users local .m2 repo before training
                 [cheshire "5.3.1" :scope "provided"]
                 [com.novemberain/monger "1.8.0" :scope "provided"]
                 [slingshot "0.10.3" :scope "provided"]
                 [clj-http "0.9.1" :scope "provided"]
                 [enlive "1.1.5" :scope "provided"]
                 [hiccup "1.0.5" :scope "provided"]
                 [clj-time "0.7.0" :scope "provided"]
                 [camel-snake-kebab "0.1.5" :scope "provided"]
                 [org.clojure/core.async "0.1.303.0-886421-alpha" :scope "provided"]
                 [garden "1.1.7" :scope "provided"]
                 [prismatic/dommy "0.1.2" :scope "provided"]]
  :source-paths ["src/clj" "src/cljs"]
  :profiles {:dev {:dependencies [[midje "1.6.3"]]
                   :plugins [[lein-ring "0.8.10"]
                             [lein-cljsbuild "1.0.3"]]}
             :uberjar {:hooks [leiningen.cljsbuild]
                       :source-paths ["src/clj" "src/main"]
                       :main setup.main
                       :aot [setup.main]
                       :cljsbuild {:builds {:client {:compiler {:optimizations :advanced
                                                                :elide-asserts true
                                                                :pretty-print false}}
                                            ; uncomment this if you're on mac and have growlnotify installed
                                            ; :notify-command ["growlnotify" "-n" "cljsbuild" "-m"]
                                            }}}}
  :cljsbuild {:builds {:client {:source-paths ["src/cljs"]
                                :compiler {:output-to "resources/public/setup.js"
                                           :output-dir "target/js/out"
                                           :optimizations :whitespace
                                           :pretty-print true}}}}
  :repl-options {:init-ns setup.server}
  :ring {:handler setup.server/app}
  :uberjar-name "setup.jar"
  :min-lein-version "2.3.4")
