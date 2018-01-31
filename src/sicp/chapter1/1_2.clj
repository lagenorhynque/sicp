(ns sicp.chapter1.1-2)

;;;; 1.1  The Elements of Programming

;;; 1.1.2  Naming and the Environment

(def size 2)

size

(* 5 size)

(def pi 3.14159)

(def radius 10)

(* pi (* radius radius))

(def circumference (* 2 pi radius))

circumference
