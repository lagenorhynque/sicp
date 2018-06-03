(ns sicp.chapter1.3-1)

;;;; 1.3  Formulating Abstractions with Higher-Order Procedures

(defn cube [x] (* x x x))

;;; 1.3.1  Procedures as Arguments

(defn sum-integers [a b]
  (if (> a b)
    0
    (+ a (sum-integers (inc a) b))))

(defn sum-cubes [a b]
  (if (> a b)
    0
    (+ (cube a) (sum-cubes (inc a) b))))

(defn pi-sum [a b]
  (if (> a b)
    0
    (+ (/ 1.0 (* a (+ a 2))) (pi-sum (+ a 4) b))))

(defn sum [term a next b]
  (if (> a b)
    0
    (+ (term a)
       (sum term (next a) next b))))

(defn sum-cubes' [a b]
  (sum cube a inc b))

(sum-cubes' 1 10)

(defn sum-integers' [a b]
  (sum identity a inc b))

(sum-integers' 1 10)

(defn pi-sum' [a b]
  (letfn [(pi-term [x]
            (/ 1.0 (* x (+ x 2))))
          (pi-next [x]
            (+ x 4))]
    (sum pi-term a pi-next b)))


(* 8 (pi-sum' 1 1000))

(defn integral [f a b dx]
  (letfn [(add-dx [x] (+ x dx))]
    (* (sum f (+ a (/ dx 2.0)) add-dx b)
       dx)))

(integral cube 0 1 0.01)
(integral cube 0 1 0.001)

;; Exercise 1.29
;; TODO

;; Exercise 1.30
;; TODO

;; Exercise 1.31
;; TODO

;; Exercise 1.32
;; TODO

;; Exercise 1.33
;; TODO
