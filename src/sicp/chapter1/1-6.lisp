(defpackage sicp.chapter1.1-6
  (:use :cl)
  (:import-from :sicp.chapter1.1-4 sum-of-squares))
(in-package :sicp.chapter1.1-6)

;;;; 1.1  The Elements of Programming

;;; 1.1.6  Conditional Expressions and Predicates

(defun abs* (x)
  (cond ((> x 0) x)
        ((= x 0) 0)
        ((< x 0) (- x))))

(defun abs** (x)
  (cond ((< x 0) (- x))
        (t x)))

(defun abs*** (x)
  (if (< x 0)
      (- x)
      x))

;; (and (> x 5) (< x 10))
;; (< 5 x 10)

(defun >=* (x y)
  (or (> x y) (= x y)))

(defun >=** (x y)
  (not (< x y)))

;; Exercise 1.1
10
;; => 10

(+ 5 3 4)
;; => 12

(- 9 1)
;; => 8

(/ 6 2)
;; => 3

(+ (* 2 4) (- 4 6))
;; => 6

(defparameter a 3)
;; => A

(defparameter b (+ a 1))
;; => B

(+ a b (* a b))
;; => 19

(= a b)
;; => NIL

(if (and (> b a) (< b (* a b)))
    b
    a)
;; => 4

(cond ((= a 4) 6)
      ((= b 4) (+ 6 7 a))
      (t 25))
;; => 16

(+ 2 (if (> b a) b a))
;; => 6

(* (cond ((> a b) a)
         ((< a b) b)
         (t -1))
   (+ a 1))
;; => 16

;; Exercise 1.2
(/ (+ 5 4 (- 2 (- 3 (+ 6 4/5))))
   (* 3 (- 6 2) (- 2 7)))

;; Exercise 1.3
(defun sum-of-squares-of-two-larger (x y z)
  (cond ((and (>= x z) (>= y z)) (sum-of-squares x y))
        ((and (>= y x) (>= z x)) (sum-of-squares y z))
        (t (sum-of-squares z x))))

;; Exercise 1.4
(defun a-plus-abs-b (a b)
  (funcall (if (> b 0) #'+ #'-) a b))

(a-plus-abs-b 1 -2)
(funcall (if (> -2 0) #'+ #'-) 1 -2)
(funcall (if nil #'+ #'-) 1 -2)
(- 1 -2)
3

(a-plus-abs-b 1 2)
(funcall (if (> 2 0) #'+ #'-) 1 2)
(funcall (if t #'+ #'-) 1 2)
(+ 1 2)
3

;; Exercise 1.5
;;;; applicative-order evaluation (e.g. Common Lisp)
;;
;; (defun p () (p))
;;
;; (defun test (x y)
;;   (if (= x 0)
;;       0
;;       y))
;;
;; > (test 0 (p))
;; unresponsive...
;;
;;;; normal-order evaluation (e.g. Haskell)
;;
;; p = p
;;
;; test x y = if x == 0
;;     then 0
;;     else y
;;
;; > test 0 p
;; 0
