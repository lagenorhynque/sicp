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
(defun fast-expt* (b n)
  (labels ((expt-iter (b n a)
             (cond ((zerop n) a)
                   ((evenp n) (expt-iter (square b) (/ n 2) a))
                   (t (expt-iter b (1- n) (* a b))))))
    (expt-iter b n 1)))

;; Exercise 1.17
(defun my-* (a b)
  (if (zerop b)
      0
      (+ a (my-* a (1- b)))))

(defun double (x) (* x 2))

(defun halve (x) (/ x 2))

(defun fast-* (a b)
  (cond ((zerop b) 0)
        ((evenp b) (double (fast-* a (halve b))))
        (t (+ a (fast-* a (1- b))))))

;; Exercise 1.18
(defun fast-** (a b)
  (labels ((*-iter (a b p)
             (cond ((zerop b) p)
                   ((evenp b) (*-iter (double a) (halve b) p))
                   (t (*-iter a (1- b) (+ a p))))))
    (*-iter a b 0)))

;; Exercise 1.19
;; TODO
