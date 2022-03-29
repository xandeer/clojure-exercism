(ns phone-number)

(defn number [num-string]
  (or (->> (re-seq #"\d" num-string)
           (apply str)
           (re-matches #"1?([2-9]\d{2}[2-9]\d{6})")
           last)
      (apply str (repeat 10 "0"))))

(defn area-code [num-string]
  (-> (number num-string)
      (subs 0 3)))

(defn pretty-print [num-string]
  (->> (number num-string)
       (partition-all 3)
       (map #(apply str %))
       (apply format "(%s) %s-%s%s")))
