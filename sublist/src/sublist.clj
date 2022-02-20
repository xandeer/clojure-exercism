(ns sublist)

(defn lists-equal? [list1 list2]
  (and (= (count list1)
          (count list2))
       (every? true? (mapv = list1 list2))))

(defn sublist? [a b]
  (and (< (count a) (count b))
       (or (= [] a)
           (and (some #(= (first a) %) b)
                (some #(lists-equal? a %)
                      (keep-indexed
                       #(if (= (first a) %2)
                          (take (count a) (nthrest b %1)))
                       b))))))

(defn classify [list1 list2] ;; <- arglist goes here
  ;; your code goes here
  (cond
    (lists-equal? list1 list2) :equal
    (sublist? list1 list2) :sublist
    (sublist? list2 list1) :superlist
    :else :unequal))

(def a [1 2 3])
(def b [3 1 2 3 4 1 2 3 6])
(partition (count a) 1 b)
(a 1)
(some #{a} (partition (count a) 1 b))
(macroexpand #{a})
(some #{12} [2 1 2])
(def c #{1})
(c 1)
