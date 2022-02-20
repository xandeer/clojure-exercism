(ns all-your-base)

(defn to-decimal [base value]
  (->> value
       reverse
       (map-indexed #(int (* %2 (Math/pow base %1))))
       (reduce +)))

(defn decimal-to [base value]
  (letfn [(reversed-to [v]
            (if (zero? v) '()
                (conj (reversed-to (int (/ v base)))
                      (rem v base))))]
    (reverse (reversed-to value))))

(defn convert [input-base digits output-base]
  (cond (or (some #(< % 2) [input-base output-base])
            (some neg? digits)
            (some #(>= % input-base) digits)) nil
        (empty? digits) digits
        (every? zero? digits) '(0)
        :else (->> digits
                   (to-decimal input-base)
                   (decimal-to output-base))))
