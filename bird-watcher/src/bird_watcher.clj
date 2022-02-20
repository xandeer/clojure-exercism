(ns bird-watcher)

(def last-week
  [0 2 5 3 7 8 4])

(defn today [birds]
  (last birds))

(defn inc-bird [birds]
  (-> birds
      pop
      (conj (inc (today birds)))))

(defn day-without-birds? [birds]
  (some? (some zero? birds)))

(defn n-days-count [birds n]
  (->> birds
       (take n)
       (reduce +)))

(defn busy-days [birds]
  (->> birds
       (remove #(< % 5))
       count))

(defn odd-week? [birds]
  (= (take (count birds) (cycle [1 0])) birds))
