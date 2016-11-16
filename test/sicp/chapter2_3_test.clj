(ns sicp.chapter2-3-test
  (:require [clojure.test :refer :all]
            [sicp.chapter2-3 :refer :all]))

;;;; 2.3  Symbolic Data

;;; 2.3.1  Quotation

(deftest memq-test
  (is (false? (memq 'apple '(pear banana prune))))
  (is (= (memq 'apple '(x (apple sauce) y apple pear)) '(apple pear))))

;; Exercise 2.54
(deftest equal?-test
  (is (true? (equal? '(this is a list) '(this is a list))))
  (is (false? (equal? '(this is a list) '(this (is a) list)))))

;;; 2.3.2  Example: Symbolic Differentiation

;;; 2.3.3  Example: Representing Sets

;;; 2.3.4  Example: Huffman Encoding Trees
