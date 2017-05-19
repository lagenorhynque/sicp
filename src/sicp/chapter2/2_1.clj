(ns sicp.chapter2.2-1)

;;;; 2.2  Hierarchical Data and the Closure Property

;;; 2.2.1  Representing Sequences

;; Exercise 2.17
(defn last-pair [coll]
  (if-let [r (next coll)]
    (recur r)
    (first coll)))

(last-pair (list 23 72 149 34))

;; Exercise 2.18
(defn reverse' [coll]
  (letfn [(rev [coll acc]
            (if (empty? coll)
              acc
              (recur (rest coll)
                     (cons (first coll) acc))))]
    (rev coll [])))

(reverse' (list 1 4 9 16 25))

;; Exercise 2.19
(def us-coins (list 50 25 10 5 1))
(def uk-coins (list 100 50 20 10 5 2 1 0.5))
;; TODO

;; Exercise 2.20
;; TODO

;; Exercise 2.21
;; TODO

;; Exercise 2.22
;; TODO

;; Exercise 2.23
;; TODO