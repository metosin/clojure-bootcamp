(ns compojure-intro.client.intro
  (:require [dommy.core :as dommy]
            [ajax.core :refer [GET POST]]
            [cljs.core.async :as async :refer [chan <! >!]])
  (:require-macros [dommy.macros :refer [sel1]]
                   [compojure-intro.client.util :as u]
                   [cljs.core.async.macros :refer [go go-loop alt!]]))

(enable-console-print!)

(def question-ch (chan (async/dropping-buffer 1)))
(def answer-ch (chan (async/sliding-buffer 1)))

(go-loop []
  (when-let [question (<! question-ch)]
    (POST "/question" {:params {:question question}
                       :handler (fn [{message :message}]
                                  (go (>! answer-ch message)))})
    (recur)))

(go-loop []
  (when-let [answer (<! answer-ch)]
    (-> (sel1 ".wait") (dommy/add-class! :hidden))
    (-> (sel1 ".resp") (dommy/set-text! answer))
    (recur)))

(defn click [e]
  (u/prevent-default e)
  (-> (sel1 ".wait") (dommy/remove-class! :hidden))
  (-> (sel1 ".resp") (dommy/set-text! ""))
  (let [question (-> (sel1 ".question-form input") (dommy/value))]
    (go (>! question-ch question))))

(defn ready []
  (-> (sel1 ".question-form button")
      (dommy/listen! :click click))
  (.log js/console "App ready!"))

(-> js/window .-onload (set! ready))
