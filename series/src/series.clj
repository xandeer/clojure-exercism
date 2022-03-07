(ns series)

(defn slices [string length]
  (if (pos? length)
    (mapv (partial apply str)
          (partition length 1 string))
    [""]))
