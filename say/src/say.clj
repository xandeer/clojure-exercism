(ns say
  (:require [clojure.string :refer [join trim]]))

(def special-nums
  {1 "one"
   2 "two"
   3 "three"
   4 "four"
   5 "five"
   6 "six"
   7 "seven"
   8 "eight"
   9 "nine"
   10 "ten"
   11 "eleven"
   12 "twelve"
   13 "thirteen"
   14 "fourteen"
   15 "fifteen"
   16 "sixteen"
   17 "seventeen"
   18 "eighteen"
   19 "nineteen"
   20 "twenty"
   30 "thirty"
   40 "forty"
   50 "fifty"
   80 "eighty"})

(defn under-hundred [n]
  (or (special-nums n)
      (let [a (int (/ n 10))
            b (mod n 10)
            x (or (special-nums (* a 10))
                  (str (special-nums a) "ty"))
            y (if (zero? b) ""
                  (special-nums b))]
        (join "-" [x y]))))

(defn under-thousand [n with-and?]
  (let [a (int (/ n 100))
        b (mod n 100)
        x (if (zero? a) ""
              (str (special-nums a) " hundred"
                   ;; (when with-and? "and ")
                   ))
        y (if (zero? b) ""
              (under-hundred b))]
    (trim (join " " [x y]))))

(defn with-suffix [suffix n]
  (if (zero? n) ""
      (format "%s %s" (under-thousand n false) suffix)))

(defn number [num]
  (condp apply [num]
    #(or (neg? %)
         (> % 999999999999)) (throw (IllegalArgumentException.))
    zero? "zero"
    (->> num
         str
         reverse
         (partition-all 3)
         (map reverse)
         (map #(apply str %))
         (map read-string)
         (map with-suffix ["" "thousand" "million" "billion"])
         reverse
         (join " ")
         trim)))
