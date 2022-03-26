(ns leap)

(defn leap-year? [year]
  (or (zero? (mod year 400))
      (and (zero? (mod year 4))
           (not (zero? (mod year 100))))))

(defn leap-year? [year]
  (condp #(zero? (mod %2 %1)) year
    400 true
    100 false
    4 true
    false))
