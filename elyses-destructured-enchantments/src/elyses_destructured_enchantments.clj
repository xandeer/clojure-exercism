(ns elyses-destructured-enchantments)

(defn first-card
  "Returns the first card from deck."
  [deck]
  (let [[first] deck]
    first))

(defn second-card
  "Returns the second card from deck."
  [deck]
  (let [[_ second] deck]
    second))

(defn swap-top-two-cards
  "Returns the deck with first two items reversed."
  [deck]
  (let [[first second & remains] deck]
    (into [second first] remains)))

(defn discard-top-card
  "Returns a vector containing the first card and
   a vector of the remaining cards in the deck."
  [deck]
  (let [[first & remains] deck]
    (conj [first] (if (nil? remains) nil
                      (into [] remains)))))

(def face-cards
  ["jack" "queen" "king"])

(defn insert-face-cards
  "Returns the deck with face cards between its head and tail."
  [deck]
  (let [[first & remains] deck]
    (-> (if (nil? first) [] [first])
        (into face-cards)
        (into remains))))
