(ns sicp.chapter1.3-2
  (:require [sicp.chapter1.1-4 :refer [square]]
            [sicp.chapter1.3-1 :refer [sum]]))

;;;; 1.3  Formulating Abstractions with Higher-Order Procedures

;;; 1.3.2  Constructing Procedures Using Lambda

(fn [x] (+ x 4))

(fn [x] (/ 1.0 (* x (+ x 2))))

(defn pi-sum [a b]
  (sum (fn [x] (/ 1.0 (* x (+ x 2))))
       a
       (fn [x] (+ x 4))
       b))

(defn integral [f a b dx]
  (* (sum f
          (+ a (/ dx 2.0))
          (fn [x] (+ x dx))
          b)
     dx))

(defn plus4 [x] (+ x 4))

(def plus4' (fn [x] (+ x 4)))

((fn [x y z] (+ x y (square z))) 1 2 3)

(defn f [x y]
  (letfn [(f-helper [a b]
            (+ (* x (square a))
               (* y b)
               (* a b)))]
    (f-helper (+ 1 (* x y))
              (- 1 y))))

(defn f' [x y]
  ((fn [a b]
     (+ (* x (square a))
        (* y b)
        (* a b)))
   (+ 1 (* x y))
   (- 1 y)))

(defn f'' [x y]
  (let [a (+ 1 (* x y))
        b (- 1 y)]
    (+ (* x (square a))
       (* y b)
       (* a b))))

;; Exercise 1.34
(defn f* [g]
  (g 2))

(f* square)

(f* (fn [z] (* z (+ z 1))))

;; (f* f*)
;; => ClassCastException java.base/java.lang.Long cannot be cast to clojure.lang.IFn
