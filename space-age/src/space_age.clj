(ns space-age)

(defn- by-earth [s r]
  (/ (/ s 31557600) r))

(defn on-mercury [s]
  (by-earth s 0.2408467))

(defn on-venus [s]
  (by-earth s 0.61519726))

(defn on-earth [s]
  (by-earth s 1))

(defn on-mars [s]
  (by-earth s 1.8808158))

(defn on-jupiter [s]
  (by-earth s 11.862615))

(defn on-saturn [s]
  (by-earth s 29.447498))

(defn on-uranus [s]
  (by-earth s 84.016846))

(defn on-neptune [s]
  (by-earth s 164.79132))

(def year-seconds (* 365.25 24 60 60))

(defmacro defperiod
  [planet ratio]
  `(defn ~(symbol (str "on-" planet))
     [seconds#]
     (/ seconds# (* year-seconds ~ratio))))

(defperiod "mercury" 0.2408467)
(defperiod "venus" 0.61519726)
(defperiod "earth" 1)
(defperiod "mars" 1.8808158)
(defperiod "jupiter" 11.862615)
(defperiod "saturn" 29.447498)
(defperiod "uranus" 84.016846)
(defperiod "neptune" 164.79132)

(def ratios
  {:mercury 0.2408467
   :venus 0.61519726
   :earth 1
   :mars 1.8808158
   :jupiter 11.862615
   :saturn 29.447498
   :uranus 84.016846
   :neptune 164.79132})

(defmacro on-planet-fn
  [planet ratio]
  `(intern *ns*
           (symbol (str "on-" (name ~planet)))
           (fn [seconds#]
             (/ seconds# (* year-seconds ~ratio)))))

(doseq [[planet ratio] ratios]
  (on-planet-fn planet ratio))
