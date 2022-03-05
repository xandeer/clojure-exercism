(ns scrabble-score)

(defn score-letter [l]
  (condp apply (clojure.string/lower-case l)
      #{\a \e \i \o \u \l \n \r \s \t} 1
      #{\d \g} 2
      #{\b \c \m \p} 3
      #{\f \h \v \w \y} 4
      #{\k} 5
      #{\j \x} 8
      #{\q \z} 10))

(defn score-word [w]
  (->> w
       (map score-letter)
       (reduce +)))

(defn score-word [w]
  (->> w
       frequencies
       seq
       (map #(* (score-letter (first %)) (second %)))
       (apply +)))
