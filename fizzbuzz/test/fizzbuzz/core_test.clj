(ns fizzbuzz.core-test
  (:require [midje.sweet :refer :all]
            [fizzbuzz.core :refer :all]
            [clojure.string :as s]))

(facts
  (div? 3 2) => false
  (div? 3 3) => true
  (div? 3 4) => false
  (div? 3 6) => true)

(def values (range 1 17))
(def correct [1 2 "fizz" 4 "buzz" "fizz" 7 8 "fizz" "buzz" 11 "fizz" 13 14 "fizzbuzz" 16])

(facts fizzbuzzs
  (map fizzbuzz-case values)  => correct
  (map fizzbuzz-if values)    => correct
  (map fizzbuzz-cond values)  => correct
  (map fizzbuzz-condp values) => correct)
