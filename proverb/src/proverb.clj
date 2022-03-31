(ns proverb
  (:require [clojure.string :as s]))

(defn recite [things]
  (if (not-empty things)
    (->> things
         (partition 2 1)
         (mapv (fn [[want lost]]
                 (format "For want of a %s the %s was lost."
                         want lost)))
         (#(conj % (format "And all for the want of a %s." (first things))))
         (s/join "\n"))
    ""))
