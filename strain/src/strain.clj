(ns strain)

(defn- prefn [pre]
  (fn [acc item]
    (if (pre item) (conj acc item)
        acc)))

(defn- handle [pre col]
  (reduce (prefn pre) [] col))

(defn retain [pre col]
  (handle pre col))

(defn discard [pre col]
  (handle (comp not pre) col))

(defn discard [pre col]
  (retain (complement pre) col))
