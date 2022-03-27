(ns pascals-triangle)

(defn next-row [row]
  (lazy-cat [1] (mapv +' (butlast row) (rest row)) [1]))

(def triangle
  (iterate next-row [1]))

(defn row [n]
  (->> triangle
       (take n)
       last))
