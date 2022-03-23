(ns isbn-verifier)

(defn isbn? [isbn]
  (boolean
   (some->> (clojure.string/replace isbn "-" "")
            (re-matches #"^\d{9}[\dX]$")
            (replace {\X 10})
            (map (comp read-string str))
            (map  * (range 10 0 -1))
            (apply +)
            (#(mod % 11))
            zero?)))
