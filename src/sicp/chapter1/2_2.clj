(ns sicp.chapter1.2-2)

;;;; 1.2  Procedures and the Processes They Generate

;;; 1.2.2  Tree Recursion

(defn fib [n]
  (cond (== n 0) 0
        (== n 1) 1
        :else (+ (fib (- n 1))
                 (fib (- n 2)))))

(defn fib-iter [a b cnt]
  (if (zero? cnt)
    b
    (recur (+ a b) a (dec cnt))))

(defn fib' [n]
  (fib-iter 1 0 n))

(defn first-denomination [kinds-of-coins]
  (case kinds-of-coins
    1 1
    2 5
    3 10
    4 25
    5 50
    nil))

(defn cc [amount kinds-of-coins]
  (cond (zero? amount) 1
        (or (< amount 0) (= kinds-of-coins 0)) 0
        :else (+ (cc amount
                     (dec kinds-of-coins))
                 (cc (- amount
                        (first-denomination kinds-of-coins))
                     kinds-of-coins))))

(defn count-change [amount]
  (cc amount 5))

(count-change 100)

;; Exercise 1.11
;; recursive process
(defn f [n]
  (if (< n 3)
    n
    (+ (f (- n 1))
       (* 2 (f (- n 2)))
       (* 3 (f (- n 3))))))

;; iterative process
;; a <- (+ a (* 2 b) (* 3 c))
;; b <- a
;; c <- b
(defn f' [n]
  (letfn [(f-iter [a b c cnt]
            (if (zero? cnt)
              c
              (recur (+ a
                        (* 2 b)
                        (* 3 c))
                     a
                     b
                     (dec cnt))))]
    (f-iter 2 1 0 n)))

;; Exercise 1.12
(defn pascals-triangle [n k]
  {:pre [(<= 0 k n)]}
  (if (or (zero? k) (== n k))
    1
    (+ (pascals-triangle (dec n) (dec k))
       (pascals-triangle (dec n) k))))

;; Exercise 1.13
;; Fib(n) = 0                   if n = 0
;;          1                   if n = 1
;;          Fib(n-1) + Fib(n-2) otherwise
;;
;; ∮ = (1 + √5)/2
;; ∮^2 = ∮ + 1
;; ψ = (1 - √5)/2
;; ψ^2 = ψ + 1
;;
;; i) n = 0
;;   Fib(0) = 0
;;          = 0/√5
;;          = (1 - 1)/√5
;;          = (∮^0 - ψ^0)/√5
;; ii) n = 1
;;   Fib(1) = 1
;;          = √5/√5
;;          = ((1 + √5)/2 - (1 - √5)/2)/√5
;;          = (∮^1 - ψ^1)/√5
;; iii) n = k
;;   if Fib(k) = (∮^k - ψ^k)/√5,
;;   Fib(k+1) = Fib(k) + Fib(k-1)
;;            = (∮^k - ψ^k)/√5 + (∮^(k-1) - ψ^(k-1))/√5
;;            = (∮^(k+1) * 1/∮ + ∮^(k+1) * 1/∮^2 - ψ^(k+1) * 1/ψ - ψ^(k+1) * 1/ψ^2)/√5
;;            = (∮^(k+1) * (1/∮ + 1/∮^2) - ψ^(k+1) * (1/ψ + 1/ψ^2))/√5
;;            = (∮^(k+1) * (∮ + 1)/∮^2 - ψ^(k+1) * (ψ + 1)/ψ^2)/√5
;;            = (∮^(k+1) * 1 - ψ^(k+1) * 1)/√5
;;            = (∮^(k+1) - ψ^(k+1))/√5
;; ∴ Fib(n) = (∮^n - ψ^n)/√5
;;
;;      4 < 5 < 9
;; <=>  2 < √5 < 3
;; <=> -3 < -√5 < -2
;; <=> -2 < 1 - √5 < -1
;; <=> -1 < (1 - √5)/2 < -1/2
;; <=> -1 < ψ < -1/2
;; =>  -1 < ψ < 1
;; <=> |ψ| < 1
;; =>  |ψ^n| < 1
;; <=> |ψ^n/√5| < 1/√5
;; =>  |ψ^n/√5| < 1/2
;; <=> -1/2 < ψ^n/√5 < 1/2
;; <=> -1/2 < -ψ^n/√5 < 1/2
;; <=> ∮^n/√5 - 1/2 < ∮^n/√5 - ψ^n/√5 < ∮^n/√5 + 1/2
;; <=> ∮^n/√5 - 1/2 < (∮^n - ψ^n)/√5 < ∮^n/√5 + 1/2
;; <=> ∮^n/√5 - 1/2 < Fib(n) < ∮^n/√5 + 1/2
