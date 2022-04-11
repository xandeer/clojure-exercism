(ns spiral-matrix)

(defn- map-spiral [v n acc s e]
  (cond
    (empty? v) acc
    (= s e) (merge acc (zipmap [(/ (inc (* n n)) 2)] v))
    :else (let [length (- e s)
                top-left (inc (* s (inc n)))
                top-right (+ top-left length)
                bottom-left (+ top-left (* n length))
                bottom-right (+ bottom-left length)
                first-row (-> (range top-left top-right)
                              (zipmap v))
                last-column (-> (range top-right bottom-right n)
                                (zipmap (drop length v)))
                last-row (-> (range bottom-right bottom-left -1)
                             (zipmap (drop (* 2 length) v)))
                first-column (-> (range bottom-left top-left (- n))
                                 (zipmap (drop (* 3 length) v)))]
            (recur (drop (* 4 length) v)
                   n
                   (merge acc first-row last-column last-row first-column)
                   (inc s)
                   (dec e)))))

(defn spiral [n]
  (->> (map-spiral (range 1 (inc (* n n))) n {} 0 (dec n))
       sort
       (map last)
       (partition n)))
