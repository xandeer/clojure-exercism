(ns binary)

(defn to-decimal [binary]
  (if (re-find #"[^01]" binary) 0
      (->> binary
           reverse
           (map-indexed (fn [idx itm]
                          (if (= \0 itm) 0
                              (Math/pow 2 idx))))
           (apply +)
           int)))

(defn to-decimal [binary]
  (if (not-every? #{\0 \1} binary) 0
      (->> binary
           reverse
           (map-indexed (fn [idx itm]
                          (bit-shift-left (case itm \1 1 0) idx)))
           (apply +))))
