(ns rotational-cipher
  (:require [clojure.string :refer [upper-case]]))

(defn- rotate-char [b e c n]
  (+ b
     (mod
      (+ (- c b) n)
      (inc (- e b)))))

(defn rotate [letter n]
  (->> letter
       (map int)
       (map #(cond
               (and (>= % (int \a))
                    (<= % (int \z))) (rotate-char (int \a) (int \z) % n)
               (and (>= % (int \A))
                    (<= % (int \Z))) (rotate-char (int \A) (int \Z) % n)
               :else %))
       (map char)
       (apply str)))

(def ^:const lower-alphabet
  (let [b (int \a)
        e (int \z)]
    (->> (range b (inc e))
         (map char)
         (apply str))))

(def ^:const upper-alphabet (upper-case lower-alphabet))

(defn- shift [raw n]
  (->> (cycle raw)
       (drop (mod n (count raw)))
       (take (count raw))
       (apply str)))


(defn rotate [letter n]
  (let [raws [lower-alphabet upper-alphabet]
        cipher (zipmap (apply str raws)
                       (->> raws
                            (map #(shift % n))
                            (apply str)))]
    (->> letter
         (map #(cipher %1 %1))
         (apply str))))
