(ns example-test
  (:require [midje.sweet :refer :all]))

(facts
  (+ 39 3)    => 42
  (+ 1295 42) => 1337)

(facts
  (/ 42 0) => (throws ArithmeticException))
