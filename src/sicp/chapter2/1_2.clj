(ns sicp.chapter2.1-2
  (:require [sicp.chapter1.2-5 :refer [gcd]]
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
;; TODO

;; Exercise 2.3
;; TODO
