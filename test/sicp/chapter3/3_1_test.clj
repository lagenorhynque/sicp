(ns sicp.chapter3.3-1-test
  (:require [clojure.test :refer :all]
            [sicp.chapter3.3-1 :refer :all]
            [sicp.common.list :refer :all]))

;;;; 3.3  Modeling with Mutable Data

;;; 3.3.1  Mutable List Structure

;; Exercise 3.16
(deftest count-pairs-test
  (let [x (kons 'a nil)
        y (kons 'b nil)
        z (kons 'c nil)]
    (set-cdr! x y)
    (set-cdr! y z)
    (is (= 3 (count-pairs x)))
    (set-car! y z)
    (is (= 4 (count-pairs x)))
    (set-car! x y)
    (is (= 7 (count-pairs x)))
    (set-car! x 'a)
    (set-car! y 'b)
    (set-cdr! z x)
    (is (thrown? StackOverflowError (count-pairs x)))))

;; Exercise 3.17
(deftest count-pairs'-test
  (let [x (kons 'a nil)
        y (kons 'b nil)
        z (kons 'c nil)]
    (set-cdr! x y)
    (set-cdr! y z)
    (is (= 3 (count-pairs' x)))
    (set-car! y z)
    (is (= 3 (count-pairs' x)))
    (set-car! x y)
    (is (= 3 (count-pairs' x)))
    (set-car! x 'a)
    (set-car! y 'b)
    (set-cdr! z x)
    (is (= 3 (count-pairs' x)))))

;; Exercise 3.18
(deftest contains-cycle?-test
  (let [x (kons 'a nil)
        y (kons 'b nil)
        z (kons 'c nil)]
    (set-cdr! x y)
    (set-cdr! y z)
    (is (false? (contains-cycle? x)))
    (set-cdr! z x)
    (is (true? (contains-cycle? x)))))

;; Exercise 3.19
(deftest contains-cycle?'-test
  (let [x (kons 'a nil)
        y (kons 'b nil)
        z (kons 'c nil)]
    (set-cdr! x y)
    (set-cdr! y z)
    (is (false? (contains-cycle?' x)))
    (set-cdr! z x)
    (is (true? (contains-cycle?' x)))))
