(defpackage sicp.chapter1.3-3
  (:use :cl)
  (:import-from :sicp.chapter1.1-7 average))
(in-package :sicp.chapter1.3-3)

;;;; 1.3  Formulating Abstractions with Higher-Order Procedures

;;; 1.3.3  Procedures as General Methods

(defun close-enoughp (x y)
  (< (abs (- x y)) 0.001))

(defun search-root (f neg-point pos-point)
  (let ((midpoint (average neg-point pos-point)))
    (if (close-enoughp neg-point pos-point)
        midpoint
        (let ((test-value (funcall f midpoint)))
          (cond ((plusp test-value) (search-root f neg-point midpoint))
                ((minusp test-value) (search-root f midpoint pos-point))
                (t midpoint))))))

(defun half-interval-method (f a b)
  (let ((a-value (funcall f a))
        (b-value (funcall f b)))
    (cond ((and (minusp a-value) (plusp b-value)) (search-root f a b))
          ((and (minusp b-value) (plusp a-value)) (search-root f b a))
          (t (error (format nil "Values are not of oppsite sign: ~a ~a" a b))))))

(half-interval-method #'sin 2.0 4.0)

(half-interval-method (lambda (x) (- (* x x x) (* 2 x) 3))
                      1.0
                      2.0)

(defparameter tolerance 0.00001)

(defun fixed-point (f first-guess)
  (labels ((close-enoughp (v1 v2)
             (< (abs (- v1 v2)) tolerance))
           (try (guess)
             (let ((nxt (funcall f guess)))
               (if (close-enoughp guess nxt)
                   nxt
                   (try nxt)))))
    (try first-guess)))

(fixed-point #'cos 1.0)

(fixed-point (lambda (y) (+ (sin y) (cos y)))
             1.0)

(defun sqrt* (x)
  (fixed-point (lambda (y) (average y (/ x y)))
               1.0))

;; Exercise 1.35
;; TODO

;; Exercise 1.36
;; TODO

;; Exercise 1.37
;; TODO

;; Exercise 1.38
;; TODO

;; Exercise 1.39
;; TODO
