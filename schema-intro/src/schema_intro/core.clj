(ns schema-intro.core
  (:require [schema.core :as s]
            [schema.coerce :as sc]))

(defn json-coerce [schema value]
  ((sc/coercer schema sc/json-coercion-matcher) value))


; s/defn


; (s/maybe)
; (s/both)
; (s/pred)
; (s/optional-key)
; (s/if)
; (s/either)
; (s/conditional)
