(ns cljs-core-async.client.util)

(defmacro prevent-default [e]
  `(doto ~e
     (.preventDefault)
     (.stopPropagation)))
