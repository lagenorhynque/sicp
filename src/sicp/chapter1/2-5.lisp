(defpackage sicp.chapter1.2-5
  (:use :cl))
(in-package :sicp.chapter1.2-5)

;;;; 1.2  Procedures and the Processes They Generate

;;; 1.2.5  Greatest Common Divisors

(defun gcd* (a b)
  (if (zerop b)
      a
      (gcd* b (rem a b))))

;; Exercise 1.20
;; TODO
