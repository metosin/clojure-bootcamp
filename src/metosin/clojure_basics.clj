(ns metosin.clojure-basics)

;;
;; Welcome to Metosin Clojure-basics training.
;;
;; This is a clojure source file. The thing at the top is
;; a namespace declaration. It says that this is namespace
;; "metosin.clojure-basics".
;;

;; Let's start with some scalar values.

1337
3.14159
"Hello, clojure world!"
true
#"hello, (\S+)"
\F

;; What are those, exactly. Let's use function 'type' to
;; inspect them:

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

;; What can we do with them? Let's call some functions and
;; pass values as arguments.

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
;; Java inter-op:
;;

;; dot special form makes a Java method invocation. First arg
;; is the object, second is the method name, rest are arguments
;; to method.

(. "Hello, world" substring 7)           ; => "world"

;; Same, but more commonly used:

(.substring "Hello, world" 7)            ; => "world"

;; new

(new java.math.BigInteger "1234567890")  ; => 1234567890

;; Same, but more commonly used:

(java.math.BigInteger. "1234567890")     ; => 1234567890

;;
;; try/throw/catch/finally:
;;

(try
  (throw (RuntimeException. "oh noes!"))
  (catch Exception e
    (println "Sorry:" (.getMessage e)))
  (finally
    (println "we are done")))

; prints:
;   Sorry: oh noes!
;   we are done

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

;; So Clojure vectors implement java.util.List. Let's try to
;; call java.util.List.add():

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

;; Note: Vecors grow naturally at the end (conj
;; adds to the end) and that getting values with
;; nth is log32N (read 'fast').

;; Vectors are also functions for their indices:

(v1 0)                 ; => 1
(v1 1)                 ; => 2
; (v1 2)               ; throws IndexOutOfBoundsException

;;
;; Collections: List
;;

;; Very similar to vectors. Lists are mostly used to invoke
;; functions, like:

(+ 1 2)                     ; => 3

;; To stop clojure from evaluating list as a function
;; call, use quote:

(quote (+ 1 2))             ; => (+ 1 2)

;; The above evaluates to a list with three elements,
;; first is a symbol +, second is number 1 and third
;; is number 2.

;; Shorthand for quote:

'(+ 1 2)                    ; => (+ 1 2)

;;
;; Lists grow 'naturally' from head:
;;

(conj [1 2] 3)              ; => [1 2 3]
(conj '(1 2) 3)             ; => (3 1 2)

;; cons (short from construct) creates a new cons cell,
; i.e. linked list node:

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

(type {"a" "b"})                     ; => clojure.lang.PersistentArrayMap
(instance? java.util.Map {"a" "b"})  ; => true

;; Getting values by key:

(get {"a" 1, "b" 2} "a")                    ; => 1
(get {"a" 1, "b" 2} "b")                    ; => 2
(get {"a" 1, "b" 2} "c")                    ; => nil
(get {"a" 1, "b" 2} "c" "wut?")             ; => "wut?"

(contains? {"foo" "bar"} "foo")             ; => true
(contains? {"foo" "bar"} "x")               ; => false

(assoc {"a" 1} "b" 2)                       ; => {"a" 1, "b" 2}
(dissoc {"a" 1, "b" 2} "b")                 ; => {"a" 1}

;; Maps are also functions:

({"a" 1, "b" 2} "a")                        ; => 1
({"a" 1, "b" 2} "c")                        ; => nil

;; Programmaticaly:

(hash-map "a" 1 "b" 2)                      ; => {"a" 1, "b" 2}

;; Note that in Clojure (like in most Lisp's) the comma is
;; just white space:

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

(get company "name")                   ; => "metosin"
(get company "address")                ; => {"street" "Hameenkatu 2", "city"   "Tampesteri"}
(get (get company "address") "street") ; => "Hameenkatu 2"
(get-in company ["address" "street"])  ; => "Hameenkatu 2"

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

; Interned strings. Comparing two strings for equality requires
; traversing the strings, but keywords are interned, so equality
; can be done by comparing memory address.
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

;; Last thing function evaluates is the retusn value. In
;; above, last thing greeter evaluates is function call
;; to println, so what ever println returns will be return
;; value of greeter. Since println always returns nil,
;; greeter returns nil. In Clojure lingo, greeter evaluates
;; to nil.

(greeter "world")                          ; => nil

(defn greeter [your-name]
  (println "Greetings," your-name)
  your-name)

(greeter "world")                          ; => "world"

;;
;; Lexical bindings: let
;;

(let [a 22
      b (* a 2)
      c (- b 2)]
  c)

=> 42

(defn greeter [your-name]
  (let [message (str "Greetings, " your-name)]
    (println message)
    message))

(greeter "world")                          ; => "Greetings, world"

;;
;; Let's play with functions and collections:
;;

; Here's some data:

(def programmers [{:name "Lisa"
                   :experience {:clojure  2
                                :java     7}}
                  {:name "Kevin"
                   :experience {:javascript  4
                                :perl        3
                                :c#          5}}
                  {:name "Agatha"
                   :experience {:clojure  3
                                :prolog   3
                                :java     5
                                :c++      6}}])

; Find names of all programmers:

(defn name-of-programmer [programmer]
  (get programmer :name))

(name-of-programmer (first programmers))                  ; => "Lisa"

; map: apply given function to each elemenent:

(map name-of-programmer programmers)                      ; => ("Lisa" "Kevin" "Agatha")

; But keywords are functions:

(:name (first programmers))                               ; => "Lisa"
(map :name programmers)                                   ; => ("Lisa" "Kevin" "Agatha")

;
; How much experience our programmers have on specific language:
;

(defn find-experience [lang]
  (map
    (fn [programmer] {:name (:name programmer)
                      :exp (get-in programmer [:experience lang] 0)})
    programmers))

(find-experience :clojure)         ; => ({:name "Lisa", :exp 2} {:name "Kevin", :exp 0} {:name "Agatha", :exp 3})
(find-experience :java)            ; => ({:name "Lisa", :exp 7} {:name "Kevin", :exp 0} {:name "Agatha", :exp 5})

;
; How much experience we have in total:
;

; reduce:

(reduce + 0 [1 2 3])                               ; => 6
; -> same as: (+ (+ (+ 0 1) 2) 3)

; With two arguments:

(reduce + [1 2 3])                                 ; => 6
; -> same as: (+ (+ 1 2) 3)

(find-experience :clojure)                         ; => ({:name "Lisa", :exp 2} {:name "Kevin", :exp 0} {:name "Agatha", :exp 3})
(map :exp (find-experience :clojure))              ; => (2 0 3)
(reduce + (map :exp (find-experience :clojure)))   ; => 5

(defn total-experience [lang]
  (reduce + (map :exp (find-experience lang))))

(total-experience :clojure)                        ; => 5
(total-experience :perl)                           ; => 3

; Total experience by language:

(map :experience programmers)                      ; => ({:clojure 2, :java 7} {:javascript 4, :perl 3, :c# 5} {:clojure 3, :c++ 6, :prolog 3, :java 5})
(map seq (map :experience programmers))            ; => (([:clojure 2] [:java 7]) ([:javascript 4] [:perl 3] [:c# 5]) ([:clojure 3] [:c++ 6] [:prolog 3] [:java 5]))
(flatten (map seq (map :experience programmers)))  ; => (:clojure 2 :java 7 :javascript 4 :perl 3 :c# 5 :clojure 3 :c++ 6 :prolog 3 :java 5)
(partition 2 (flatten (map seq (map :experience programmers))))  ; => ((:clojure 2) (:java 7) (:javascript 4) (:perl 3) (:c# 5) (:clojure 3) (:c++ 6) (:prolog 3) (:java 5))

(defn add-exp [acc element]
  (let [lang (first element)
        exp  (second element)]
    (assoc acc lang (+ (get acc lang 0) exp))))

(reduce add-exp {} (partition 2 (flatten (map seq (map :experience programmers)))))
; => {:prolog 3, :c++ 6, :c# 5, :perl 3, :javascript 4, :java 12, :clojure 5}

; pro tip: there's a function for it
(apply merge-with + (map :experience programmers))
; => {:clojure 5, :javascript 4, :c++ 6, :perl 3, :c# 5, :prolog 3, :java 12}

; common in clojure:
(partition 2 (flatten (map seq (map :experience programmers))))

; Joda says: hard to read is
; Time for macro: -> and ->> converts Joda talk to human talk

; -> chains on second position:

(.replaceAll (.substring (.toLowerCase "HELLO WORLD") 6) "w" "W")         ; => "World"

(-> "HELLO WORLD" (.toLowerCase) (.substring 6) (.replaceAll "w" "W"))    ; => "World"

; ->> chains on last position:

(partition 2 (flatten (map seq (map :experience programmers))))
; => ((:clojure 2) (:java 7) (:javascript 4) (:perl 3) (:c# 5) (:clojure 3) (:c++ 6) (:prolog 3) (:java 5))

(->> programmers (map :experience) (map seq) (flatten) (partition 2))
; => ((:clojure 2) (:java 7) (:javascript 4) (:perl 3) (:c# 5) (:clojure 3) (:c++ 6) (:prolog 3) (:java 5))

;;
;; virtues and perils of being lazy
;;

(def r (map (fn [x] (println "x=" x) (* 2 x)) [0 1 2 3 4 5 6]))
; prints nothing?

(nth r 0)
; prints:
; x= 0
; x= 1
; x= 2
; x= 3
; x= 4
; x= 5
; x= 6
; => 0

(nth r 1)
; => 2

; When you need to execute something for side-effects over a seq, use:
;  dorun
;  doall
;  doseq

; In general, when you see 'do', you know some side-effects gonna happen:

(doseq [n (range 7)]
  (println "n=" n))

; prints:
; n= 0
; n= 1
; n= 2
; n= 3
; n= 4
; n= 5
; n= 6

;;
;; Control structures:
;;

(if true (println "yes") (println "no"))
; prints: yes
; => nil

(println (if true "yes" "no"))
; prints: yes
; => nil

;;
;; truthy:
;;

(println (if true     "yes" "no"))   ; prints "yes"
(println (if 42       "yes" "no"))   ; prints "yes"
(println (if :foo     "yes" "no"))   ; prints "yes"
(println (if []       "yes" "no"))   ; prints "yes"
(println (if println  "yes" "no"))   ; prints "yes"

; in fact, everything is truthy, except false and nil:

(println (if false     "yes" "no"))   ; prints "no"
(println (if nil       "yes" "no"))   ; prints "no"

; when

(when true
  (print "this ")
  (print "and ")
  (println "that"))

; prints: this and that

(cond
  false      (println "not this")
  (even? 1)  (println "or this")
  (odd? 1)   (println "this is it")
  true       (println "gone already"))

; prints: this is it
