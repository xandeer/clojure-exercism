(ns prime-factors)

(defn of
  ([n] (of n [] 2))
  ([n acc count]
   (cond
     (= n 1) acc
     (zero? (mod n count)) (recur (/ n count) (conj acc count) count)
     :else (recur n acc (inc count)))))
