(ns robot-name)

(defn- rand-upper-char []
  (-> (rand-int 26)
      (+ (int \A))
      char))

(defn- rand-str [f l]
  (apply str (repeatedly l f)))

(defn- generate-name []
  (str (rand-str rand-upper-char 2)
       (rand-str #(rand-int 10) 3)))

(def names (atom #{}))

(defn- unique-name []
  (let [name (->> (repeatedly generate-name)
                  (reduce #(when-not (@names %2) (reduced %2))))]
    (swap! names conj name)
    name))

(defn robot []
  (atom {:name (unique-name)}))

(defn robot-name [robot]
  (:name @robot))

(defn reset-name [robot]
  (swap! robot conj {:name (unique-name)}))
