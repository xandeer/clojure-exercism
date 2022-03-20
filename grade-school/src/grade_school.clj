(ns grade-school)

(defn grade [school grade]
  (get school grade []))

(defn add [school name grade]
  (update school grade (fnil conj []) name))

(defn sorted [school]
  (->> school
       keys
       sort
       (map #(->> (get school %)
                  sort
                  (assoc {} %)))
       (reduce conj)))


(defn grade [school grade]
  (school grade []))

(defn add [school name grade]
  (merge-with concat school {grade [name]}))

(defn sroted [school]
  (->> school
       (map (fn [[k v]]
              [k (sort v)]))
       (into (sorted-map))))
