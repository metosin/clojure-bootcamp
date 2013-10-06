(ns metosin.clojure-basics)

;;
;; Welcome to Metosin Clojure-basics training.
;;
;; This is a clojure source file. The thing at the top is a namespace declaration. It
;; says that this is namespace "metosin.clojure-basics".
;;

;; Let's start with some scalar values.

1337

3.14159

"Hello, clojure world!"

true

#"hello, (\S+)"

\F

;; What are those, exactly. Let's use function 'type' so inspect them:

(type 1337)                      ; => java.lang.Long
(type 3.14159)                   ; => java.lang.Double
(type "Hello, clojure world!")   ; => java.lang.String
(type true)                      ; => java.lang.Boolean
(type #"hello, (\S+)")           ; => java.util.regex.Pattern
(type \F)                        ; => java.lang.Character

;; Ok, so those look familiar :)

(string? "foo")                  ; => true
(number? 1337)                   ; => true
(char? \newline)                 ; => true

;; Ratios:

(/ 2 4)                          ; 1/2
(type (/ 2 4))                   ; => clojure.lang.Ratio

; Ratios are not subject to roundoff errors:

(* (/ 1 7) 7)                    ; => 1N
(* (double (/ 1 7)) 7)           ; => 1.0000000000000004

;; Some less common number formats:

; hex
0xFF                             ; => 255
0xff                             ; => 255
; oct
052                              ; => 42
; Base 2
2r1101                           ; => 13
; Base 36
36rWUT                           ; => 42581
; BigInt
12345678901234567890N            ; => 12345678901234567890N
; BigDecimal
1234567890.1234567890M           ; => 12345678901234567890N

;; What can we do with them? Let's call some functions and pass values as arguments.

(+ 1 2)                          ; => 3
(* 3 4)                          ; => 12

;; Function + can take any nmber of arguments:

(+ 1 2 3)                        ; => 6

;; Function <

(< 4 5)                          ; => true
(< 4 5 6)                        ; => true
(< 4 7 6)                        ; => false

;; Function =

(= 1)                            ; => true
(= 1 1)                          ; => true
(= 1 1 1)                        ; => true
(= 1 1 1 2)                      ; => false

;; They nest too:

(* 3 (+ 1 2))                    ; => 9

;; We aready saw function 'type', another usefull function to use
;; when we are learing clojure is 'instance?':

(instance? java.lang.String "foo")     ; => true
(instance? java.lang.String 42)        ; => false

;;
;; Collections: Vector
;;

(type [1 2 3])                           ; => clojure.lang.PersistentVector
(instance? java.util.Vector [1 2 3])     ; => false  (huh, that was a releaf, eh?)
(instance? java.util.List [1 2 3])       ; => true
(instance? java.lang.Iterable [1 2 3])   ; => true
(vector? [1 2 3])                        ; => true
(vector? [])                             ; => true
(count [1 2 3])                          ; => 3
(count [])                               ; => 0

;; Vectors can be created programmatically too:

(vector 1 2 3)                           ; => [1 2 3]

;; So Clojure vectors implement java.util.List. Let's try to call java.util.List.add():

(try
  (.add [1 2 3] 4)
  (println "were in trouble")
  (catch UnsupportedOperationException e
    (println "WIN, vectors are immutable!")))
; prints "WIN, vectors are immutable!"

;; Let's play with vectors:

(def v1 [1 2])
v1                     ; => [1 2]
(conj v1 3)            ; => [1 2 3]
v1                     ; => [1 2]
(nth v1 0)             ; => 1
(nth v1 1)             ; => 2
; (nth v1 2)           ; throws IndexOutOfBoundsException

;; Note: Vecors grow naturally at the end (conj adds to the end) and that getting
;; values with nth is log32N (read 'fast').

;; Vectors are also functions for their indices:

(v1 0)                 ; => 1
(v1 1)                 ; => 2
; (v1 2)               ; throws IndexOutOfBoundsException

;;
;; Collections: List
;;

;; Very similar to vectors. Lists are mostly used to invoke functions, like:

(+ 1 2)                     ; => 3

;; To stop clojure from evaluating list as a function call, use quote:

(quote (+ 1 2))             ; => (+ 1 2)

;; The above evaluates to a list with three elements, first is a symbol +, second
;; is number 1 and third is number 2.

;; Shorthand for quote:

'(+ 1 2)                    ; => (+ 1 2)

;;
;; Lists grow 'naturally' from head:
;;

(conj [1 2] 3)              ; => [1 2 3]
(conj '(1 2) 3)             ; => (3 1 2)

;; cons (short from construct) creates a new cons cell, i.e. linked list node:

(cons 3 nil)                     ; => (3)
(cons 2 (cons 3 nil))            ; => (2 3)
(cons 1 (cons 2 (cons 3 nil)))   ; => (1 2 3)

;; Getting first and rest from list is O(1)

(first '(1 2 3))            ; => 1
(rest '(1 2 3))             ; => (2 3)

;; Programmaticaly:

(list 1 2 3)                ; => (1 2 3)

;;
;; Collections: Set
;;

#{1 2 3}                             ; => #{1 2 3}

(type #{1 2 3})                      ; => clojure.lang.PersistentHashSet
(instance? java.util.Set #{1 2 3})   ; => true

(conj #{1 2} 3)                      ; => #{1 2 3}
(disj #{1 2 3} 2)                    ; => #{1 3}
(contains? #{1 2} 2)                 ; => true
(contains? #{1 2} 3)                 ; => false

;; Sets are also functions:

(#{1 2 3} 2)                         ; => 2
(#{1 2 3} 4)                         ; => nil

;; Programmaticaly:

(set [1 2 3])                        ; => #{1 2 3}
(hash-set 1 2 3)                     ; => #{1 2 3}

;;
;; Collections: Map
;;

(type {"a" "b"})                           ; => clojure.lang.PersistentArrayMap
(instance? java.util.Map {"a" "b"})        ; => true

;; Getting values by key:

(get {"a" 1, "b" 2} "a")                     ; => 1
(get {"a" 1, "b" 2} "b")                     ; => 2
(get {"a" 1, "b" 2} "c")                     ; => nil
(get {"a" 1, "b" 2} "c" "wut?")              ; => "wut?"

(contains? {"foo" "bar"} "foo")             ; => true
(contains? {"foo" "bar"} "x")               ; => false

(assoc {"a" 1} "b" 2)                       ; => {"a" 1, "b" 2}
(dissoc {"a" 1, "b" 2} "b")                  ; => {"a" 1}

;; Maps are also functions:

({"a" 1, "b" 2} "a")                         ; => 1
({"a" 1, "b" 2} "c")                         ; => nil

;; Programmaticaly:

(hash-map "a" 1 "b" 2)                      ; => {"a" 1, "b" 2}

;; Note that in Clojure (like in most Lisp's) the comma is just white space:

(= {"a" 1 "b" 2}  {"a" 1, "b" 2})           ; => true
(= {"a" 1 "b" 2}  {,,"a", 1 "b", ,, ,,, 2}) ; => true

(keys {"a" 1 "b" 2})                        ; => ("a" "b")
(vals {"a" 1 "b" 2})                        ; => (1 2)

(merge {"a" 1 "b" 2} {"b" 3 "c" 4})         ; => {"a" 1, "b" 3, "c" 4}

;; Common to nest:

(def company {"name"      "metosin"
              "twitter"   "@metosin"
              "address"   {"street" "Hameenkatu 2"
                           "city"   "Tampesteri"}})

(get company "name")                        ; => "metosin"
(get company "address")                     ; => {"street" "Hameenkatu 2", "city"   "Tampesteri"}
(get (get company "address") "street")      ; => "Hameenkatu 2"
(get-in company ["address" "street"])       ; => "Hameenkatu 2"

(assoc-in company ["address" "zip"] "33100") 
; => {"name" "metosin",
;     "twitter" "@metosin",
;     "address" {"street" "Hameenkatu 2",
;                "city" "Tampesteri",
;                "zip" "33100"}}

;;
;; Collections: Seq
;;

; Like java.util.Iterator

(seq? '(1 2 3))                             ; => true
(seq? [1 2 3])                              ; => false

; Function seq can be used to turn almost anything into a seq:

(seq [1 2 3])                               ; => (1 2 3)
(seq "hello")                               ; => (\h \e \l \l \o)
(seq #{"a" "b" "c"})                        ; => ("a" "b" "c")
(seq {"a" 1 "b" 2})                         ; => (["a" 1] ["b" 2])

; ...with one notable exception: empty list

(seq '())                                   ; => nil

; That's actually an idiomatic way to test for empty list

;;
;; Keywords:
;;

; Interned strings. Comparing two strings for equality requires traversing
; the strings, but keywords are interned, so equality can be done by comparing
; memory address.
;
; From clojure.lang.Keyword:
;
;   public boolean equals(Object obj) {
;       return (this == obj);
;   }

(type (keyword "foo"))                     ; => clojure.lang.Keyword
(type :foo)                                ; => clojure.lang.Keyword

(keyword? "foo")                           ; => false
(keyword? :foo)                            ; => true

; Very commonly used as a keys in maps:

(def company {:name     "metosin"
              :twitter  "@metosin"
              :address  {:street "Hameenkatu 2"
                         :city   "Tampesteri"}})

(get-in company [:address :street])        ; => "Hameenkatu 2"

;; Like sets and maps, keywords are functions too:

(:twitter company)                         ; => "@metosin"
(:foo company)                             ; => nil
(:foo company "wut?")                      ; => "wut?"

;;
;; Functions:
;;

(fn [your-name]
  (println "Hello," your-name))

(def greeter (fn [your-name]
               (println "Hello," your-name)))

(greeter "world")
; prints: "Hello, world"

(defn greeter [your-name]
  (println "Greetings," your-name))

(greeter "world")
; prints: "Greetings, world"

;; Last thing function evaluates is the retusn value. In above, last thing
;; greeter evaluates is function call to println, so what ever println returns
;; will be return value of greeter. Since println always returns nil, greeter
;; returns nil. In Clojure lingo, greeter evaluates to nil.

(greeter "world")                          ; => nil

(defn greeter [your-name]
  (println "Greetings," your-name)
  your-name)

(greeter "world")                          ; => "world"

;;
;; Lexical bindings: let
;;

(defn greeter [your-name]
  (let [message (str "Greetings, " your-name)]
    (println message)
    message))

(greeter "world")                          ; => "Greetings, world"

;;
;; Let's play with functions and collections:
;;


(def programmers [{:name "Lisa"
                   :langs [:clojure :java]
                   :experience 8}
                  {:name "Kevin"
                   :langs [:javascript :perl :c#]
                   :experience 6}
                  {:name "Agatha"
                   :langs [:clojure :prolog :c++]
                   :experience 12}])

; Find names of programmers:

(defn name-of-programmer [programmer]
  (get programmer :name))

(map name-of-programmer programmers)                      ; => ("Lisa" "Kevin" "Agatha")

; Since keywords are functions:

(map :name programmers)                                   ; => ("Lisa" "Kevin" "Agatha")

; What languages we have

(map :langs programmers)                                  ; => ([:clojure :java] [:javascript :perl :c#] [:clojure :prolog :c++])
(flatten (map :langs programmers))                        ; => (:clojure :java :javascript :perl :c# :clojure :prolog :c++)
(set (flatten (map :langs programmers)))                  ; => #{:clojure :javascript :c++ :perl :c# :prolog :java}

; Check if some of our programmers knows given language:

(contains? (set (flatten (map :langs programmers))) :clojure)   ; => true
(contains? (set (flatten (map :langs programmers))) :haskell)   ; => false

#_(->> programmers
  (map :langs)
  (flatten)
  (set)
  (contains? ))

; Example: ->>

; Threads the expr through the forms. Inserts x as the
; last item in the first form, making a list of it if it is not a
; list already. If there are more forms, inserts the first form as the
; last item in second form, etc.

; require

(even? 1)                                                 ; => false
(even? 2)                                                 ; => true

(defn triple-me [x] (* 3 x))
(map triple-me [1 2 3 4 5 6])                             ; => (2 4 6 8 10 12)

(filter even? (map triple-me [1 2 3 4 5 6]))              ; => (6 12 18)

(defn half [x] (/ x 2))

(map half (filter even? (map triple-me [1 2 3 4 5 6])))   ; => (3 6 9)

;;
;; Macros:
;;

;;
;; Java inter-op:
;;

;; dot special form makes a Java method invocation. First arg is the object, second is
;; the method name, rest are arguments to method.

(. "Hello, world" substring 7)   ; => "world"

;; Same, but more commonly used:

(.substring "Hello, world" 7)    ; => "world"

