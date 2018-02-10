(ns sicp.chapter1.1-8
  (:require [sicp.chapter1.1-4 :refer [square]]
            [sicp.chapter1.1-6 :refer [abs]]
            [sicp.chapter1.1-7 :refer [average]]))

;;;; 1.1  The Elements of Programming

;;; 1.1.8  Procedures as Black-Box Abstractions

(defn sqrt [x]
  (letfn [(good-enough? [guess x]
            (< (abs (- (square guess) x)) 0.001))
          (improve [guess x]
            (average guess (/ x guess)))
          (sqrt-iter [guess x]
            (if (good-enough? guess x)
              guess
              (recur (improve guess x) x)))]
    (sqrt-iter 1.0 x)))

(defn sqrt' [x]
  (letfn [(good-enough? [guess]
            (< (abs (- (square guess) x)) 0.001))
          (improve [guess]
            (average guess (/ x guess)))
          (sqrt-iter [guess]
            (if (good-enough? guess)
              guess
              (recur (improve guess))))]
    (sqrt-iter 1.0)))
