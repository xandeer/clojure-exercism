(ns clock)

(defn clock->string [[h m]]
  (format "%02d:%02d" h m))

(defn clock [hours minutes]
  [(-> (quot minutes 60)
         (#(if (neg? minutes) (dec %) %))
         (+ hours)
         (mod 24))
   (mod minutes 60)])

(defn add-time [[h m] t]
  (clock h (+ m t)))

(defn- norm [m] (mod m 1440))

(defn clock [h m]
  (norm (+ (* h 60) m)))

(defn add-time [c m]
  (norm (+ c m)))

(defn clock->string [c]
  (format "%02d:%02d" (quot c 60) (mod c 60)))
