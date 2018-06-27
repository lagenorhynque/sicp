(defpackage sicp.chapter2.1-3
  (:use :cl))
(in-package :sicp.chapter2.1-3)

;;;; 2.1  Introduction to Data Abstraction

;;; 2.1.3  What Is Meant by Data?

(defun kons (x y)
  (labels ((dispatch (m)
             (case m
               (0 x)
               (1 y)
               (otherwise (error (format nil "Argument not 0 or 1 -- CONS ~a" m))))))
    #'dispatch))

(defun kar (z) (funcall z 0))

(defun kdr (z) (funcall z 1))

;; Exercise 2.4
;; TODO

;; Exercise 2.5
;; TODO

;; Exercise 2.6
;; TODO
