(ns twitter-sphere.stm)

; atom

(def a (atom "hello"))

(deref a)
@a

(swap! a (fn [old-value]
           (str old-value old-value)))
@a

(swap! a (fn [old-value]
           (str old-value old-value)))
@a

(swap! a str "!!")
@a

(def game (atom {:score 1000}))

(swap! game assoc :level 1)

; @a = (deref game)

; agent

(def agt (agent 42))

(deref agt)
@agt

(send agt + 2)
@agt

(send agt (fn [old-value]
            (Thread/sleep 10000)
            (inc old-value)))

@agt

(send-off agt (fn [old-value]
                (Thread/sleep 10000)
                (inc old-value)))

@agt

(import '[java.util.concurrent Executors])

(def my-executor (Executors/newFixedThreadPool 5))
(send-via my-executor agt inc)

@agt

; ref

(def r (ref 1337))

(deref r)
@r

(alter r + 42)
; IllegalStateException No transaction running  clojure.lang.LockingTransaction

(dosync
  (alter r + 42))
; 1379

(def acc1 (ref 0))
(def acc2 (ref 100))


(def audit-agent (agent nil))

(defn make-audit-trail [_ & messages]
  (apply println messages)
  nil)

(defn send-me-moneys [from-acc to-acc ammount]
  (dosync
    (send-off audit-agent make-audit-trail "about to take moneys" @from-acc @to-acc ammount)
    (alter from-acc - ammount)
        
    (send-off audit-agent make-audit-trail "adding moneys" @from-acc @to-acc ammount)
    (alter to-acc + ammount)

    ; (throw (ex-info "oh no" {:from from-acc :to to-acc :ammout ammount}))
    
    (send-off audit-agent make-audit-trail "money moved" @from-acc @to-acc ammount)))

(send-me-moneys acc2 acc1 50)

@acc1
@acc2

(add-watch acc2 :security-watcher
           (fn [key ref old-state new-state]
             (println "key" key)
             (println "ref" ref)
             (println "old" old-state)
             (println "new" new-state)))

(set-validator! acc1 (fn [balance]
                       (>= balance 0)))

(set-validator! acc2 (fn [balance]
                       (>= balance 0)))

(defn cell [conversion-f]
  (reify clojure.lang.IRef
    (deref [_]
      (conversion-f))))

(def acc1-c (cell
              (fn []
                (* (deref acc1) 42))))

(deref acc1-c)
@acc1-c

(dosync
  (let [acc1-value @acc1
        acc2-value @acc2]
    @acc1-c))











