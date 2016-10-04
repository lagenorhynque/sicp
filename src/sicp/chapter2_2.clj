(ns sicp.chapter2-2)

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
(defn reverse' [coll]
  (letfn [(rev [coll acc]
            (if (empty? coll)
              acc
              (recur (rest coll)
                     (cons (first coll) acc))))]
    (rev coll [])))

(defn deep-reverse [coll]
  (letfn [(rev [coll acc]
            (cond
              (not (seq? coll)) coll
              (empty? coll) acc
              :else (recur (rest coll)
                           (cons (deep-reverse (first coll)) acc))))]
    (rev coll [])))

(def x (list (list 1 2) (list 3 4)))
(reverse' x)
(deep-reverse x)

;; Exercise 2.28
(defn fringe [tree]
  (cond
    (not (seq? tree)) [tree]
    (empty? tree) []
    :else (concat (fringe (first tree))
                  (fringe (fnext tree)))))

(def x (list (list 1 2) (list 3 4)))
(fringe x)
(fringe (list x x))

;; Exercise 2.29
