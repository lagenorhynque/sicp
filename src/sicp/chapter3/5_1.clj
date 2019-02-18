(ns sicp.chapter3.5-1)

;;;; 3.5  Streams

;;; 3.5.1  Streams Are Delayed Lists

;; Exercise 3.51
(defn show [x]
  (println x)
  x)

(def x (map show (range 0 (inc 10))))

#_(nth x 5)
#_(nth x 7)
