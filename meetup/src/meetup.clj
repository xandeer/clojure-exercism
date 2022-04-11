(ns meetup
  (:import java.time.LocalDate))

(defn- get-day-of-week [year month day]
  (-> (LocalDate/of year month day)
      .getDayOfWeek
      .getValue))

(def weeks (zipmap [:monday :tuesday :wednesday :thursday :friday :saturday :sunday]
                   (iterate inc 1)))

(defn- first-week [year month week]
  (->> (range 1 8)
       (reduce #(if (= (get-day-of-week year month %2)
                       (week weeks))
                  (reduced %2)
                  1))))

(defn- is-teenth? [d]
  (<= 13 d 19))

(defn- get-teenth [first]
  (->> (range 1 3)
       (reduce (fn [acc _]
                 (if (is-teenth? acc)
                   (reduced acc)
                   (+ acc 7)))
               first)))

(defn- get-last [year month week]
  (let [next-month (-> (LocalDate/of year month 1)
                       (.plusMonths 1)
                       bean
                       ((juxt :year :monthValue)))
        year (first next-month)
        month (second next-month)
        next-week (first-week year month week)]
    (-> (LocalDate/of year month next-week)
        (.minusWeeks 1)
        .getDayOfMonth)))

(def orders (zipmap [:first :second :third :fourth]
                    (iterate inc 0)))

(defn meetup [month year week order]
  [year month
   (condp apply [order]
     orders (+ (first-week year month week)
               (* 7 (orders order)))
     #(= :teenth %) (get-teenth (first-week year month week))
     #(= :last %) (get-last year month week))])
