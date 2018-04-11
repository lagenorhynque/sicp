(ns sicp.chapter1.2-6
  (:require [sicp.chapter1.1-4 :refer [square]]))

;;;; 1.2  Procedures and the Processes They Generate

;;; 1.2.6  Example: Testing for Primality

(defn divides? [a b]
  (zero? (rem b a)))

(defn find-divisor [n test-divisor]
  (cond
    (> (square test-divisor) n) n
    (divides? test-divisor n) test-divisor
    :else (recur n (inc test-divisor))))

(defn smallest-divisor [n]
  (find-divisor n 2))

(defn prime? [n]
  (== n (smallest-divisor n)))

(defn expmod [base exp m]
  (cond
    (zero? exp) 1
    (even? exp) (rem (square (expmod base (/ exp 2) m))
                     m)
    :else (rem (*' base (expmod base (dec exp) m))
               m)))

(defn fermat-test [n]
  (letfn [(try-it [a]
            (== (expmod a n n) a))]
    (try-it (inc (rand-int (dec n))))))

(defn fast-prime? [n times]
  (cond
    (zero? times) true
    (fermat-test n) (recur n (dec times))
    :else false))

;; Exercise 1.21
(smallest-divisor 199)
;; => 199
(smallest-divisor 1999)
;; => 1999
(smallest-divisor 19999)
;; => 7

;; Exercise 1.22
;; TODO

;; Exercise 1.23
;; TODO

;; Exercise 1.24
;; TODO

;; Exercise 1.25
;; TODO

;; Exercise 1.26
;; TODO

;; Exercise 1.27
;; TODO

;; Exercise 1.28
;; TODO
