(defpackage sicp.chapter1.1-8
  (:use :cl)
  (:import-from :sicp.chapter1.1-4 square)
  (:import-from :sicp.chapter1.1-6 abs*)
  (:import-from :sicp.chapter1.1-7 average))
(in-package :sicp.chapter1.1-8)

;;;; 1.1  The Elements of Programming

;;; 1.1.8  Procedures as Black-Box Abstractions

(defun sqrt* (x)
  (labels ((good-enough-p (guess x)
             (< (abs (- (square guess) x)) 0.001))
           (improve (guess x)
             (average guess (/ x guess)))
           (sqrt-iter (guess x)
             (if (good-enough-p guess x)
                 guess
                 (sqrt-iter (improve guess x) x))))
    (sqrt-iter 1.0 x)))

(defun sqrt** (x)
  (labels ((good-enough-p (guess)
             (< (abs (- (square guess) x)) 0.001))
           (improve (guess)
             (average guess (/ x guess)))
           (sqrt-iter (guess)
             (if (good-enough-p guess)
                 guess
                 (sqrt-iter (improve guess)))))
    (sqrt-iter 1.0)))
