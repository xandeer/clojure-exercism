(ns binary-search)

(defn middle [v]
  (int (/ (count v) 2)))

(defn search-for
  ([target elements] (search-for target elements 0))
  ([target elements acc]
   (if (empty? elements) (throw (Exception. "not found"))
       (let [middle-point (middle elements)
             middle-element (nth elements middle-point)]
         (cond
           (= target middle-element) (+ acc middle-point)
           (< target middle-element) (recur target (take middle-point elements) acc)
           :else (recur target (drop (inc middle-point) elements) (+ acc (inc middle-point))))))))

