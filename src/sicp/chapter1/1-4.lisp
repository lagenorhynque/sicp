(defpackage sicp.chapter1.1-4
  (:use :cl))
(in-package :sicp.chapter1.1-4)

;;;; 1.1  The Elements of Programming

;;; 1.1.4  Compound Procedures

(defun square (x) (* x x))

(square 21)

(square (+ 2 5))

(square (square 3))

(defun sum-of-squares (x y)
  (+ (square x) (square y)))

(sum-of-squares 3 4)

(defun f (a)
  (sum-of-squares (+ a 1) (* a 2)))

(f 5)
