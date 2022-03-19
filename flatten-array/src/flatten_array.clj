(ns flatten-array)

(defn flatten [arr]
  (->> arr
       (reduce (fn [acc itm]
                 (if (coll? itm) (apply (partial conj acc) (flatten itm))
                     (conj acc itm)))
               [])
       ;; (remove nil?)
       ;; (filter identity)
       (keep identity)))
