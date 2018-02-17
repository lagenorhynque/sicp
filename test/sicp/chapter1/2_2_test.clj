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
