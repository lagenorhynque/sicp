(defpackage sicp.chapter1.1-5
  (:use :cl)
  (:import-from :sicp.chapter1.1-4 f square sum-of-squares))
(in-package :sicp.chapter1.1-5)

;;;; 1.1  The Elements of Programming

;;; 1.1.5  The Substitution Model for Procedure Application

;; applicative-order evaluation
(f 5)
(sum-of-squares (+ 5 1) (* 5 2))
(+ (square 6) (square 10))
(+ (* 6 6) (* 10 10))
(+ 36 100)
136

;; normal-order evaluation
(f 5)
(sum-of-squares (+ 5 1) (* 5 2))
(+ (square (+ 5 1)) (square (* 5 2)))
(+ (* (+ 5 1) (+ 5 1)) (* (* 5 2) (* 5 2)))
(+ (* 6 6) (* 10 10))
(+ 36 100)
136
