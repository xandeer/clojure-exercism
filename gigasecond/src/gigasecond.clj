(ns gigasecond)

(defn from [y m d]
  (-> (java.time.LocalDate/of y m d)
      (.atStartOfDay)
      (.plusSeconds 1e9)
      bean
      ((juxt :year :monthValue :dayOfMonth))))
