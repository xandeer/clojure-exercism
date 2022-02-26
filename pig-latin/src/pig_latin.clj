(ns pig-latin
  (:require [clojure.string :as s]))

(defn translate-word [w]
  (cond
    (re-find #"^[aeiou]|xr|yt" w) (str w "ay")
    (re-find #"^.y$" w) (s/replace w #"(.)y" "y$1ay")
    (re-find #"^qu" w) (s/replace w #"qu(.*)" "$1quay")
    (re-find #"^[^a^e^i^o^u]qu" w) (s/replace w #"(...)(.*)" "$2$1ay")
    (re-find #"^[^a^e^i^o^u]*[aeiou]" w) (s/replace w #"([^a^e^i^o^u]*)([aeiou].*)" "$2$1ay")
    (re-find #"[^a^e^i^o^u]*y" w) (s/replace w #"([^a^e^i^o^u]*)(y.*)" "$2$1ay")
    :else w))

(def rule1 #"([aeiou]|xr|yt)(.+)")
(def rule2 #"(s?qu|[^aeiou]+)(.+)")

(defn translate-word [w]
  (condp re-matches w
    rule1 :>> #(str (first %) "ay")
    rule2 :>> #(str (% 2) (% 1) "ay")
    w))

(defn translate [s]
  (->> (s/split s #"\b")
       (map translate-word)
       (apply str)))
