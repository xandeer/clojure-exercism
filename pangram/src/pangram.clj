(ns pangram
  (:require [clojure.string :as s]))

(defn- char-range [start end]
  (map char
       (range (int start)
              (inc (int end)))))

(def freq-zero (zipmap (char-range \a \z) (repeat 0)))

(defn pangram? [sentence]
  (->> (frequencies (s/lower-case sentence))
       (merge freq-zero)
       vals
       (every? pos?)))

(defn pangram-re? [sentence]
  (->> (s/lower-case sentence)
       (re-seq #"[a-z]")
       distinct
       count
       (= 26)))

;; (def pangram? pangram-re?)

(defn pangram-set? [sentence]
  (-> (s/lower-case sentence)
      set
      (every? (char-range \a \z))))

;; (def pangram? pangram-set?)
