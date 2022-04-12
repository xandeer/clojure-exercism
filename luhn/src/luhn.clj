(ns luhn)

(defn- double [n]
  (let [db (* n 2)]
    (if (> db 9) (- db 9)
        db)))

(defn valid? [s]
  (let [no-space (clojure.string/replace s #" " "")]
    (and (> (count no-space) 1)
         (boolean (re-matches #"\d*" no-space))
         (->> (clojure.string/split no-space #"")
              (map read-string)
              reverse
              (partition 2 2 [0])
              (reduce (fn [acc [a b]]
                        (+ acc a (double b)))
                      0)
              (#(mod % 10))
              zero?))))
