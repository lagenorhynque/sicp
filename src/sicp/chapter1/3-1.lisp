(defpackage sicp.chapter1.3-1
  (:use :cl))
(in-package :sicp.chapter1.3-1)

;;;; 1.3  Formulating Abstractions with Higher-Order Procedures

(defun cube (x) (* x x x))

;;; 1.3.1  Procedures as Arguments

(defun sum-integers (a b)
  (if (> a b)
      0
      (+ a (sum-integers (1+ a) b))))

(defun sum-cubes (a b)
  (if (> a b)
      0
      (+ (cube a) (sum-cubes (1+ a) b))))

(defun pi-sum (a b)
  (if (> a b)
      0
      (+ (/ 1.0 (* a (+ a 2))) (pi-sum (+ a 4) b))))

(defun sum (term a next b)
  (if (> a b)
      0
      (+ (funcall term a)
         (sum term (funcall next a) next b))))

(defun sum-cubes* (a b)
  (sum #'cube a #'1+ b))

(sum-cubes* 1 10)

(defun sum-integers* (a b)
  (sum #'identity a #'1+ b))

(sum-integers* 1 10)

(defun pi-sum* (a b)
  (labels ((pi-term (x)
             (/ 1.0 (* x (+ x 2))))
           (pi-next (x)
             (+ x 4)))
    (sum #'pi-term a #'pi-next b)))

(* 8 (pi-sum* 1 1000))

(defun integral (f a b dx)
  (labels ((add-dx (x) (+ x dx)))
    (* (sum f (+ a (/ dx 2.0)) #'add-dx b)
       dx)))

(integral #'cube 0 1 0.01)
(integral #'cube 0 1 0.001)

;; Exercise 1.29
;; TODO

;; Exercise 1.30
;; TODO

;; Exercise 1.31
;; TODO

;; Exercise 1.32
;; TODO

;; Exercise 1.33
;; TODO
