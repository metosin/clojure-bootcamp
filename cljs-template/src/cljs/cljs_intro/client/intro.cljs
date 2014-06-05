(ns cljs-intro.client.intro)

(defn ready []
  (.log js/console "Hello, ClojureScript!"))

(aset js/window "onload" ready)
