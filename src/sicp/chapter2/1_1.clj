(ns sicp.chapter2.1-1
  (:require [sicp.chapter1.2-5 :refer [gcd]]
            [sicp.common.list :refer :all]))

;;;; 2.1  Introduction to Data Abstraction

;;; 2.1.1  Example: Arithmetic Operations for Rational Numbers

(declare make-rat numer denom)

(defn add-rat [x y]
  (make-rat (+ (* (numer x) (denom y))
               (* (numer y) (denom x)))
            (* (denom x) (denom y))))

(defn sub-rat [x y]
  (make-rat (- (* (numer x) (denom y))
               (* (numer y) (denom x)))
            (* (denom x) (denom y))))

(defn mul-rat [x y]
  (make-rat (* (numer x) (numer y))
            (* (denom x) (denom y))))

(defn div-rat [x y]
  (make-rat (* (numer x) (denom y))
            (* (denom x) (numer y))))

(defn equal-rat? [x y]
  (== (* (numer x) (denom y))
      (* (numer y) (denom x))))

(def x (kons 1 2))

(car x)

(cdr x)

(def y (kons 3 4))

(def z (kons x y))

(car (car z))

(car (cdr z))

(defn make-rat [n d] (kons n d))

(defn numer [x] (car x))

(defn denom [x] (cdr x))

(defn print-rat [x]
  (println (format "%s/%s" (numer x) (denom x))))

(def one-half (make-rat 1 2))
#_(print-rat one-half)

(def one-third (make-rat 1 3))
#_(print-rat (add-rat one-half one-third))

#_(print-rat (mul-rat one-half one-third))

#_(print-rat (add-rat one-third one-third))

#_(defn make-rat [n d]
    (let [g (gcd n d)]
      (kons (/ n g) (/ d g))))

#_(print-rat (add-rat one-third one-third))

;; Exercise 2.1
;; TODO
