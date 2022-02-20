(ns interest-is-interesting)

(defn interest-rate
  "Caluate the interest rate."
  [balance]
  (condp > balance
    0 -3.213
    1000 0.5
    5000 1.621
    2.475))

(defn annual-balance-update
  [balance]
  ((if (neg? balance) - +)
   balance
   (-> balance
       interest-rate
       bigdec
       (* balance)
       (/ 100))))

(defn amount-to-donate
  [balance tax-free-percentage]
  (if (neg? balance) 0
      (-> balance
          (* tax-free-percentage)
          (* 2)
          (/ 100)
          int)))
