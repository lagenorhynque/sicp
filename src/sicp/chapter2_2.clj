(ns sicp.chapter2-2)

;;;; 2.2  Hierarchical Data and the Closure Property

;;; 2.2.1  Representing Sequences

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

(def x' (list (list 1 2) (list 3 4)))
(reverse' x')
(deep-reverse x')

;; Exercise 2.28
(defn fringe [tree]
  (cond
    (not (seq? tree)) [tree]
    (empty? tree) []
    :else (concat (fringe (first tree))
                  (fringe (fnext tree)))))

(def x'' (list (list 1 2) (list 3 4)))
(fringe x'')
(fringe (list x'' x''))

;; Exercise 2.29

;;; 2.2.3  Sequences as Conventional Interfaces

;; Exercise 2.42
(defn queens [board-size]
  (let [pos (fn [r c]
              [r c])
        row-pos (fn [p]
                  (first p))
        column-pos (fn [p]
                     (second p))
        empty-board []
        safe? (fn [k positions]
                (let [[k-position & rest-positions] positions
                      k-row (row-pos k-position)
                      k-column (column-pos k-position)]
                  (not-any? (fn [position]
                              (let [row (row-pos position)
                                    column (column-pos position)
                                    row-diff (- k-row row)
                                    column-diff (- k-column column)]
                                (or (zero? row-diff)
                                    (zero? column-diff)
                                    (= (Math/abs row-diff) (Math/abs column-diff)))))
                            rest-positions)))
        adjoin-position (fn [new-row k rest-of-queens]
                          (cons (pos new-row k) rest-of-queens))
        queen-cols (fn queen-cols [k]
                     (if (zero? k)
                       [empty-board]
                       (filter
                        (fn [positions] (safe? k positions))
                        (mapcat
                         (fn [rest-of-queens]
                           (map (fn [new-row]
                                  (adjoin-position new-row k rest-of-queens))
                                (range 1 (inc board-size))))
                         (queen-cols (dec k))))))]
    (queen-cols board-size)))

;;; 2.2.4  Example: A Picture Language
