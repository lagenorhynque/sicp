(ns sicp.chapter2.3-1-test
  (:require [clojure.test :as t]
            [sicp.chapter2.3-1 :as sut]))

;;;; 2.3  Symbolic Data

;;; 2.3.1  Quotation

(t/deftest memq-test
  (t/is (false? (sut/memq 'apple '(pear banana prune))))
  (t/is (= '(apple pear) (sut/memq 'apple '(x (apple sauce) y apple pear)))))

;; Exercise 2.54
(t/deftest equal?-test
  (t/is (true? (sut/equal? '(this is a list) '(this is a list))))
  (t/is (false? (sut/equal? '(this is a list) '(this (is a) list)))))
