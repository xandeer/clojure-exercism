(ns matching-brackets)

(def pairs
  {\( \)
   \[ \]
   \{ \}})

(def bracket? (-> (set (keys pairs))
                  (into (vals pairs))))

(defn valid? [string]
  (->> (filter bracket? string)
       (reduce (fn [acc itm]
                 (cond
                   (= (pairs (peek acc)) itm) (pop acc)
                   (pairs itm) (conj acc itm)
                   :else (reduced [:invalid])))
               [])
       empty?))
