(defpackage sicp.chapter1.2-6
  (:use :cl)
  (:import-from :sicp.chapter1.1-4 square))
(in-package :sicp.chapter1.2-6)

;;;; 1.2  Procedures and the Processes They Generate

;;; 1.2.6  Example: Testing for Primality

(defun dividesp (a b)
  (zerop (rem b a)))

(defun find-divisor (n test-divisor)
  (cond ((> (square test-divisor) n) n)
        ((dividesp test-divisor n) test-divisor)
        (t (find-divisor n (1+ test-divisor)))))

(defun smallest-divisor (n)
  (find-divisor n 2))

(defun primep (n)
  (= n (smallest-divisor n)))

(defun expmod (base exp m)
  (cond ((zerop exp) 1)
        ((evenp exp) (rem (square (expmod base (/ exp 2) m))
                          m))
        (t (rem (* base (expmod base (1- exp) m))
                m))))

(defun fermat-test (n)
  (labels ((try-it (a)
             (= (expmod a n n) a)))
    (try-it (1+ (random (1- n))))))

(defun fast-primep (n times)
  (cond ((zerop times) t)
        ((fermat-test n) (fast-primep n (1- times)))
        (t nil)))

;; Exercise 1.21
;; TODO

;; Exercise 1.22
;; TODO

;; Exercise 1.23
;; TODO

;; Exercise 1.24
;; TODO

;; Exercise 1.25
;; TODO

;; Exercise 1.26
;; TODO

;; Exercise 1.27
;; TODO

;; Exercise 1.28
;; TODO
