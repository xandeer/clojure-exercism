(ns hexadecimal)

(defn hex-to-int [hex]
  (if (re-find #"[^0-9a-f]" hex) 0
      (->> (reverse hex)
           (map (fn [c]
                  (- (int c)
                     (if (>= (int c) (int \a)) (- (int \a) 10)
                         (int \0)))))
           (map-indexed (fn [idx itm]
                          (* itm (bigint (Math/pow 16 idx)))))
           (apply +))))
