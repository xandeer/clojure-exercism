(ns perfect-numbers)

(defn classify [n]
  (if (pos? n)
    (let [s (->> (range 1 n)
                 (filter #(zero? (mod n %)))
                 (apply +))]
      (condp apply [s]
        #(= n %) :perfect
        #(< n %) :abundant
        :deficient))
    (throw (IllegalArgumentException.))))
