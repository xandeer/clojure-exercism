(ns run-length-encoding)

(defn run-length-encode
  "encodes a string with run-length-encoding"
  [plain-text]
  (->> plain-text
       (reduce (fn [a i]
                 (if (or (empty? a)
                         (not= (:key (last a)) i))
                   (conj a {:key i :count 1})
                   (conj (pop a) {:key i :count (inc (:count (last a)))}))) [])
       (map #(if (> (% :count) 1) (str (% :count) (% :key))
                 (str (% :key))))
       (apply str)))

(defn run-length-decode
  "decodes a run-length-encoded string"
  [cipher-text]
  (->> cipher-text
       (re-seq #"(\d*)(\D)")
       (mapcat #(repeat (if (empty? (second %)) 1
                            (read-string (second %)))
                        (last %)))
       (apply str)))

(defn run-length-encode
  "encodes a string with run-length-encoding"
  [plain-text]
  (->> plain-text
       (partition-by identity)
       (mapcat (juxt count first))
       (remove #{1})
       (apply str)))
