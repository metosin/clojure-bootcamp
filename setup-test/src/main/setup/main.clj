(ns setup.main
  (:gen-class))

(defn -main [& args]
  (require 'setup.server)
  (apply (resolve 'setup.server/run) args)
  (println "Setup application running..."))
