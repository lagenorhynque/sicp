(ns sicp.chapter2.3-3-test
  (:require [clojure.test :refer :all]
            [sicp.chapter2.3-3 :refer :all]))

;;;; 2.3  Symbolic Data

;;; 2.3.3  Example: Representing Sets

(deftest element-of-set?-test
  (is (false? (element-of-set? 1 [])))
  (is (true? (element-of-set? 1 [1 2 3])))
  (is (true? (element-of-set? 3 [1 2 3])))
  (is (false? (element-of-set? 2 [1 3]))))

(deftest adjoin-set-test
  (is (= #{1} (set (adjoin-set 1 []))))
  (is (= #{1 2} (set (adjoin-set 2 [1])))))

(deftest intersection-set-test
  (is (= #{} (set (intersection-set [] []))))
  (is (= #{} (set (intersection-set [1] []))))
  (is (= #{} (set (intersection-set [] [1]))))
  (is (= #{2} (set (intersection-set [1 2 3] [2]))))
  (is (= #{2} (set (intersection-set [2] [1 2 3])))))

(deftest union-set-test
  (is (= #{} (set (union-set [] []))))
  (is (= #{1} (set (union-set [1] []))))
  (is (= #{1} (set (union-set [] [1]))))
  (is (= #{1 2 3} (set (union-set [1 3] [2]))))
  (is (= #{1 2 3} (set (union-set [2] [1 3])))))
