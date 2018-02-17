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
  (if (< n 3) n
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
;; TODO

;; Exercise 1.13
;; TODO
