(defpackage sicp.chapter1.2-4
  (:use :cl)
  (:import-from :sicp.chapter1.1-4 square))
(in-package :sicp.chapter1.2-4)

;;;; 1.2  Procedures and the Processes They Generate

;;; 1.2.4  Exponentiation

(defun expt* (b n)
  (if (zerop n)
      1
      (* b (expt* b (1- n)))))

(defun expt-iter (b counter product)
  (if (zerop counter)
      product
      (expt-iter b
                 (1- counter)
                 (* b product))))

(defun expt** (b n)
  (expt-iter b n 1))

(defun evenp* (n)
  (zerop (rem n 2)))

(defun fast-expt (b n)
  (cond ((zerop n) 1)
        ((evenp* n) (square (fast-expt b (/ n 2))))
        (t (* b (fast-expt b (1- n))))))

;; Exercise 1.16
;; TODO

;; Exercise 1.17
;; TODO

;; Exercise 1.18
;; TODO

;; Exercise 1.19
;; TODO
