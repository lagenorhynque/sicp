(ns sicp.chapter1.1-7-test
  (:require [clojure.test :as t]
            [sicp.chapter1.1-7 :as sut]))

;;;; 1.1  The Elements of Programming

;;; 1.1.7  Example: Square Roots by Newton's Method

;; Exercise 1.8
(t/deftest cbrt-test
  (t/is (== 1 (Math/floor (sut/cbrt 1))))
  (t/is (== 2 (Math/floor (sut/cbrt 8))))
  (t/is (== 3 (Math/floor (sut/cbrt 27)))))
