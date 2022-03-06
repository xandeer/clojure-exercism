(ns secret-handshake)

(defn- bit-and? [x y]
  (not (zero? (bit-and x y))))

(defn commands [n]
  (cond-> []
    (bit-and? n 1) (conj "wink")
    (bit-and? n 2) (conj "double blink")
    (bit-and? n 4) (conj "close your eyes")
    (bit-and? n 8) (conj "jump")
    (bit-and? n 16) reverse))

(defn commands [n]
  (->> ["wink" "double blink" "close your eyes" "jump" reverse]
       (map-indexed vector)
       (filter #(bit-test n (first %)))
       (mapcat (juxt second))
       (reduce (fn [acc cmd]
                 ((if (string? cmd) #(conj % cmd)
                      cmd)
                  acc))
               [])))
