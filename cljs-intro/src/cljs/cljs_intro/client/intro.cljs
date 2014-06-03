(ns cljs-intro.client.intro
  (:require [dommy.core :as dommy]
            [ajax.core :refer [GET POST]])
  (:require-macros [dommy.macros :refer [sel1]]
                   [cljs-intro.client.util :as u]))

(enable-console-print!)

(defn on-submit [e]
  (u/prevent-default e)
  (dommy/remove-class! (sel1 ".wait") :hidden)
  (dommy/set-text! (sel1 ".resp") "")
  (POST "/question" {:params {:question (-> ".question-form input" sel1 dommy/value)}
                     :handler (fn [resp]
                                (dommy/add-class! (sel1 ".wait") :hidden)
                                (dommy/set-text! (sel1 ".resp") (:message resp)))}))

(defn ready []
  (dommy/listen! (sel1 ".question-form button") :click on-submit))

(-> js/window .-onload (set! ready))
