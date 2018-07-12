(ns sicp.chapter2.1-3
  (:require [clojure.math.numeric-tower :refer [expt]]))

;;;; 2.1  Introduction to Data Abstraction

;;; 2.1.3  What Is Meant by Data?

(defn kons [x y]
  (letfn [(dispatch [m]
            (case m
              0 x
              1 y
              (throw (IllegalArgumentException.
                      (str "Argument not 0 or 1 -- CONS " m)))))]
    dispatch))

(defn car [z] (z 0))

(defn cdr [z] (z 1))

;; Exercise 2.4
(defn kons' [x y]
  (fn [m] (m x y)))
(defn car' [z]
  (z (fn [p q] p)))

;; (car' (kons' x y))
;; (car' (fn [m] (m x y)))
;; ((fn [m] (m x y)) (fn [p q] p))
;; ((fn [p q] p) x y)
;; x

(defn cdr' [z]
  (z (fn [p q] q)))

;; Exercise 2.5
(defn kons'' [x y]
  (* (expt 2 x)
     (expt 3 y)))

(defn- count-factor
  ([x n] (count-factor x n 0))
  ([x n acc]
   (if (zero? (mod x n))
     (recur (quot x n) n (inc acc))
     acc)))

(defn car'' [z]
  (count-factor z 2))

(defn cdr'' [z]
  (count-factor z 3))

;; Exercise 2.6
(def zero (fn [f] (fn [x] x)))
(defn add-1 [n]
  (fn [f] (fn [x] (f ((n f) x)))))

;; define `one`
(add-1 zero)
(fn [f] (fn [x] (f (((fn [f] (fn [x] x)) f) x))))
(fn [f] (fn [x] (f ((fn [x] x) x))))
(fn [f] (fn [x] (f x)))
(def one (fn [f] (fn [x] (f x))))

;; define `two`
(add-1 one)
(fn [f] (fn [x] (f (((fn [f] (fn [x] (f x))) f) x))))
(fn [f] (fn [x] (f ((fn [x] (f x)) x))))
(fn [f] (fn [x] (f (f x))))
(def two (fn [f] (fn [x] (f (f x)))))

;; TODO: define `+`
