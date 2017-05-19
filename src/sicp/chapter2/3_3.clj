(ns sicp.chapter2.3-3)

;;;; 2.3  Symbolic Data

;;; 2.3.3  Example: Representing Sets

(defn element-of-set? [x set]
  (cond
    (empty? set) false
    (= x (first set)) true
    :else (recur x (rest set))))

(defn adjoin-set [x set]
  (if (element-of-set? x set)
    set
    (cons x set)))

(defn intersection-set [set1 set2]
  (cond
    (or (empty? set1) (empty? set2)) []
    (element-of-set? (first set1) set2) (cons (first set1)
                                              (intersection-set (rest set1) set2))
    :else (intersection-set (rest set1) set2)))

;; Exercise 2.59
(defn union-set [set1 set2]
  (cond
    (empty? set1) set2
    (empty? set2) set1
    (element-of-set? (first set1) set2) (union-set (rest set1) set2)
    :else (cons (first set1)
                (union-set (rest set1) set2))))

;; Exercise 2.60
;; TODO
