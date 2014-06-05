(ns workflow.handler-test
  (:require [midje.sweet :refer :all]
            [workflow.handler :refer :all]))

#_(fact apply-fixtures
  (apply-fixtures)
  (count (get-stuff)) => 1)
