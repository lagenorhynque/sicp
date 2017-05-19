(ns sicp.chapter2.3-1-test
  (:require [clojure.test :refer :all]
            [sicp.chapter2.3-1 :refer :all]))

;;;; 2.3  Symbolic Data

;;; 2.3.1  Quotation

(deftest memq-test
  (is (false? (memq 'apple '(pear banana prune))))
  (is (= (memq 'apple '(x (apple sauce) y apple pear)) '(apple pear))))

;; Exercise 2.54
(deftest equal?-test
  (is (true? (equal? '(this is a list) '(this is a list))))
  (is (false? (equal? '(this is a list) '(this (is a) list)))))
