(ns sicp.chapter2.1-2
  (:require [sicp.chapter1.1-6 :refer [abs]]
            [sicp.chapter1.1-7 :refer [average]]
            [sicp.chapter1.2-5 :refer [gcd]]
            [sicp.common.list :refer :all]))

;;;; 2.1  Introduction to Data Abstraction

;;; 2.1.2  Abstraction Barriers

(defn make-rat [n d]
  (kons n d))

(defn numer [x]
  (let [g (gcd (car x) (cdr x))]
    (/ (car x) g)))

(defn denom [x]
  (let [g (gcd (car x) (cdr x))]
    (/ (cdr x) g)))

;; Exercise 2.2
(defn make-point [x y]
  (kons x y))
(defn x-point [p]
  (car p))
(defn y-point [p]
  (cdr p))

(defn make-segment [start end]
  (kons start end))
(defn start-segment [s]
  (car s))
(defn end-segment [s]
  (cdr s))

(defn midpoint-segment [s]
  (let [start (start-segment s)
        start-x (x-point start)
        start-y (y-point start)
        end (end-segment s)
        end-x (x-point end)
        end-y (y-point end)]
    (make-point (average start-x end-x)
                (average start-y end-y))))

(defn print-point [p]
  (printf "(%s,%s)%n" (x-point p) (y-point p)))

;; Exercise 2.3
(defn make-rectangle [p1 p2]
  (kons p1 p2))

(defn width [rect]
  (let [p1 (car rect)
        p1-x (x-point p1)
        p2 (cdr rect)
        p2-x (x-point p2)]
    (abs (- p2-x p1-x))))

(defn height [rect]
  (let [p1 (car rect)
        p1-y (y-point p1)
        p2 (cdr rect)
        p2-y (y-point p2)]
    (abs (- p2-y p1-y))))

(defn perimeter [rect]
  (+ (* 2 (width rect))
     (* 2 (height rect))))

(defn area [rect]
  (* (width rect) (height rect)))
