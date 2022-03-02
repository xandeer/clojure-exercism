(ns roman-numerals
  (:require [clojure.string :refer [join]]))

(defn numerals [n]
  (cond
    (>= n 1000) (str (join (repeat (quot n 1000) \M))
                     (numerals (mod n 1000)))
    (>= n 900) (str "CM"
                    (numerals (- n 900)))
    (>= n 500) (str "D" (join (repeat (quot (- n 500) 100) \C))
                    (numerals (mod n 100)))
    (>= n 400) (str "CD"
                    (numerals (- n 400)))
    (>= n 100) (str (join (repeat (quot n 100) \C))
                    (numerals (mod n 100)))
    (>= n 90) (str "XC"
                   (numerals (- n 90)))
    (>= n 50) (str "L" (join (repeat (quot (- n 50) 10) \X))
                   (numerals (mod n 10)))
    (>= n 40) (str "XL"
                   (numerals (- n 40)))
    (>= n 10) (str (join (repeat (quot n 10) \X))
                  (numerals (mod n 10)))
    (= 9 n) "IX"
    (>= n 5) (str "V" (join (repeat (- n 5) \I)))
    (= 4 n) "IV"
    :else (join (repeat n \I))))
