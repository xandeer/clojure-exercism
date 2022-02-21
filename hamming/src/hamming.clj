(ns hamming)

(defn distance [strand1 strand2]
  (if (= (count strand1)
         (count strand2))
    (reduce #(+ %1 (if (= (nth strand1 %2)
                          (nth strand2 %2))
                     0
                     1))
            0
            (range (count strand1)))))

(defn distance-filter [a b]
  (when (= (count a)
           (count b))
    (->> (map = a b)
         (filter false?)
         count)))
