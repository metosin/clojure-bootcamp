(ns twitter-sphere.play-ground
  (:require [clojure.core.async
                :as async
                :refer [chan go >!! >! <!! <!
                        alt! alts! alt!! alts!!]]))

;;
;; Warning: evaluating this ns will block your
;; repl thread. You'll need a second repl on
;; same VM and same ns where you can send
;; unblocking commands.
;;

; simple channel:
(def c (chan))

; carefull now, this will block:
(>!! c "hello, world")

; channel with a buffer for 3 messages:
(def c2 (chan 3))

(>!! c2 "Hello")
(>!! c2 "world")
(>!! c2 "!")
; this will block
(>!! c2 "?")

; channel with a buffer that will allow
; writes all the time, forgetting the old
; messages when full:
(def c3 (chan (async/sliding-buffer 3)))

(>!! c3 "hello")
(>!! c3 "world")
(>!! c3 "!")
; this will succees, but the "hello" will
; be gone:
(>!! c3 "?")

(def c4 (chan (async/dropping-buffer 3)))

; same as sliding, but will ignore new messages
; when full:
(>!! c4 "hello")
(>!! c4 "world")
(>!! c4 "!")
; this will be ignored
(>!! c4 "?")

(def c (chan))

; creates a light-weight background
; process, so main thread will not
; not block on write:
(go
  (>! c "good morning"))

; another background process that
; reads the previous message:
(go
  (println "got:" (<! c)))

; create a lot of channels:
(def channels (repeatedly 1e6 chan))

; create a one background processes, one
; for each channel:
(doseq [c channels]
  (go
    (>! c "hello")))

;;
;; select like stuff:
;;

(def c1 (chan))
(def c2 (chan))

(let [[value] (alts!! [c1 c2 (async/timeout 1e4)])]
  (println "value:" value))

(def channel-to-be-closed (chan))

; writing in alts
(let [[value ch] (alts!! [c1 [c2 "value for 2"]])]
  (println "value:" value ch))

; alt! is like alts! + case:

(def c1 (chan))
(def c2 (chan))

(alt!!
  c1               ([v] (println "stuff from c1:" v))
  [[c2 "message"]] (println "wrote stuff to c2"))

;;
;; Simple chan chaining:
;;

(def source (chan))
(def target (chan))

;; This will read a number from 'source',
;; increase it by 1, and send to to 'target':

(go
  (loop []
    (when-some [v (<! source)]
      (>! target (inc v))
      (recur))))

;; This reads from target and print
;; the results.

(go
  (loop []
    (when-some [v (<! target)]
       (println "got:" v)
       (recur))))

;; Send one number to 'source':

(>!! source 41)

;; core.async has many helpers for
;; mapping, filtering etc. on channels.
;; This till send all values from
;; a seq to channel:

(async/onto-chan source (range 10))
