(ns robot-simulator)

(defn robot [coords bearing]
  {:coordinates coords
   :bearing bearing})

(def bearings [:north :east :south :west])

(defn- turn-bearing [bearings]
  (zipmap bearings
          (drop 1 (cycle bearings))))

(def turn-right (turn-bearing bearings))

(def turn-left (turn-bearing (reverse bearings)))

(def advance (zipmap bearings [[:y inc] [:x inc] [:y dec] [:x dec]]))

(def instruct {\R #(update % :bearing turn-right)
               \L #(update % :bearing turn-left)
               \A #(let [a (advance (:bearing %))]
                     (update-in % [:coordinates (first a)] (second a)))})

(defn simulate [instructions robbie]
  (reduce #((instruct %2) %1) robbie instructions))
