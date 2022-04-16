(ns bank-account)

(def open-account #(atom 0))

(def close-account #(reset! % nil))

(def get-balance deref)

(defn update-balance [account amount]
  (swap! account + amount))
