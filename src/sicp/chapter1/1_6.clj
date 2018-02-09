(ns sicp.chapter1.1-6
  (:require [sicp.chapter1.1-4 :refer [sum-of-squares]]))

;;;; 1.1  The Elements of Programming

;;; 1.1.6  Conditional Expressions and Predicates

(defn abs [x]
  (cond (> x 0) x
        (== x 0) 0
        (< x 0) (- x)))

(defn abs' [x]
  (cond (< x 0) (- x)
        :else x))

(defn abs'' [x]
  (if (< x 0)
    (- x)
    x))

;; (and (> x 5) (< x 10))
;; (< 5 x 10)

(defn >=' [x y]
  (or (> x y) (== x y)))

(defn >='' [x y]
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

(def a 3)
;; => #'sicp.chapter1.1-6/a

(def b (+ a 1))
;; => #'sicp.chapter1.1-6/b

(+ a b (* a b))
;; => 19

(== a b)
;; => false

(if (and (> b a) (< b (* a b)))
  b
  a)
;; => 4

(cond (== a 4) 6
      (== b 4) (+ 6 7 a)
      :else 25)
;; => 16

(+ 2 (if (> b a) b a))
;; => 6

(* (cond (> a b) a
         (< a b) b
         :else -1)
   (+ a 1))
;; => 16

;; Exercise 1.2
(/ (+ 5 4 (- 2 (- 3 (+ 6 4/5))))
   (* 3 (- 6 2) (- 2 7)))

;; Exercise 1.3
(defn sum-of-squares-of-two-larger [x y z]
  (cond (and (>= x z) (>= y z)) (sum-of-squares x y)
        (and (>= y x) (>= z x)) (sum-of-squares y z)
        :else (sum-of-squares z x)))

;; Exercise 1.4
(defn a-plus-abs-b [a b]
  ((if (> b 0) + -) a b))

(a-plus-abs-b 1 -2)
((if (> -2 0) + -) 1 -2)
((if false + -) 1 -2)
(- 1 -2)
3

(a-plus-abs-b 1 2)
((if (> 2 0) + -) 1 2)
((if true + -) 1 2)
(+ 1 2)
3

;; Exercise 1.5
;;;; applicative-order evaluation (e.g. Clojure)
;;
;; (defn p [] (p))
;;
;; (defn test [x y]
;;   (if (== x 0)
;;     0
;;     y))
;;
;; > (test 0 (p))
;; java.lang.StackOverflowError
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
