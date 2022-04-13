(ns sieve)

(defn- rm-multiples
  ([col n] (rm-multiples col n 2))
  ([col n i]
   (if (> (* n i) (last col)) col
       (recur (remove #(= (* n i) %) col) n (inc i)))))

(defn sieve
  ([limit] (sieve (range 2 (inc limit)) 0))
  ([col index]
   (if (= (count col) index) col
       (recur (rm-multiples col (nth col index)) (inc index)))))
