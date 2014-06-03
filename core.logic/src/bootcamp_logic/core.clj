(ns bootcamp-logic.core
  (:refer-clojure :exclude [==])
  (:require [clojure.core.logic :refer :all]
            [clojure.core.logic.pldb :refer :all]))

;; Basics

;; Run* is used to find possible values for lvars (logival values)
;; q is the lvar here
(run* [q]
  (== q true))
;; => (true)

(run* [q]
  (== q 1)
  (== q 2))
;; => ()
;; q can't satisfy both constraints

(run* [q]
  ;; Constraint: q is one of following
  (membero q [1 2 3]))
;; => (1 2 3)

(run* [q x]
  (== x 2))
;; => ([_0 2])
;; _0 means q has is "first unconstrained variable"

(run* [q]
  (fresh [a]
         (membero a [42 1337 9000])
         (== q a)))
;; => (42 1337 9000)

;; core.logic.pldb

;; Define few possible relations
(db-rel interested-in name interest)
(db-rel knows name lang)
(db-rel prog-years name years)

;; Define database containing some facts using previous relations
(def facts
  (db [interested-in "Elmeri Lahti" :core-logic]
      [prog-years "Elmeri Lahti" 10.0]
      [interested-in "Amanda Salo" :core-logic]
      [prog-years "Amanda Salo" 4.0]
      [knows "Maria Laaksonen" :scheme]
      [prog-years "Maria Laaksonen" 10.0]
      [interested-in "Amanda Mäkelä" :core-logic]
      [knows "Amanda Mäkelä" :scheme]
      [knows "Amanda Mäkelä" :cljs]
      [prog-years "Amanda Mäkelä" 11.0]
      [knows "Inkeri Aalto" :scheme]
      [prog-years "Inkeri Aalto" 20.0]
      [knows "Kristian Vuorinen" :scheme]
      [prog-years "Kristian Vuorinen" 10.0]))

;; Do some queries
(with-db facts
  (run* [q]
    (knows q :scheme)))
;; => ("Inkeri Aalto" "Kristian Vuorinen" "Amanda Mäkelä" "Maria Laaksonen")

;; run* will try to find all results.
;; run will only search for as many results as you ask
(with-db facts (run 1 [q] (knows q :scheme)))
;; => ("Inkeri Aalto")

(with-db facts
  (run* [q]
    ;; q is bound to name
    ;; conde - ppl who are interested-in core-logic or know scheme
    (conde ((interested-in q :core-logic)) ((knows q :scheme)))
    ;; There is some y - year
    (fresh [y]
           ;; Which is constrained by this predicate function
           (predc y #(>= % 11))
           ;; Constrain peoples to those who have programmed y-years
           (prog-years q y))))
;; => ("Inkeri Aalto" "Amanda Mäkelä" "Amanda Mäkelä")
;; Amanda is shown twice because she knows Scheme and is insterested in core-logic
