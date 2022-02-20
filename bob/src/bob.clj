(ns bob
  (:require [clojure.string :refer [trim blank?]]))

(defn question? [sentence]
  (= \? (last sentence)))

(defn yell? [sentence]
  (and (re-find #"[A-Z]" sentence)
       (not (re-find #"[a-z]" sentence))))

(defn response-for [s]
  (condp apply [(trim s)]
    blank? "Fine. Be that way!"
    (every-pred question? yell?) "Calm down, I know what I'm doing!"
    question? "Sure."
    yell? "Whoa, chill out!"
    "Whatever."))
