(defpackage sicp.chapter1.2-1
  (:use :cl))
(in-package :sicp.chapter1.2-1)

;;;; 1.2  Procedures and the Processes They Generate

;;; 1.2.1  Linear Recursion and Iteration

(defun factorial (n)
  (if (= n 1)
      1
      (* n (factorial (1- n)))))

(factorial 6)

(defun factorial-iter (product counter max-count)
  (if (> counter max-count)
      product
      (factorial-iter (* counter product)
                      (1+ counter)
                      max-count)))

(defun factorial* (n)
  (factorial-iter 1 1 n))

(factorial* 6)

;; Exercise 1.9
(defun +* (a b)
  (if (zerop a)
      b
      (1+ (+* (1- a) b))))

(+* 4 5)
(1+ (+* (1- 4) 5))
(1+ (+* 3 5))
(1+ (1+ (+* (1- 3) 5)))
(1+ (1+ (+* 2 5)))
(1+ (1+ (1+ (+* (1- 2) 5))))
(1+ (1+ (1+ (+* 1 5))))
(1+ (1+ (1+ (1+ (+* (1- 1) 5)))))
(1+ (1+ (1+ (1+ (+* 0 5)))))
(1+ (1+ (1+ (1+ 5))))
(1+ (1+ (1+ 6)))
(1+ (1+ 7))
(1+ 8)
9
;; recursive process

(defun +** (a b)
  (if (zerop a)
      b
      (+** (1- a) (1+ b))))

(+** 4 5)
(+** (1- 4) (1+ 5))
(+** 3 6)
(+** (1- 3) (1+ 6))
(+** 2 7)
(+** (1- 2) (1+ 7))
(+** 1 8)
(+** (1- 1) (1+ 8))
(+** 0 9)
9
;; iterative process

;; Exercise 1.10
(defun A (x y)
  (cond ((= y 0) 0)
        ((= x 0) (* 2 y))
        ((= y 1) 2)
        (t (A (- x 1)
              (A x (- y 1))))))

(A 1 10)
;; => 1024

(A 2 4)
;; => 65536

(A 3 3)
;; => 65536

(defun f (n) (A 0 n))
;; f(n) = 2n

(defun g (n) (A 1 n))
;; g(n) = 0   if n = 0
;;        2^n otherwise

(defun h (n) (A 2 n))
;; h(n) = 0          if n = 0
;;        2          if n = 1
;;        2 ^ h(n-1) otherwise
