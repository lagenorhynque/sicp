(ns sicp.chapter2.2-2
  (:require [sicp.chapter1.1-4 :refer [square]]
            [sicp.chapter2.2-1 :refer [reverse']]))

;;;; 2.2  Hierarchical Data and the Closure Property

;;; 2.2.2  Hierarchical Structures

;; Exercise 2.24
(list 1 (list 2 (list 3 4)))
;=> (1 (2 (3 4)))

;; Exercise 2.25
(first (nfirst (nnext '(1 3 (5 7) 9))))

(ffirst '((7)))

(fnext (fnext (fnext (fnext (fnext (fnext '(1 (2 (3 (4 (5 (6 7))))))))))))

;; Exercise 2.26
(def x (list 1 2 3))
(def y (list 4 5 6))

(concat x y)
;=> (1 2 3 4 5 6)

(cons x y)
;=> ((1 2 3) 4 5 6)

(list x y)
;=> ((1 2 3) (4 5 6))

;; Exercise 2.27
(defn deep-reverse [coll]
  (letfn [(rev [coll acc]
            (cond
              (not (seq? coll)) coll
              (empty? coll) acc
              :else (recur (rest coll)
                           (cons (deep-reverse (first coll)) acc))))]
    (rev coll [])))

(def x' (list (list 1 2) (list 3 4)))
(reverse' x')
(deep-reverse x')

;; Exercise 2.28
(defn fringe [tree]
  (letfn [(fri [coll acc]
            (cond
              (not (seq? coll)) (cons coll acc)
              (empty? coll) acc
              :else (recur (rest coll)
                           (fri (first coll) acc))))]
    (reverse (fri tree []))))

(def x'' (list (list 1 2) (list 3 4)))
(fringe x'')
(fringe (list x'' x''))

;; Exercise 2.29
;; TODO

;; Exercise 2.30
(defn square-tree [tree]
  (map (fn [t]
         (if (not (seq? t))
           (square t)
           (square-tree t)))
       tree))

;; Exercise 2.31
(defn tree-map [f tree]
  (map (fn [t]
         (if (not (seq? t))
           (f t)
           (tree-map f t)))
       tree))

;; Exercise 2.32
(defn subsets [s]
  (if (empty? s)
    (list ())
    (let [rest' (subsets (rest s))]
      (concat rest' (map (fn [x] (cons (first s) x)) rest')))))
