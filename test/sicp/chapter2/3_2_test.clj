(ns sicp.chapter2.3-2-test
  (:require [clojure.test :as t]
            [sicp.chapter2.3-2 :as sut]))

;;;; 2.3  Symbolic Data

;;; 2.3.2  Example: Symbolic Differentiation

(t/deftest deriv-test
  (t/is (= 1 (sut/deriv '(+ x 3) 'x)))
  (t/is (= 'y (sut/deriv '(* x y) 'x)))
  (t/is (= '(+ (* x y) (* y (+ x 3))) (sut/deriv '(* (* x y) (+ x 3)) 'x))))

;; Exercise 2.56
(t/deftest deriv'-test
  (t/is (= 1 (sut/deriv' '(** x 1) 'x)))
  (t/is (= '(* 2 x) (sut/deriv' '(** x 2) 'x)))
  (t/is (= '(* 3 (** x 2)) (sut/deriv' '(** x 3) 'x))))

;; Exercise 2.57

;; Exercise 2.58
