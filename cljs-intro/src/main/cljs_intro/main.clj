(ns misdef.main
  (:gen-class))

(defn -main [& args]
  (require 'cljs-intro.server.server)
  (apply (resolve 'cljs-intro.server.server/run) args))
