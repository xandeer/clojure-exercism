(ns etl
  (:require [clojure.string :refer [lower-case]]))

(defn transform [source]
  (->> source
       keys
       (reduce (fn [m k]
                 (into m
                       (map #(hash-map (lower-case %) k)
                            (source k))))
               {})))

(defn transform-for [s]
  (into {}
        (for [[score letters] s
              letter letters]
          {(lower-case letter) score})))
