(ns fizzbuzz.core)

(defn div? [divider v]
  (zero? (mod v divider)))

(defn fizzbuzz-if [n]
  (if (div? 15 n)
    "fizzbuzz"
    (if (div? 3 n)
      "fizz"
      (if (div? 5 n)
        "buzz"
        n))))

(defn fizzbuzz-cond [n]
  (cond
    (div? 15 n)  "fizzbuzz"
    (div? 3 n)   "fizz"
    (div? 5 n)   "buzz"
    :else        n))

(defn fizzbuzz-condp [n]
  (condp div? n
    15  "fizzbuzz"
    3   "fizz"
    5   "buzz"
    n))

(defn fizzbuzz-case [n]
  (case (mod n 15)
    0           "fizzbuzz"
    (3 6 9 12)  "fizz"
    (5 10)      "buzz"
    n))
