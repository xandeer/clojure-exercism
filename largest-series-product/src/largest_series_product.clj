(ns largest-series-product)

(defn largest-product [length input]
  (cond
    (or (re-find #"[^0-9]" input)
        (neg? length)
        (and (pos? length)
             (empty? input))) (throw (IllegalArgumentException. "Invalid input."))
    (zero? length) 1
    :else (->> (partition length 1 input)
               (map #(map (fn [c]
                            (- (int c) (int \0))) %))
               (map #(apply * %))
               (apply  max))))
