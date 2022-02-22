(ns nth-prime)

(defn- prime? [n]
  (cond
    (or (= n 2)
        (= n 3)) true
    (or (<= n 1)
        (zero? (mod n 2))
        (zero? (mod n 3))) false
    :else (reduce #(if (or (zero? (mod n %2))
                           (zero? (mod n (+ 2 %2))))
                     (reduced false)
                     true)
                  true
                  (range 5 (inc (Math/sqrt n)) 6))))

(def cache (atom [2 3 5]))

(defn nth-prime [n]
  (when (< n 1)
    (throw (IllegalArgumentException. "Wrong input.")))
  (if (>= (count @cache) n) (nth @cache (dec n))
      (reduce #(when (prime? %2)
                 (do
                   (swap! cache conj %2)
                   (when (= (count @cache) n)
                     (reduced %2))))
              (drop (inc (last @cache)) (range)))))
