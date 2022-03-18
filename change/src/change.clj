(ns change)

(defn- change [cash coins]
  (let [coin (first coins)]
    (cond
      (zero? cash) '()
      (= 1 (count coins)) (repeat (/ cash coin) coin)
      :else (concat (change (mod cash coin) (rest coins))
                    (repeat (/ cash coin) coin)))))

(defn issue [cash coins]
  (let [coins (sort coins)]
    (when (or (neg? cash)
              (and (pos? cash)
                   (< cash (first coins))))
      (throw (IllegalArgumentException. "cannot change")))

    (->> (range 2 (inc (count coins)))
         (map #(take % coins))
         (map reverse)
         (map (partial change cash))
         (reduce #(if (< (count %1) (count %2)) %1
                      %2)))))
