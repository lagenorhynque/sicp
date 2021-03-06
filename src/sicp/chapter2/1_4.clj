(ns sicp.chapter2.1-4
  (:require [sicp.common.list :refer :all]))

;;;; 2.1  Introduction to Data Abstraction

;;; 2.1.4  Extended Exercise: Interval Arithmetic

(declare make-interval lower-bound upper-bound)

(defn add-interval [x y]
  (make-interval (+ (lower-bound x) (lower-bound y))
                 (+ (upper-bound x) (upper-bound y))))

(defn mul-interval [x y]
  (let [p1 (* (lower-bound x) (lower-bound y))
        p2 (* (lower-bound x) (upper-bound y))
        p3 (* (upper-bound x) (lower-bound y))
        p4 (* (upper-bound x) (upper-bound y))]
    (make-interval (min p1 p2 p3 p4)
                   (max p1 p2 p3 p4))))

(defn div-interval [x y]
  (mul-interval x
                (make-interval (/ 1.0 (upper-bound y))
                               (/ 1.0 (lower-bound y)))))

;; Exercise 2.7
(defn make-interval [a b]
  (kons a b))

(defn lower-bound [interval]
  (car interval))

(defn upper-bound [interval]
  (cdr interval))

;; Exercise 2.8
(defn sub-interval [x y]
  (make-interval (- (lower-bound x) (upper-bound y))
                 (- (upper-bound x) (lower-bound y))))

;; Exercise 2.9
;; TODO

;; Exercise 2.10
;; TODO

;; Exercise 2.11
;; TODO

;; Exercise 2.12
;; TODO

;; Exercise 2.13
;; TODO

;; Exercise 2.14
;; TODO

;; Exercise 2.15
;; TODO

;; Exercise 2.16
;; TODO
