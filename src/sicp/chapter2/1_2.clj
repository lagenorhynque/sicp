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

;; Exercise 2.3'
(defn point [x y]
  #:point{:x x
          :y y})

(defn vect [p1 p2]
  #:vect{:x (- (:point/x p2) (:point/x p1))
         :y (- (:point/y p2) (:point/y p1))})

(defn dist [p1 p2]
  (let [dx (- (:point/x p2) (:point/x p1))
        dy (- (:point/y p2) (:point/y p1))]
    (Math/sqrt (+ (Math/pow dx 2)
                  (Math/pow dy 2)))))

(defn rectangle [pa po pb]
  #:rectangle{:len-a (dist po pa)
              :len-b (dist po pb)
              :vect-a (vect po pa)
              :vect-b (vect po pb)})

(defn perimeter* [{:rectangle/keys [len-a len-b]}]
  (+ (* 2 len-a) (* 2 len-b)))

(defn area* [{:rectangle/keys [vect-a vect-b]}]
  (Math/abs (- (* (:vect/x vect-a) (:vect/y vect-b))
               (* (:vect/y vect-a) (:vect/x vect-b)))))

#_(let [p1 (point 0 0)
        p2 (point -1 1)
        p3 (point 0 2)
        rect (rectangle p1 p2 p3)]
    {:perimeter (perimeter* rect)
     :area (area* rect)})
