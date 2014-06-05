(ns cljs-core-async.main
  (:gen-class))

(defn -main [& args]
  (require 'cljs-core-async.server.server)
  (apply (resolve 'cljs-core-async.server.server/run) args)
  (println "Server running..."))
