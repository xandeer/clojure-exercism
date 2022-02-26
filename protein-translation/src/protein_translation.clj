(ns protein-translation)

(defn translate-codon [codon]
  (condp re-matches codon
    #"AUG" "Methionine"
    #"UUU|UUC" "Phenylalanine"
    #"UUA|UUG" "Leucine"
    #"UCU|UCC|UCA|UCG" "Serine"
    #"UAU|UAC" "Tyrosine"
    #"UGU|UGC" "Cysteine"
    #"UGG" "Tryptophan"
    #"UAA|UAG|UGA" "STOP"))

(defn translate-rna [rna]
  (->> (clojure.string/split rna #"UAA|UAG|UGA")
       first
       (re-seq #"[A-Z]{3}")
       (map translate-codon)
       (apply vector)))

(defn translate-rna [rna]
  (->> (partition 3 rna)
       (map (partial apply str))
       (map translate-codon)
       (take-while (partial not= "STOP"))))
