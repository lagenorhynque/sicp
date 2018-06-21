(defpackage sicp.chapter2.1-2
  (:use :cl))
(in-package :sicp.chapter2.1-2)

;;;; 2.1  Introduction to Data Abstraction

;;; 2.1.2  Abstraction Barriers

(defun make-rat (n d)
  (cons n d))

(defun numer (x)
  (let ((g (gcd (car x) (cdr x))))
    (/ (car x) g)))

(defun denom (x)
  (let ((g (gcd (car x) (cdr x))))
    (/ (cdr x) g)))

;; Exercise 2.2
;; TODO

;; Exercise 2.3
;; TODO
