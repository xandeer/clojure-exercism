(ns triangle)

(defn- other-sides [sides]
  (map #(take 2 (drop % (cycle sides))) (range 1 4)))

(defn- other-sum [sides]
  (->> sides
       other-sides
       (map (partial reduce +))
       (zipmap sides)
       seq))

(defn is-valid? [sides]
  (and
   (every? pos? sides)
   (->> sides
        other-sum
        (every? #(<= (first %) (second %))))))

(defn is-valid? [sides]
  (and (every? pos? sides)
       (let [sorted (sort sides)
             [a b c] sorted]
         (>= (+ a b) c))))

(defn- with-check? [f sides]
  (and (is-valid? sides)
       (f sides)))

(defn- distinct-count [sides]
  (count (distinct sides)))

(defn equilateral? [& sides]
  (with-check?
    #(= 1 (distinct-count %)) sides))

(defn isosceles? [& sides]
  (with-check?
    #(> 3 (distinct-count %)) sides))

(defn scalene? [& sides]
  (with-check?
    #(= 3 (distinct-count %)) sides))

(defn degenerate? [& sides]
  (with-check?
    #(let [sorted (sort %)
           [a b c] sorted]
       (= (+ a b) c)) sides))
