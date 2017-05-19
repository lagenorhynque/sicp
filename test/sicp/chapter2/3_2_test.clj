(ns sicp.chapter2.3-2-test
  (:require [clojure.test :refer :all]
            [sicp.chapter2.3-2 :refer :all]))

;;;; 2.3  Symbolic Data

;;; 2.3.2  Example: Symbolic Differentiation

(deftest deriv-test
  (is (= (deriv '(+ x 3) 'x) 1))
  (is (= (deriv '(* x y) 'x) 'y))
  (is (= (deriv '(* (* x y) (+ x 3)) 'x) '(+ (* x y) (* y (+ x 3))))))

;; Exercise 2.56
(deftest deriv'-test
  (is (= (deriv' '(** x 1) 'x) 1))
  (is (= (deriv' '(** x 2) 'x) '(* 2 x)))
  (is (= (deriv' '(** x 3) 'x) '(* 3 (** x 2)))))

;; Exercise 2.57

;; Exercise 2.58
