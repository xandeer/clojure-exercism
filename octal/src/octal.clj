(ns octal)

(def base 8)

(defn- to-int [c]
  (- (int c) (int \0)))

(defn- valid? [c]
  (<= 0 (to-int c) (dec base)))

(defn to-decimal [octal]
  (if (every? valid? octal)
    (->> octal
         (map to-int)
         (reduce (fn [a i]
                   (+ (* a base) i))))
    0))

(defn to-octal
  ([decimal] (to-octal decimal ""))
  ([decimal rest]
   (if (zero? decimal)
     rest
     (recur (int (/ decimal base))
            (str (mod decimal base) rest)))))
