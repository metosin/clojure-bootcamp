(ns compojure-api.main
  (:gen-class))

(defn -main [& args]
  (require 'compojure-api.server)
  (apply (resolve 'compojure-api.server/run) args)
  (println "Server running..."))
