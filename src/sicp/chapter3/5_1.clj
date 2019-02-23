(ns sicp.chapter3.5-1)

;;;; 3.5  Streams

;;; 3.5.1  Streams Are Delayed Lists

;; Exercise 3.50
(defn stream-map [proc & argstreams]
  (lazy-seq
   (if (empty? (first argstreams))
     nil
     (cons
      (apply proc (map first argstreams))
      (apply stream-map
             (cons proc (map rest argstreams)))))))

;; Exercise 3.51
(defn show [x]
  (println x)
  x)

(def x (map show (range 0 (inc 10))))
#_(nth x 5)
#_(nth x 7)

;; Exercise 3.52
(defn display-stream [s]
  (run! println s))

(def sum (atom 0))
#_@sum
(defn accum [x]
  (swap! sum + x))
#_@sum
(def seq' (map accum (range 1 (inc 20))))
#_@sum
(def y (filter even? seq'))
#_@sum
(def z (filter #(zero? (rem % 5)) seq'))
#_@sum
#_(nth y 7)
#_@sum
#_(display-stream z)
#_@sum
