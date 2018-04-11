(ns sicp.chapter1.2-5)

;;;; 1.2  Procedures and the Processes They Generate

;;; 1.2.5  Greatest Common Divisors

(defn gcd [a b]
  (if (zero? b)
    a
    (recur b (rem a b))))

;; Exercise 1.20
;; normal-order evaluation
(gcd 206 40)
(gcd 40 (rem 206 40))
;; (if (zero? (rem 206 40)) => 1
(gcd (rem 206 40)
     (rem 40
          (rem 206 40)))
;; (if (zero? (rem 40
;;                 (rem 206 40))) => 2
(gcd (rem 40
          (rem 206 40))
     (rem (rem 206 40)
          (rem 40
               (rem 206 40))))
;; (if (zero? (rem (rem 206 40)
;;                 (rem 40
;;                      (rem 206 40)))) => 4
(gcd (rem (rem 206 40)
          (rem 40
               (rem 206 40)))
     (rem (rem 40
               (rem 206 40))
          (rem (rem 206 40)
               (rem 40 (rem 206 40)))))
;; (if (zero? (rem (rem 40
;;                      (rem 206 40))
;;                 (rem (rem 206 40)
;;                      (rem 40 (rem 206 40))))) => 7
;;   (rem (rem 206 40)
;;        (rem 40
;;             (rem 206 40))) => 4
;; 18 `rem` operations are performed.

;; applicative-order evaluation
(gcd 206 40)
;; (gcd 40 (rem 206 40)) => 1
(gcd 40 6)
;; (gcd 6 (rem 40 6)) => 1
(gcd 6 4)
;; (gcd 4 (rem 6 4)) => 1
(gcd 4 2)
;; (gcd 2 (rem 4 2)) => 1
(gcd 2 0)
;; 4 `rem` operations are performed.
