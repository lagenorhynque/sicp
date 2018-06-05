(ns sicp.chapter1.3-4
  (:require [sicp.chapter1.1-4 :refer [square]]
            [sicp.chapter1.1-7 :refer [average]]
            [sicp.chapter1.3-3 :refer [fixed-point]]))

;;;; 1.3  Formulating Abstractions with Higher-Order Procedures

;;; 1.3.4  Procedures as Returned Values

(defn average-damp [f]
  (fn [x] (average x (f x))))

((average-damp square) 10)

(defn sqrt [x]
  (fixed-point (average-damp (fn [y] (/ x y)))
               1.0))

(defn cube-root [x]
  (fixed-point (average-damp (fn [y] (/ x (square y))))
               1.0))

(def dx 0.00001)

(defn deriv [g]
  (fn [x]
    (/ (- (g (+ x dx)) (g x))
       dx)))

(defn cube [x] (* x x x))
((deriv cube) 5)

(defn newton-transform [g]
  (fn [x]
    (- x (/ (g x) ((deriv g) x)))))

(defn newtons-method [g guess]
  (fixed-point (newton-transform g) guess))

(defn sqrt' [x]
  (newtons-method (fn [y] (- (square y) x))
                  1.0))

(defn fixed-point-of-transform [g transform guess]
  (fixed-point (transform g) guess))

(defn sqrt'' [x]
  (fixed-point-of-transform (fn [y] (/ x y))
                            average-damp
                            1.0))

(defn sqrt''' [x]
  (fixed-point-of-transform (fn [y] (- (square y) x))
                            newton-transform
                            1.0))

;; Exercise 1.40
;; TODO

;; Exercise 1.41
;; TODO

;; Exercise 1.42
;; TODO

;; Exercise 1.43
;; TODO

;; Exercise 1.44
;; TODO

;; Exercise 1.45
;; TODO

;; Exercise 1.46
;; TODO
