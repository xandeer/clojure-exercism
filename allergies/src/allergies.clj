(ns allergies)

(def items
  [:eggs
   :peanuts
   :shellfish
   :strawberries
   :tomatoes
   :chocolate
   :pollen
   :cats])

(defn allergies [score]
  (->> items
       (map-indexed (fn [idx itm]
                      (when (bit-test score idx) itm)))
       (remove nil?)))

(defn allergic-to? [score item]
  (->> (allergies score)
       (some (partial = item))))
