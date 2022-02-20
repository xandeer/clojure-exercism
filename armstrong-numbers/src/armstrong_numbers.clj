(ns armstrong-numbers
  (:require [clojure.string :as str]))

(defn armstrong? [num]
  (let [s (format "%d" num)
        e (count s)]
    (->> (str/split s #"")
         (map read-string)
         (map bigdec)
         (map #(.pow % e))
         (reduce +)
         (== num))))
