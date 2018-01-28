(ns sicp.chapter3.3-1-test
  (:require [clojure.test :as t]
            [sicp.chapter3.3-1 :as sut]
            [sicp.common.list :refer :all]))

;;;; 3.3  Modeling with Mutable Data

;;; 3.3.1  Mutable List Structure

;; Exercise 3.16
(t/deftest count-pairs-test
  (let [x (kons 'a nil)
        y (kons 'b nil)
        z (kons 'c nil)]
    (set-cdr! x y)
    (set-cdr! y z)
    (t/is (= 3 (sut/count-pairs x)))
    (set-car! y z)
    (t/is (= 4 (sut/count-pairs x)))
    (set-car! x y)
    (t/is (= 7 (sut/count-pairs x)))
    (set-car! x 'a)
    (set-car! y 'b)
    (set-cdr! z x)
    (t/is (thrown? StackOverflowError (sut/count-pairs x)))))

;; Exercise 3.17
(t/deftest count-pairs'-test
  (let [x (kons 'a nil)
        y (kons 'b nil)
        z (kons 'c nil)]
    (set-cdr! x y)
    (set-cdr! y z)
    (t/is (= 3 (sut/count-pairs' x)))
    (set-car! y z)
    (t/is (= 3 (sut/count-pairs' x)))
    (set-car! x y)
    (t/is (= 3 (sut/count-pairs' x)))
    (set-car! x 'a)
    (set-car! y 'b)
    (set-cdr! z x)
    (t/is (= 3 (sut/count-pairs' x)))))

;; Exercise 3.18
(t/deftest contains-cycle?-test
  (let [x (kons 'a nil)
        y (kons 'b nil)
        z (kons 'c nil)]
    (set-cdr! x y)
    (set-cdr! y z)
    (t/is (false? (sut/contains-cycle? x)))
    (set-cdr! z x)
    (t/is (true? (sut/contains-cycle? x)))))

;; Exercise 3.19
(t/deftest contains-cycle?'-test
  (let [x (kons 'a nil)
        y (kons 'b nil)
        z (kons 'c nil)]
    (set-cdr! x y)
    (set-cdr! y z)
    (t/is (false? (sut/contains-cycle?' x)))
    (set-cdr! z x)
    (t/is (true? (sut/contains-cycle?' x)))))
