(ns isogram
  (:require [clojure.string :as s]))

(defn isogram? [word]
  (->> (s/replace word #"[ -]" "")
       s/lower-case
       ;; frequencies
       ;; vals
       ;; (every? (partial = 1))
       (apply distinct?)
       ))
