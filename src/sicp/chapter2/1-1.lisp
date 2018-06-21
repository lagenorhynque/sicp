(defpackage sicp.chapter2.1-1
  (:use :cl))
(in-package :sicp.chapter2.1-1)

;;;; 2.1  Introduction to Data Abstraction

;;; 2.1.1  Example: Arithmetic Operations for Rational Numbers

(defun add-rat (x y)
  (make-rat (+ (* (numer x) (denom y))
               (* (numer y) (denom x)))
            (* (denom x) (denom y))))

(defun sub-rat (x y)
  (make-rat (- (* (numer x) (denom y))
               (* (numer y) (denom x)))
            (* (denom x) (denom y))))

(defun mul-rat (x y)
  (make-rat (* (numer x) (numer y))
            (* (denom x) (denom y))))

(defun div-rat (x y)
  (make-rat (* (numer x) (denom y))
            (* (denom x) (numer y))))

(defun equal-ratp (x y)
  (= (* (numer x) (denom y))
     (* (numer y) (denom x))))

(defparameter x (cons 1 2))

(car x)

(cdr x)

(defparameter y (cons 3 4))

(defparameter z (cons x y))

(car (car z))

(car (cdr z))

(defun make-rat (n d) (cons n d))

(defun numer (x) (car x))

(defun denom (x) (cdr x))

(defun print-rat (x)
  (format t "~a/~a~%" (numer x) (denom x)))

(defparameter one-half (make-rat 1 2))
#+(or)(print-rat one-half)

(defparameter one-third (make-rat 1 3))
#+(or)(print-rat (add-rat one-half one-third))

#+(or)(print-rat (mul-rat one-half one-third))

#+(or)(print-rat (add-rat one-third one-third))

#+(or)(defun make-rat (n d)
        (let ((g (gcd n d)))
          (cons (/ n g) (/ d g))))

#+(or)(print-rat (add-rat one-third one-third))

;; Exercise 2.1
;; TODO
