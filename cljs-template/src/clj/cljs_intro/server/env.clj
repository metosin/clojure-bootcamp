(ns cljs-intro.server.env)

(def dev?  (nil? (System/getenv "DYNO")))
(def prod? (not dev?))

