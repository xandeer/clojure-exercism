(ns word-count)

(def rx #"\w+'\w|\w+")

(defn word-count [s]
  (->> s
       clojure.string/lower-case
       (re-seq rx)
       frequencies))
