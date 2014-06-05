(ns schema-intro.core
  (:require [schema.core :as s]
            [schema.coerce :as sc]))

(defn json-coerce [schema value]
  ((sc/coercer schema sc/json-coercion-matcher) value))

; (s/maybe)
; (s/both)
; (s/pred)
; (s/optional-key)
; (s/if)
; (s/either)
; (s/conditional)

; s/defn
; (s/with-fn-validation
; ^:always-validate
