(ns system)

(def system (atom nil))

(defn get-system []
  @system)
