(ns sicp.chapter1.1-6-test
  (:require [clojure.test :as t]
            [sicp.chapter1.1-6 :as sut]))

;;;; 1.1  The Elements of Programming

;;; 1.1.6  Conditional Expressions and Predicates

;; Exercise 1.3
(t/deftest sum-of-squares-of-two-larger-test
  (t/is (= 13 (sut/sum-of-squares-of-two-larger 1 2 3)))
  (t/is (= 13 (sut/sum-of-squares-of-two-larger 1 3 2)))
  (t/is (= 13 (sut/sum-of-squares-of-two-larger 2 1 3)))
  (t/is (= 13 (sut/sum-of-squares-of-two-larger 2 3 1)))
  (t/is (= 13 (sut/sum-of-squares-of-two-larger 3 1 2)))
  (t/is (= 13 (sut/sum-of-squares-of-two-larger 3 2 1)))
  (t/is (= 8 (sut/sum-of-squares-of-two-larger 1 2 2)))
  (t/is (= 8 (sut/sum-of-squares-of-two-larger 2 1 2)))
  (t/is (= 8 (sut/sum-of-squares-of-two-larger 2 2 1)))
  (t/is (= 8 (sut/sum-of-squares-of-two-larger 2 2 2))))
