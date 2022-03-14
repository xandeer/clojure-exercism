(ns beer-song)

(defn verse
  "Returns the nth verse of the song."
  [num]
  (condp = num
    0 "No more bottles of beer on the wall, no more bottles of beer.
Go to the store and buy some more, 99 bottles of beer on the wall.\n"
    1 "1 bottle of beer on the wall, 1 bottle of beer.
Take it down and pass it around, no more bottles of beer on the wall.\n"
    2 "2 bottles of beer on the wall, 2 bottles of beer.
Take one down and pass it around, 1 bottle of beer on the wall.\n"
    (format
     "%d bottles of beer on the wall, %d bottles of beer.
Take one down and pass it around, %d bottles of beer on the wall.\n"
     num num (dec num))))

(def sentences
  {:howmany "%s of beer on the wall, %s of beer.\n"
   :take "Take %s down and pass it around, %s of beer on the wall.\n"
   :buysome "Go to the store and buy some more, 99 bottles of beer on the wall.\n"})

(def bottles
  {0 "No more bottles"
   1 "1 bottle"
   'n "%d bottles"})

(defn bottle-s [n]
  (or (bottles n) (format (bottles 'n) n)))

(defn takeone [p n]
  (format (:take sentences) p (bottle-s (dec n))))

(defn howmany [n]
  (format (:howmany sentences)
          (bottle-s n)
          (clojure.string/lower-case (bottle-s n))))

(defmulti verse identity)

(defmethod verse 0 [n]
  (str (howmany n)
       (:buysome sentences)))

(defmethod verse 1 [n]
  (str (howmany n)
       (takeone "it" (bottle-s n))))

(defmethod verse :default [n]
  (str (howmany n)
       (takeone "one" n)))

(defn sing
  "Given a start and an optional end, returns all verses in this interval. If
  end is not given, the whole song from start is sung."
  ([start] (sing start 0))
  ([start end]
   (->> (range start (dec end) -1)
        (map verse)
        (clojure.string/join "\n"))))
