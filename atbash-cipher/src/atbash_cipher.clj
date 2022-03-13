(ns atbash-cipher)

(def ^:const lower-alphabet
  (let [b (int \a)
        e (int \z)]
    (->> (range b (inc e))
         (map char)
         (apply str))))

(def ^:const cipher-chars
  (zipmap lower-alphabet (reverse lower-alphabet)))

(defn encode [plain]
  (->> plain
       (clojure.string/lower-case)
       (re-seq #"\w")
       (map (comp char first))
       (map #(or (cipher-chars %)
                 %))
       (partition-all 5)
       (map #(apply str %))
       (clojure.string/join " ")))
