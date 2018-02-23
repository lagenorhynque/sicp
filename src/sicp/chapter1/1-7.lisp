(defpackage sicp.chapter1.1-7
  (:use :cl)
  (:import-from :sicp.chapter1.1-4 square)
  (:import-from :sicp.chapter1.1-6 abs*))
(in-package :sicp.chapter1.1-7)

;;;; 1.1  The Elements of Programming

;;; 1.1.7  Example: Square Roots by Newton's Method

(defun good-enough-p (guess x)
  (< (abs (- (square guess) x)) 0.001))

(defun average (x y)
  (/ (+ x y) 2))

(defun improve (guess x)
  (average guess (/ x guess)))

(defun sqrt-iter (guess x)
  (if (good-enough-p guess x)
      guess
      (sqrt-iter (improve guess x)
                 x)))

(defun sqrt* (x)
  (sqrt-iter 1.0 x))

(sqrt* 9)

(sqrt* (+ 100 37))

(sqrt* (+ (sqrt* 2) (sqrt* 3)))

(square (sqrt* 1000))

;; Exercise 1.6
(defun new-if (predicate then-clause else-clause)
  (cond (predicate then-clause)
        (t else-clause)))

(new-if (= 2 3) 0 5)

(new-if (= 1 1) 0 5)

(defun sqrt-iter* (guess x)
  (new-if (good-enough-p guess x)
          guess
          (sqrt-iter* (improve guess x)
                      x)))

;; > (sqrt-iter' 1.0 2)
;; unresponsive...
;;
;; Reason: Since all the arguments of `new-if` are always evaluated even if `predicate` is evaluated to false,
;;   recursion does not stop forever. You can control evaluation of arguments using macros as follows:

(defmacro new-if* (predicate then-clause else-clause)
  `(cond (,predicate ,then-clause)
         (t ,else-clause)))

(defun sqrt-iter** (guess x)
  (new-if* (good-enough-p guess x)
           guess
           (sqrt-iter** (improve guess x)
                        x)))

(sqrt-iter** 1.0 2)

;; Exercise 1.7
;; When applied to very small numbers,
;; e.g. (sqrt* 1.0e-20)
;; returns inaccurate results.
;;
;; When applied to very large numbers,
;; e.g. (sqrt* 1.0e50)
;; takes too much time to calculate results.

(defun good-enough?* (old-guess new-guess)
  (< (abs (- old-guess new-guess)) 0.001))

(defun sqrt-iter*** (old-guess new-guess x)
  (if (good-enough?* old-guess new-guess)
      new-guess
      (sqrt-iter*** new-guess
                    (improve new-guess x)
                    x)))

(defun sqrt** (x)
  (sqrt-iter*** 0 1.0 x))

;; Exercise 1.8
(defun good-enough-cbrt-p (guess x)
  (< (abs (- (expt guess 3) x))
     0.001))

(defun improve-cbrt (guess x)
  (/ (+ (/ x (square guess))
        (* 2 guess))
     3))

(defun cbrt-iter (guess x)
  (if (good-enough-cbrt-p guess x)
      guess
      (cbrt-iter (improve-cbrt guess x)
                 x)))

(defun cbrt (x)
  (cbrt-iter 1.0 x))
