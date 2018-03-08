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
;; a' <- bq + aq + ap
;; b' <- bp + aq
;;
;; a'' <- (bp + aq)q + (bq + aq + ap)q + (bq + aq + ap)p
;; b'' <- (bp + aq)p + (bq + aq + ap)q
;;
;;   (bp + aq)q + (bq + aq + ap)q + (bq + aq + ap)p
;; = bpq + aq^2 + bq^2 + aq^2 + apq + bpq + apq + ap^2
;; = ap^2 + 2aq^2 + bq^2 + 2apq + 2bpq
;; = b(q^2 + 2pq) + a(q^2 + 2pq) + a(p^2 + q^2)
;;
;;   (bp + aq)p + (bq + aq + ap)q
;; = bp^2 + apq + bq^2 + aq^2 + apq
;; = aq^2 + bp^2 + bq^2 + 2apq
;; = b(p^2 + q^2) + a(q^2 + 2pq)
;;
;; a'' <- bq' + aq' + ap'
;; b'' <- bp' + aq'
;;
;; p' = p^2 + q^2
;; q' = q^2 + 2pq

(defn fib-iter [a b p q cnt]
  (cond
    (zero? cnt) b
    (even? cnt) (recur a
                       b
                       (+ (square p) (square q))
                       (+ (square q) (* 2 p q))
                       (/ cnt 2))
    :else (recur (+ (* b q) (* a q) (* a p))
                 (+ (* b p) (* a q))
                 p
                 q
                 (dec cnt))))

(defn fib [n]
  (fib-iter 1 0 0 1 n))
