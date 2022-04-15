(ns wordy
  (:require [clojure.string :refer [join]]))

(def instructions ["plus" "minus" "multiplied by" "divided by"])
(def operations (zipmap instructions [+ - * /]))

(def oper-re " (plus|minus|multiplied by|divided by) (-?\\d+)")
(def expr-re (str "What is (-?\\d+)((?:" oper-re ")*)\\?"))

(defn evaluate [question]
  (if-some [m (re-matches (re-pattern expr-re) question)]
    (reduce (fn [acc [_ op n]]
              ((operations op) acc (read-string n)))
            (read-string (m 1))
            (re-seq (re-pattern oper-re) (m 2)))
    (throw (IllegalArgumentException.))))
