(ns binary-search-tree)

(defn singleton [v]
  {:value v})

(def value :value)

(def left :left)

(def right :right)

(defn insert [v t]
  (if (nil? t)
    (singleton v)
    (if (> v (value t))
      (assoc t :right (insert v (right t)))
      (assoc t :left (insert v (left t))))))

(defn to-list [{:keys [left value right] :as t}]
  (when t
    `(~@(to-list left) ~value ~@(to-list right))))

(defn to-list [{:keys [left value right] :as t}]
  (when t
    (concat (to-list left) [value] (to-list right))))

(defn from-list [v]
  (reduce #(insert %2 %1) nil v))
