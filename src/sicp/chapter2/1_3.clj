(ns sicp.chapter2.1-3)

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
;; TODO

;; Exercise 2.5
;; TODO

;; Exercise 2.6
;; TODO
