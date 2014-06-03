(ns working-with-data.name-generator
  (:require [clojure.java.io :as io]
            [clojure.string :as s]))

;;
;; Not used in this project. This is the code I used to generate random
;; names to anonymize the participants excel. The used names are scrapped
;; from public web resources.
;;

(def first-names (->> "popular-first-names.txt"
                      (io/resource)
                      (slurp)
                      (s/split-lines)
                      (drop 1)
                      (partition 17)
                      (mapcat (fn [r] [(nth r 1) (nth r 5)]))))

(def last-names (->> "popular-last-names.txt"
                     (io/resource)
                     (slurp)
                     (s/split-lines)
                     (drop 1)
                     (map (partial re-matches #"\d+\s+(\S+)"))
                     (map second)))

(defn names []
  (cons (str (rand-nth first-names) \space (rand-nth last-names))
        (lazy-seq (names))))

(defn print-22-random-names []
  (->> (names)
       (distinct)
       (take 22)
       (map println)
       (dorun)))
