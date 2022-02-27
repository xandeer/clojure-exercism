(ns raindrops)

(def drops
  {3 "Pling"
   5 "Plang"
   7 "Plong"})

(defn convert [num]
  (let [r (->> (keys drops)
               (map #(when (zero? (mod num %))
                       (get drops %)))
               (filter (comp not nil?))
               (apply str))]
    (if (zero? (count r)) (str num)
        r)))

(defn convert [num]
  (letfn [(rain? [n] (zero? (mod num n)))]
    (cond-> nil
      (rain? 3) (str "Pling")
      (rain? 5) (str "Plang")
      (rain? 7) (str "Plong")
      :always (or (str num)))))
