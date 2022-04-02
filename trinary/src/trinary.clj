(ns trinary)

(def base 3)

(defn to-int [char]
  (- (int char) (int \0)))

(defn valid? [char]
  (< -1 (to-int char) base))

(defn to-decimal [trinary]
  (if (every? valid? trinary)
    (->> trinary
         (map to-int)
         (reduce (fn [acc itm]
                   (+ (* acc base) itm))))
    0))
