(ns collatz-conjecture)

(defn- =1? [n] (= 1 n))

(defn- collatz-i [num]
  (if (even? num) (/ num 2)
      (inc (* num 3))))

(defn- guard-input [n]
  (when (< n 1)
    (throw (Exception. "Wrong input: n must be larger than 0."))))

(defn collatz-loop [n]
  (guard-input n)
  (loop [i 0 acc n]
    (if (=1? acc) i
        (recur (inc i) (collatz-i acc)))))

(defn collatz-r [n]
  (guard-input n)
  (if (=1? n) 0
      (inc (collatz-r (collatz-i n)))
      ;; ((comp inc collatz collatz-i) n)
      ))

(defn collatz-recur
  ([n] (collatz-recur n 0))
  ([n i]
   (guard-input n)
   (if (=1? n) i
       (recur (collatz-i n)
              (inc i)))))

(defn collatz-reduced [n]
  (guard-input n)
  (reduce #(if (=1? %1) (reduced %2)
               (collatz-i %1))
          n
          (range)))

;; intersting, but the slowest
(defn collatz-take-while [n]
  (guard-input n)
  (->> n
       (iterate collatz-i)
       (take-while #(not= 1 %))
       (count)))

;; loop is the fastest
(def collatz collatz-loop)

;; (time
;;  (dotimes [_ 100000]
;;    (collatz-loop 1000000)))

;; (time
;;  (dotimes [_ 100000]
;;    (collatz-take-while 1000000)))

;; (time
;;  (dotimes [_ 100000]
;;    (collatz-recur 1000000)))

;; (time
;;  (dotimes [_ 100000]
;;    (collatz-r 1000000)))

;; (time
;;  (dotimes [_ 100000]
;;    (collatz-reduced 1000000)))
