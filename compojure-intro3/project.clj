(defproject compojure-intro3 "0.1.0-SNAPSHOT"
  :description "Compojure sample app, part3"
  :dependencies [[org.clojure/clojure "1.6.0"]
                 #_[compojure "1.1.8"]
                 [ring-middleware-format "0.3.2"]
                 [com.novemberain/monger "2.0.0-rc1"]
                 [metosin/ring-http-response "0.4.0"]
                 [metosin/compojure-api "0.11.5"]
                 [metosin/ring-swagger-ui "2.0.16-2"]
                 [clj-http "0.7.9"]
                 [org.clojure/clojurescript "0.0-2227" :scope "provided"]
                 [org.clojure/core.async "0.1.303.0-886421-alpha"]
                 [prismatic/dommy "0.1.2" :scope "provided"]
                 [cljs-ajax "0.2.4" :scope "provided"]]
  :ring {:handler compojure-intro.handler/app
         :reload-paths ["src"]} ; don't rerun tests
  :plugins [[lein-cljsbuild "1.0.3"]]
  :profiles {:dev {:dependencies [[javax.servlet/servlet-api "2.5"]
                                  [ring/ring-jetty-adapter "1.3.0"]
                                  [midje "1.6.3"]
                                  [ring-mock "0.1.5"]]
                   :plugins [[lein-midje "3.1.3"]
                             [lein-ring "0.8.10"]]}
             :uberjar {:main compojure-intro.main
                       :aot [compojure-intro.main]
                       :hooks [leiningen.cljsbuild]
                       :cljsbuild {:builds {:client {:compiler {:optimizations :advanced
                                                                :elide-asserts true
                                                                :pretty-print false}}}}}}
  :cljsbuild {:builds {:client {:source-paths ["src" "cljs-src"]
                                :compiler {:output-to "resources/public/beers.js"
                                           :output-dir "target/js/out"
                                           :optimizations :whitespace
                                           :pretty-print true}}}}
  :uberjar-name "beers.jar"
  :min-lein-version "2.3.4")
