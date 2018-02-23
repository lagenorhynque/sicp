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
;; TODO

;; Exercise 1.12
;; TODO

;; Exercise 1.13
;; TODO
