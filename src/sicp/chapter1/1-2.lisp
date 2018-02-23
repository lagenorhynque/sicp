(defpackage sicp.chapter1.1-2
  (:use :cl))
(in-package :sicp.chapter1.1-2)

;;;; 1.1  The Elements of Programming

;;; 1.1.2  Naming and the Environment

(defparameter size 2)

size

(* 5 size)

(defparameter pi* 3.14159)

(defparameter radius 10)

(* pi* (* radius radius))

(defparameter circumference (* 2 pi* radius))

circumference
