(defpackage sicp.chapter1.3-4
  (:use :cl)
  (:import-from :sicp.chapter1.1-4 square)
  (:import-from :sicp.chapter1.1-7 average)
  (:import-from :sicp.chapter1.3-3 fixed-point))
(in-package :sicp.chapter1.3-4)

;;;; 1.3  Formulating Abstractions with Higher-Order Procedures

;;; 1.3.4  Procedures as Returned Values

(defun average-damp (f)
  (lambda (x) (average x (funcall f x))))

(funcall (average-damp #'square) 10)

(defun sqrt* (x)
  (fixed-point (average-damp (lambda (y) (/ x y)))
               1.0))

(defun cube-root (x)
  (fixed-point (average-damp (lambda (y) (/ x (square y))))
               1.0))

(defparameter dx 0.00001)

(defun deriv (g)
  (lambda (x)
    (/ (- (funcall g (+ x dx)) (funcall g x))
       dx)))

(defun cube (x) (* x x x))
(funcall (deriv #'cube) 5)

(defun newton-transform (g)
  (lambda (x)
    (- x (/ (funcall g x) (funcall (deriv g) x)))))

(defun newtons-method (g guess)
  (fixed-point (newton-transform g) guess))

(defun sqrt** (x)
  (newtons-method (lambda (y) (- (square y) x))
                  1.0))

(defun fixed-point-of-transform (g transform guess)
  (fixed-point (funcall transform g) guess))

(defun sqrt*** (x)
  (fixed-point-of-transform (lambda (y) (/ x y))
                            #'average-damp
                            1.0))

(defun sqrt**** (x)
  (fixed-point-of-transform (lambda (y) (- (square y) x))
                            #'newton-transform
                            1.0))

;; Exercise 1.40
;; TODO

;; Exercise 1.41
;; TODO

;; Exercise 1.42
;; TODO

;; Exercise 1.43
;; TODO

;; Exercise 1.44
;; TODO

;; Exercise 1.45
;; TODO

;; Exercise 1.46
;; TODO
