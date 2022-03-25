(ns kindergarten-garden)

(def ^:const plants
  {\R :radishes
   \C :clover
   \G :grass
   \V :violets})

(def ^:const default-students
  ["Alice" "Bob" "Charlie" "David" "Eve" "Fred"
   "Ginny" "Harriet" "Ileana" "Joseph" "Kincaid" "Larry"])

(defn garden
  ([seeds] (garden seeds default-students))
  ([seeds students]
   (let [plants (->> (clojure.string/split-lines seeds)
                     (map #(partition 2 %))
                     (apply map concat)
                     (map #(map plant-map %)))
         students (->> students
                       (map clojure.string/lower-case)
                       sort
                       (map keyword))]
     (zipmap students plants))))
