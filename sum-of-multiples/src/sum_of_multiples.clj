(ns sum-of-multiples)

(defn sum-of-multiples [nums end]
  (->> (range 1 end)
       (filter #(reduce (fn [_ item]
                          (if (zero? (mod % item))
                            (reduced true)
                            false))
                        false nums))
       (apply +)))

(defn sum-of-multiples [nums end]
  (->> (range 1 end)
       (filter #(some (fn [n]
                        (zero? (mod % n)))
                      nums))
       (apply +)))

(defn sum-of-multiples [nums end]
  (->> nums
       (mapcat #(range % end %))
       ;; set
       distinct
       (apply +)))
