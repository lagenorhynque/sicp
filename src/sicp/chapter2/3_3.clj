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
    (or (empty? set1) (empty? set2)) ()
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

(defn element-of-set?' [x set]
  (cond
    (empty? set) false
    (= x (first set)) true
    (< x (first set)) false
    :else (recur x (rest set))))

(defn intersection-set' [set1 set2]
  (if (or (empty? set1) (empty? set2))
    ()
    (let [x1 (first set1)
          x2 (first set2)]
      (cond
        (= x1 x2)
        (cons x1
              (intersection-set' (rest set1)
                                 (rest set2)))
        (< x1 x2)
        (intersection-set' (rest set1) set2)
        (< x2 x1)
        (intersection-set' set1 (rest set2))))))

;; Exercise 2.61
(defn adjoin-set' [x set]
  (cond
    (empty? set) (list x)
    (= x (first set)) set
    (< x (first set)) (cons x set)
    :else (cons (first set)
                (adjoin-set' x (rest set)))))

;; Exercise 2.62
(defn union-set' [set1 set2]
  (cond
    (empty? set1) set2
    (empty? set2) set1
    :else
    (let [x1 (first set1)
          x2 (first set2)]
      (cond
        (= x1 x2)
        (cons x1
              (union-set' (rest set1)
                          (rest set2)))
        (< x1 x2)
        (cons x1
              (union-set' (rest set1) set2))
        (< x2 x1)
        (cons x2
              (union-set' set1 (rest set2)))))))
