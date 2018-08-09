(ns sicp.chapter2.2-1
  (:require [sicp.common.list :refer :all]))

;;;; 2.2  Hierarchical Data and the Closure Property

;;; 2.2.1  Representing Sequences

;; Exercise 2.17
(defn last-pair [coll]
  (if-let [r (next coll)]
    (recur r)
    (first coll)))

(last-pair (list 23 72 149 34))

(defn last-pair' [pair]
  (if (nil? (cdr pair))
    pair
    (recur (cdr pair))))

;; Exercise 2.18
(defn reverse' [coll]
  (letfn [(rev [coll acc]
            (if (empty? coll)
              acc
              (recur (rest coll)
                     (cons (first coll) acc))))]
    (rev coll [])))

(reverse' (list 1 4 9 16 25))

(defn reverse'' [l]
  (letfn [(rev [l acc]
            (if (nil? l)
              acc
              (recur (cdr l)
                     (kons (car l) acc))))]
    (rev l nil)))

(defn reverse''' [l]
  (loop [l l
         acc nil]
    (if (nil? l)
      acc
      (recur (cdr l)
             (kons (car l) acc)))))

;; Exercise 2.19
(def us-coins (list 50 25 10 5 1))
(def uk-coins (list 100 50 20 10 5 2 1 0.5))
;; TODO

;; Exercise 2.20
(defn same-parity [x & xs]
  (letfn [(same? [n]
            (= (mod x 2) (mod n 2)))]
    (cons x (filter same? xs))))

(defn my-filter [f coll]
  (lazy-seq
   (when-let [s (seq coll)]
     (if (f (first s))
       (cons (first s)
             (my-filter f (rest s)))
       (my-filter f (rest s))))))

;; Exercise 2.21
;; TODO

;; Exercise 2.22
;; TODO

;; Exercise 2.23
(defn for-each [f coll]
  (if (empty? coll)
    nil
    (do (f (first coll))
        (recur f (rest coll)))))
