(ns queen-attack)

(def empty-board
  (->> (repeat 8 "_")
       (clojure.string/join " ")
       (#(str % "\n"))
       (repeat 8)
       (apply str)))

(defn- put-piece [board piece [r c]]
  (->> (partition 16 board)
       (map-indexed (fn [row-index row]
                      (map-indexed (fn [column-index original]
                                     (if (and (= r row-index)
                                              (= (* 2 c) column-index))
                                       piece
                                       original))
                                   row)))
       (apply concat)
       (apply str)))

(defn board-string [{:keys [w b]}]
  (-> (put-piece empty-board \W w)
      (put-piece \B b)))

(def abs #(max % (- %)))

(defn can-attack [{[wx wy] :w [bx by] :b}]
  (or (= wx bx)
      (= wy by)
      (= (abs (- wx bx))
         (abs (- wy by)))))
