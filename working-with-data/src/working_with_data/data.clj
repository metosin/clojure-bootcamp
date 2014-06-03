(ns working-with-data.data
  (:require [clojure.java.io :as io])
  (:import [org.apache.poi.ss.usermodel Sheet Row Cell]
           [org.apache.poi.xssf.usermodel XSSFWorkbook]))

;;
;; Provides a data set we can work with. Data is read from excel
;; that contains participant names (anonymized) and some participant
;; info.
;;

;
; Generic Excel stuff:
;

(defn load-excel [resource-name]
  (with-open [in (-> resource-name
                     (io/resource)
                     (io/input-stream))]
    (doto (XSSFWorkbook. in)
      (.setMissingCellPolicy Row/RETURN_BLANK_AS_NULL))))

(defn sheet [sheet-name wb]
  (.getSheet wb sheet-name))

(defn cells [row]
  (for [col (range (.getLastCellNum row))]
    (.getCell row col)))

(defn cell-value [cell]
  (if cell
    (condp = (.getCellType cell)
      Cell/CELL_TYPE_STRING   (.getStringCellValue cell)
      Cell/CELL_TYPE_NUMERIC  (.getNumericCellValue cell)
      Cell/CELL_TYPE_BOOLEAN  (.getBooleanCellValue cell)
      Cell/CELL_TYPE_BLANK    nil
      nil)))

;;
;; Load participants data:
;;

(def language-columns [:scala
                       :js
                       :haskel
                       :prolog
                       :ruby
                       :mathematica	 
                       :scheme	 
                       :c#	 
                       :python	 
                       :groovy])

(def advanced-topic-columns [:core-async
                             :core-logic
                             :core-typed
                             :cljs
                             :web
                             :frameworks
                             :tooling])

(def x? {"X" true})

(def columns (concat [[:name identity]
                      [:prog-years identity]
                      [:clojure {"None" :none, "Basics" :basic, "Some" :some}]]
                     (map (fn [l] [l x?]) language-columns)
                     nil
                     (map (fn [l] [l x?]) advanced-topic-columns)))

(defn row->participant [row]
  (->> row
       (cells)
       (map cell-value)
       (map vector columns)
       (reduce (fn [info [[col-name normalizer] value]]
                 (if col-name
                   (assoc info col-name (normalizer value))
                   info))
               {})))

(def participants (->> (load-excel "participants.xlsx")
                       (sheet "participants")
                       (seq)
                       (drop 1)
                       (map row->participant)
                       (take-while (comp not nil? :name))))
