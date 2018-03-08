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
(defn fast-expt' [b n]
  (letfn [(expt-iter [b n a]
            (cond
              (zero? n) a
              (even? n) (recur (square b) (/ n 2) a)
              :else (recur b (dec n) (*' a b))))]
    (expt-iter b n 1)))

;; Exercise 1.17
(defn *'' [a b]
  (if (zero? b)
    0
    (+' a (*'' a (dec b)))))

(defn double' [x] (*' x 2))

(defn halve [x] (/ x 2))

(defn fast-* [a b]
  (cond
    (zero? b) 0
    (even? b) (double' (fast-* a (halve b)))
    :else (+' a (fast-* a (dec b)))))

;; Exercise 1.18
(defn fast-*' [a b]
  (letfn [(*-iter [a b p]
            (cond
              (zero? b) p
              (even? b) (recur (double' a) (halve b) p)
              :else (recur a (dec b) (+' a p))))]
    (*-iter a b 0)))

;; Exercise 1.19
;; TODO
