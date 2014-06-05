(ns compojure-intro.client.util)

(defmacro prevent-default [e]
  `(doto ~e
     (.preventDefault)
     (.stopPropagation)))
