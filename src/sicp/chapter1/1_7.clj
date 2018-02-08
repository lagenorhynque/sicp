(ns sicp.chapter1.1-7
  (:require [sicp.chapter1.1-4 :refer [square]]
            [sicp.chapter1.1-6 :refer [abs]]))

;;;; 1.1  The Elements of Programming

;;; 1.1.7  Example: Square Roots by Newton's Method

(defn good-enough? [guess x]
  (< (abs (- (square guess) x)) 0.001))

(defn average [x y]
  (/ (+ x y) 2))

(defn improve [guess x]
  (average guess (/ x guess)))

(defn square-iter [guess x]
  (if (good-enough? guess x)
    guess
    (recur (improve guess x)
           x)))

(defn sqrt [x]
  (square-iter 1.0 x))

(sqrt 9)

(sqrt (+ 100 37))

(sqrt (+ (sqrt 2) (sqrt 3)))

(square (sqrt 1000))

;; Exercise 1.6
;; TODO

;; Exercise 1.7
;; TODO

;; Exercise 1.8
;; TODO
