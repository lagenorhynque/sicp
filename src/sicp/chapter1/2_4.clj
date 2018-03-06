(ns sicp.chapter1.2-4
  (:require [sicp.chapter1.1-4 :refer [square]]))

;;;; 1.2  Procedures and the Processes They Generate

;;; 1.2.4  Exponentiation

(defn expt [b n]
  (if (zero? n)
    1
    (*' b (expt b (dec n)))))

(defn expt-iter [b counter product]
  (if (zero? counter)
    product
    (recur b
           (dec counter)
           (*' b product))))

(defn expt' [b n]
  (expt-iter b n 1))

(defn even?' [n]
  (zero? (rem n 2)))

(defn fast-expt [b n]
  (cond
    (zero? n) 1
    (even?' n) (square (fast-expt b (/ n 2)))
    :else (*' b (fast-expt b (dec n)))))

;; Exercise 1.16
;; TODO

;; Exercise 1.17
;; TODO

;; Exercise 1.18
;; TODO

;; Exercise 1.19
;; TODO
