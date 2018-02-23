(ns sicp.chapter1.2-1)

;;;; 1.2  Procedures and the Processes They Generate

;;; 1.2.1  Linear Recursion and Iteration

(defn factorial [n]
  (if (== n 1)
    1N
    (* n (factorial (dec n)))))

(factorial 6)

(defn factorial-iter [product counter max-count]
  (if (> counter max-count)
    product
    (recur (* counter product)
           (inc counter)
           max-count)))

(defn factorial' [n]
  (factorial-iter 1N 1 n))

(factorial' 6)

;; Exercise 1.9
(defn +'' [a b]
  (if (zero? a)
    b
    (inc (+'' (dec a) b))))

(+'' 4 5)
(inc (+'' (dec 4) 5))
(inc (+'' 3 5))
(inc (inc (+'' (dec 3) 5)))
(inc (inc (+'' 2 5)))
(inc (inc (inc (+'' (dec 2) 5))))
(inc (inc (inc (+'' 1 5))))
(inc (inc (inc (inc (+'' (dec 1) 5)))))
(inc (inc (inc (inc (+'' 0 5)))))
(inc (inc (inc (inc 5))))
(inc (inc (inc 6)))
(inc (inc 7))
(inc 8)
9
;; recursive process

(defn +''' [a b]
  (if (zero? a)
    b
    (recur (dec a) (inc b))))

(+''' 4 5)
(+''' (dec 4) (inc 5))
(+''' 3 6)
(+''' (dec 3) (inc 6))
(+''' 2 7)
(+''' (dec 2) (inc 7))
(+''' 1 8)
(+''' (dec 1) (inc 8))
(+''' 0 9)
9
;; iterative process

;; Exercise 1.10
(defn A [x y]
  (cond (== y 0) 0
        (== x 0) (* 2 y)
        (== y 1) 2
        :else (A (- x 1)
                 (A x (- y 1)))))

(A 1 10)
;; => 1024

(A 2 4)
;; => 65536

(A 3 3)
;; => 65536

(defn f [n] (A 0 n))
;; f(n) = 2n

(defn g [n] (A 1 n))
;; g(n) = 0   if n = 0
;;        2^n otherwise

(defn h [n] (A 2 n))
;; h(n) = 0          if n = 0
;;        2          if n = 1
;;        2 ^ h(n-1) otherwise
