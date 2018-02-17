(ns sicp.chapter1.2-2-test
  (:require [clojure.test :as t]
            [sicp.chapter1.2-2 :as sut]))

;;;; 1.2  Procedures and the Processes They Generate

;;; 1.2.2  Tree Recursion

;; Exercise 1.11
(t/deftest f-test
  (t/is (= [0 1 2 4 11 25 59 142 335 796]
           (map sut/f (range 10)))))

(t/deftest f'-test
  (t/is (= [0 1 2 4 11 25 59 142 335 796]
           (map sut/f' (range 10)))))

;; Exercise 1.12
(t/deftest pascals-triangle-test
  (t/is (= [1 1 1 1 2 1 1 3 3 1 1 4 6 4 1 1 5 10 10 5 1]
           (for [n (range (inc 5))
                 k (range (inc n))]
             (sut/pascals-triangle n k)))))
