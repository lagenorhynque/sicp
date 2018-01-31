(ns sicp.chapter1.1-6)

;;;; 1.1  The Elements of Programming

;;; 1.1.6  Conditional Expressions and Predicates

(defn abs [x]
  (cond (> x 0) x
        (= x 0) 0
        (< x 0) (- x)))

(defn abs' [x]
  (cond (< x 0) (- x)
        :else x))

(defn abs'' [x]
  (if (< x 0)
    (- x)
    x))

;; (and (> x 5) (< x 10))
;; (< 5 x 10)

(defn >=' [x y]
  (or (> x y) (= x y)))

(defn >='' [x y]
  (not (< x y)))

;; Exercise 1.1
10
;=> 10

(+ 5 3 4)
;=> 12

;; TODO

;; Exercise 1.2
;; TODO

;; Exercise 1.3
;; TODO

;; Exercise 1.4
;; TODO

;; Exercise 1.5
;; TODO
