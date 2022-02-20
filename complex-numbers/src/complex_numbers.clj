(ns complex-numbers)

(def real first)

(def imaginary second)

(def square (partial reduce #(+ (Math/pow %2 2) %1) 0))

(defn abs [a]
  (Math/sqrt (square a)))

(defn conjugate [a]
  (update a 1 -))

(def add (partial map +))

(def sub (partial map -))

(defn- operate [f1 f2 [a b] [c d]]
  [(f1 (* a c) (* b d))
   (f2 (* b c) (* a d))])

(def mul (partial operate - +))

(defn div [a b]
  (->> (operate + - a b)
       (map #(/ % (square b)))))
