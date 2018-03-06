(ns sicp.chapter1.1-7
  (:require [clojure.math.numeric-tower :refer [expt]]
            [sicp.chapter1.1-4 :refer [square]]
            [sicp.chapter1.1-6 :refer [abs]]))

;;;; 1.1  The Elements of Programming

;;; 1.1.7  Example: Square Roots by Newton's Method

(defn good-enough? [guess x]
  (< (abs (- (square guess) x)) 0.001))

(defn average [x y]
  (/ (+ x y) 2))

(defn improve [guess x]
  (average guess (/ x guess)))

(defn sqrt-iter [guess x]
  (if (good-enough? guess x)
    guess
    (recur (improve guess x)
           x)))

(defn sqrt [x]
  (sqrt-iter 1.0 x))

(sqrt 9)

(sqrt (+ 100 37))

(sqrt (+ (sqrt 2) (sqrt 3)))

(square (sqrt 1000))

;; Exercise 1.6
(defn new-if [predicate then-clause else-clause]
  (cond
    predicate then-clause
    :else else-clause))

(new-if (== 2 3) 0 5)

(new-if (== 1 1) 0 5)

(defn sqrt-iter' [guess x]
  (new-if (good-enough? guess x)
          guess
          (sqrt-iter' (improve guess x)
                      x)))

;; > (sqrt-iter' 1.0 2)
;; java.lang.StackOverflowError
;;
;; Reason: Since all the arguments of `new-if` are always evaluated even if `predicate` is evaluated to false,
;;   recursion does not stop forever. You can control evaluation of arguments using macros as follows:

(defmacro new-if' [predicate then-clause else-clause]
  `(cond
     ~predicate ~then-clause
     :else ~else-clause))

(defn sqrt-iter'' [guess x]
  (new-if' (good-enough? guess x)
           guess
           (sqrt-iter'' (improve guess x)
                        x)))

(sqrt-iter'' 1.0 2)

;; Exercise 1.7
;; When applied to very small numbers,
;; e.g. (sqrt 1.0e-20)
;; returns inaccurate results.
;;
;; When applied to very large numbers,
;; e.g. (sqrt 1.0e50)
;; takes too much time to calculate results.

(defn good-enough?' [old-guess new-guess]
  (< (abs (- old-guess new-guess)) 0.001))

(defn sqrt-iter''' [old-guess new-guess x]
  (if (good-enough?' old-guess new-guess)
    new-guess
    (recur new-guess
           (improve new-guess x)
           x)))

(defn sqrt' [x]
  (sqrt-iter''' 0 1.0 x))

;; Exercise 1.8
(defn good-enough-cbrt? [guess x]
  (< (abs (- (expt guess 3) x))
     0.001))

(defn improve-cbrt [guess x]
  (/ (+ (/ x (square guess))
        (* 2 guess))
     3))

(defn cbrt-iter [guess x]
  (if (good-enough-cbrt? guess x)
    guess
    (recur (improve-cbrt guess x)
           x)))

(defn cbrt [x]
  (cbrt-iter 1.0 x))
