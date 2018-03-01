(defpackage sicp.chapter1.2-2
  (:use :cl))
(in-package :sicp.chapter1.2-2)

;;;; 1.2  Procedures and the Processes They Generate

;;; 1.2.2  Tree Recursion

(defun fib (n)
  (cond ((= n 0) 0)
        ((= n 1) 1)
        (t (+ (fib (- n 1))
              (fib (- n 2))))))

(defun fib-iter (a b cnt)
  (if (zerop cnt)
    b
    (fib-iter (+ a b) a (1- cnt))))

(defun fib* (n)
  (fib-iter 1 0 n))

(defun first-denomination (kinds-of-coins)
  (case kinds-of-coins
    (1 1)
    (2 5)
    (3 10)
    (4 25)
    (5 50)
    (t nil)))

(defun cc (amount kinds-of-coins)
  (cond ((zerop amount) 1)
        ((or (< amount 0) (= kinds-of-coins 0)) 0)
        (t (+ (cc amount
                  (1- kinds-of-coins))
              (cc (- amount
                     (first-denomination kinds-of-coins))
                  kinds-of-coins)))))

(defun count-change (amount)
  (cc amount 5))

(count-change 100)

;; Exercise 1.11
;; recursive process
(defun f (n)
  (if (< n 3)
      n
      (+ (f (- n 1))
         (* 2 (f (- n 2)))
         (* 3 (f (- n 3))))))

;; iterative process
;; a <- (+ a (* 2 b) (* 3 c))
;; b <- a
;; c <- b
(defun f* (n)
  (labels ((f-iter (a b c cnt)
             (if (zerop cnt)
                 c
                 (f-iter (+ a
                            (* 2 b)
                            (* 3 c))
                         a
                         b
                         (1- cnt)))))
    (f-iter 2 1 0 n)))

;; Exercise 1.12
(defun pascals-triangle (n k)
  (if (or (zerop k) (= n k))
      1
      (+ (pascals-triangle (1- n) (1- k))
         (pascals-triangle (1- n) k))))

;; Exercise 1.13
;; TODO
