(ns sicp.chapter1.2-5)

;;;; 1.2  Procedures and the Processes They Generate

;;; 1.2.5  Greatest Common Divisors

(defn gcd [a b]
  (if (zero? b)
    a
    (recur b (rem a b))))

;; Exercise 1.20
;; TODO
