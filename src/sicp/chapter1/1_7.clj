(ns sicp.chapter1.1-7
  (:require [sicp.chapter1.1-4 :refer [square]]
            [sicp.chapter1.1-6 :refer [abs]]))

;;;; 1.1  The Elements of Programming

;;; 1.1.7  Example: Square Roots by Newton's Method

(defn good-enough? [guess x]
  (< (abs (- (square guess) x)) 0.001))

(defn average [x y]
  (/ (+ x y) 2))

(defn improve [guess x]
  (average guess (/ x guess)))

(defn square-iter [guess x]
  (if (good-enough? guess x)
    guess
    (recur (improve guess x)
           x)))

(defn sqrt [x]
  (square-iter 1.0 x))

(sqrt 9)

(sqrt (+ 100 37))

(sqrt (+ (sqrt 2) (sqrt 3)))

(square (sqrt 1000))

;; Exercise 1.6
(defn new-if [predicate then-clause else-clause]
  (cond predicate then-clause
        :else else-clause))

(new-if (== 2 3) 0 5)

(new-if (== 1 1) 0 5)

(defn square-iter' [guess x]
  (new-if (good-enough? guess x)
          guess
          (square-iter' (improve guess x)
                        x)))

;; > (square-iter' 1.0 2)
;; java.lang.StackOverflowError
;;
;; Reason: Since all the argments of `new-if` are always evaluated even if `predicate` is evaluated to false,
;;   recursion does not stop forever. You can control evaluation of arguments using macros as follows:

(defmacro new-if' [predicate then-clause else-clause]
  `(cond ~predicate ~then-clause
         :else ~else-clause))

(defn square-iter'' [guess x]
  (new-if' (good-enough? guess x)
           guess
           (square-iter'' (improve guess x)
                          x)))

(square-iter'' 1.0 2)

;; Exercise 1.7
;; TODO

;; Exercise 1.8
;; TODO
