(ns sicp.chapter2.3-3-test
  (:require [clojure.test :as t]
            [sicp.chapter2.3-3 :as sut]))

;;;; 2.3  Symbolic Data

;;; 2.3.3  Example: Representing Sets

(t/deftest element-of-set?-test
  (t/is (false? (sut/element-of-set? 1 [])))
  (t/is (true? (sut/element-of-set? 1 [1 2 3])))
  (t/is (true? (sut/element-of-set? 3 [1 2 3])))
  (t/is (false? (sut/element-of-set? 2 [1 3]))))

(t/deftest adjoin-set-test
  (t/is (= #{1} (set (sut/adjoin-set 1 []))))
  (t/is (= #{1 2} (set (sut/adjoin-set 2 [1])))))

(t/deftest intersection-set-test
  (t/is (= #{} (set (sut/intersection-set [] []))))
  (t/is (= #{} (set (sut/intersection-set [1] []))))
  (t/is (= #{} (set (sut/intersection-set [] [1]))))
  (t/is (= #{2} (set (sut/intersection-set [1 2 3] [2]))))
  (t/is (= #{2} (set (sut/intersection-set [2] [1 2 3])))))

(t/deftest union-set-test
  (t/is (= #{} (set (sut/union-set [] []))))
  (t/is (= #{1} (set (sut/union-set [1] []))))
  (t/is (= #{1} (set (sut/union-set [] [1]))))
  (t/is (= #{1 2 3} (set (sut/union-set [1 3] [2]))))
  (t/is (= #{1 2 3} (set (sut/union-set [2] [1 3])))))
