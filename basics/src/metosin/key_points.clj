(ns metosin.key-points)

;;
;; Simple:
;;

(println "Hello, world!")
; prints: Hello, world!

;;
;; Functional:
;;

(filter odd? [1 2 3 4 5 6])
;=> (1 3 5)

;;
;; Statically typed:
;;

;(+ 5 "5")
; throws:
;   ClassCastException java.lang.String cannot be cast to java.lang.Number

;;
;; Java interop:
;;

(class "Greetings, earthlings")
;=> java.lang.String

(.toUpperCase "Hello, world")
;=> "HELLO, WORLD"

;;
;; Compiled to Java byte-code:
;;

(defn show-time []
  (println (System/currentTimeMillis)))

(instance? java.lang.Runnable show-time)
;=> true

(def t (new Thread show-time))
(.start t)
; prints: 1389692362338

;;
;; Dynamic:
;;

(hello "Jarppe")
; throws:
;  CompilerException java.lang.RuntimeException: Unable to resolve symbol: hello

(defn hello [user-name]
  (println "Hello," user-name))

(hello "Jarppe")
; prints: Hello, Jarppe

(defn hello [user-name]
  (println "Greetings," user-name))

(hello "Jarppe")
; prints: Greetings, Jarppe

;;
;; Interactive:
;;

+

(println (/ 84 2))

