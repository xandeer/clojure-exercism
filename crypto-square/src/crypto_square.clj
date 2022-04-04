(ns crypto-square
  (:require [clojure.string :as s]))

(defn normalize-plaintext [raw]
  (->> (s/lower-case raw)
       (re-seq #"[0-9a-z]")
       (apply str)))

(defn square-size [str]
  (->> (count str)
       Math/sqrt
       Math/ceil
       int))

(defn plaintext-segments [raw]
  (let [normalized (normalize-plaintext raw)]
    (->> normalized
         (partition-all (square-size normalized))
         (mapv #(apply str %)))))

(defn- padded-segments [raw]
  (let [segments (plaintext-segments raw)
        segment-size (count (first segments))
        last-segment (last segments)
        last-size (count last-segment)
        padded-last (apply str (concat last-segment (repeat (- segment-size last-size) " ")))]
    (->> (concat (butlast segments) [padded-last])
         (apply interleave)
         (apply str))))

(defn ciphertext [raw]
  (-> (padded-segments raw)
       (s/replace #" " "")))

(defn normalize-ciphertext [raw]
  (let [normalized (normalize-plaintext raw)
        padded (padded-segments raw)
        row-size (/ (count padded) (square-size normalized))]
    (->> padded
         (partition row-size)
         (map #(apply str %))
         (s/join " "))))
