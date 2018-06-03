(defpackage sicp.chapter1.3-2
  (:use :cl)
  (:import-from :sicp.chapter1.1-4 square)
  (:import-from :sicp.chapter1.3-1 sum))
(in-package :sicp.chapter1.3-2)

;;;; 1.3  Formulating Abstractions with Higher-Order Procedures

;;; 1.3.2  Constructing Procedures Using Lambda

(lambda (x) (+ x 4))

(lambda (x) (/ 1.0 (* x (+ x 2))))

(defun pi-sum (a b)
  (sum (lambda (x) (/ 1.0 (* x (+ x 2))))
       a
       (lambda (x) (+ x 4))
       b))

(defun integral (f a b dx)
  (* (sum f
          (+ a (/ dx 2.0))
          (lambda (x) (+ x dx))
          b)
     dx))

(defun plus4 (x) (+ x 4))

(defparameter plus4* (lambda (x) (+ x 4)))

(funcall (lambda (x y z) (+ x y (square z))) 1 2 3)

(defun f (x y)
  (labels ((f-helper (a b)
             (+ (* x (square a))
                (* y b)
                (* a b))))
    (f-helper (+ 1 (* x y))
              (- 1 y))))

(defun f* (x y)
  (funcall (lambda (a b)
             (+ (* x (square a))
                (* y b)
                (* a b)))
           (+ 1 (* x y))
           (- 1 y)))

(defun f** (x y)
  (let ((a (+ 1 (* x y)))
        (b (- 1 y)))
    (+ (* x (square a))
       (* y b)
       (* a b))))

;; Exercise 1.34
(defun f*** (g)
  (funcall g 2))

(f*** #'square)

(f*** (lambda (z) (* z (+ z 1))))

;; (f*** #'f***)
;; => #<TYPE-ERROR expected-type: (OR FUNCTION SYMBOL) datum: 2>
