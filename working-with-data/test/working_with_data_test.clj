(ns working-with-data-test
  (:require [working-with-data :refer :all]
            [midje.sweet :refer :all]))

(facts average-experience
  (average-experience [{:prog-years 5} {:prog-years 7}]) => 6
  (average-experience [{:prog-years 5} {:prog-years 7} {:prog-years nil}]) => 6)

(tabular
  (fact knows-lang
    (knows-lang ?data ?lang) => ?num)
    ?lang   ?num   ?data                        
    :foo    0      []
    :foo    1      [{:lang :foo}]
    :foo    2      [{:lang :foo} {:lang :foo}]
    :foo    1      [{:lang :foo} {:lang :bar}])
