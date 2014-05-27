(ns metosin.clojure-basics-test
  (:require [midje.sweet :refer :all]
            [metosin.clojure-basics :refer :all]))

(fact
  (+ 1 2) => 3)
