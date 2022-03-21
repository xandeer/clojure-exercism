(ns grains)

(defn square [n]
  (bigint
   (.pow 2M (dec n))))

(defn total []
  (->> (range 1 65)
       (map square)
       (apply +)))

(defn square [n]
  (.shiftLeft
   (biginteger 1)                       ;; (BigInteger/ONE)
   (dec n)))

(defn total []
  (dec
   (square 65)))
