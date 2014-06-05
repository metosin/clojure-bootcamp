(ns cljs-core-async.server.env)

(def dev?  (nil? (System/getenv "DYNO")))
(def prod? (not dev?))

