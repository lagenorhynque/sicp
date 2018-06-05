(ns sicp.chapter1.3-3
  (:require [clojure.math.numeric-tower :refer [abs]]
            [sicp.chapter1.1-7 :refer [average]]))

;;;; 1.3  Formulating Abstractions with Higher-Order Procedures

;;; 1.3.3  Procedures as General Methods

(defn close-enough? [x y]
  (< (abs (- x y)) 0.001))

(defn search [f neg-point pos-point]
  (let [midpoint (average neg-point pos-point)]
    (if (close-enough? neg-point pos-point)
      midpoint
      (let [test-value (f midpoint)]
        (cond
          (pos? test-value) (recur f neg-point midpoint)
          (neg? test-value) (recur f midpoint pos-point)
          :else midpoint)))))

(defn half-interval-method [f a b]
  (let [a-value (f a)
        b-value (f b)]
    (cond
      (and (neg? a-value) (pos? b-value)) (search f a b)
      (and (neg? b-value) (pos? a-value)) (search f b a)
      :else (throw (IllegalArgumentException.
                    (format "Values are not of oppsite sign: %s %s" a b))))))

(half-interval-method #(Math/sin %) 2.0 4.0)

(half-interval-method (fn [x] (- (* x x x) (* 2 x) 3))
                      1.0
                      2.0)

(def tolerance 0.00001)

(defn fixed-point [f first-guess]
  (letfn [(close-enough? [v1 v2]
            (< (abs (- v1 v2)) tolerance))
          (try-it [guess]
            (let [nxt (f guess)]
              (if (close-enough? guess nxt)
                nxt
                (recur nxt))))]
    (try-it first-guess)))

(fixed-point #(Math/cos %) 1.0)

(fixed-point (fn [y] (+ (Math/sin y) (Math/cos y)))
             1.0)

(defn sqrt [x]
  (fixed-point (fn [y] (average y (/ x y)))
               1.0))

;; Exercise 1.35
;; TODO

;; Exercise 1.36
;; TODO

;; Exercise 1.37
;; TODO

;; Exercise 1.38
;; TODO

;; Exercise 1.39
;; TODO
