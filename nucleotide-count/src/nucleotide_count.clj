(ns nucleotide-count)

(def rx #"[^A^C^G^T]")

(defn count-of-nucleotide-in-strand [nucleotide strand]
  (when (or (re-find rx (format "%s" nucleotide))
            (re-find rx strand))
    (throw (IllegalArgumentException. "Wrong input.")))
  (->> strand
       (map (partial = nucleotide))
       (filter true?)
       count))


(def nucleotides (set "ACGT"))

(defn nucleotide-counts [strand]
  (when (re-find rx strand)
    (throw (IllegalArgumentException. "Wrong input.")))
  (reduce #(conj %1 [%2 (count-of-nucleotide-in-strand %2 strand)])
          {}
          nucleotides))

(defn nucleotide-counts-freq [strand]
  {:post [(= nucleotides
             (set (keys %)))]}
  (merge (zipmap nucleotides (repeat 0))
         (frequencies strand)))

(def nucleotide-counts nucleotide-counts-freq)

(defn count-of-nucleotide-in-strand [n s]
  {:pre [(contains? nucleotides n)]}
  (get (nucleotide-counts-freq s) n))
