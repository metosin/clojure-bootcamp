(ns compojure-intro.main
  (:gen-class))

(defn -main [& args]
  (require 'compojure-intro.server)
  (apply (resolve 'compojure-intro.server/run) args))
