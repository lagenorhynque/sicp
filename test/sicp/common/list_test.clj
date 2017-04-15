(ns sicp.common.list-test
  (:require [clojure.test :refer :all]
            [sicp.common.list :refer :all]))

(deftest kons-test
  (let [x (kons 1 2)]
    (is (= 1 (car x)))
    (is (= 2 (cdr x)))
    (set-car! x 3)
    (is (= 3 (car x)))
    (is (= 2 (cdr x)))
    (set-cdr! x 4)
    (is (= 3 (car x)))
    (is (= 4 (cdr x)))))

(deftest scheme-like-list-test
  (let [xs (scheme-like-list)]
    (is (nil? xs)))
  (let [xs (scheme-like-list 1)]
    (is (= 1 (car xs)))
    (is (nil? (cdr xs))))
  (let [xs (scheme-like-list 1 2 3)]
    (is (= 1 (car xs)))
    (is (= (scheme-like-list 2 3) (cdr xs)))
    (is (= 2 (car (cdr xs))))
    (is (= (scheme-like-list 3) (cdr (cdr xs))))
    (is (= 3 (car (cdr (cdr xs)))))
    (is (nil? (cdr (cdr (cdr xs)))))))

(deftest pair?-test
  (is (true? (pair? (kons 1 2))))
  (is (false? (pair? nil)))
  (is (false? (pair? (scheme-like-list))))
  (is (true? (pair? (scheme-like-list 1))))
  (is (true? (pair? (scheme-like-list 1 2 3))))
  (is (false? (pair? [1 2 3]))))

(deftest pair-toString-test
  (is (= "(1 . 2)" (str (kons 1 2))))
  (is (= "(1 . )" (str (kons 1 nil))))
  (is (= "( . 2)" (str (kons nil 2))))
  (is (= "( . )" (str (kons nil nil))))
  (is (= "(1 . (2 . (3 . )))" (str (scheme-like-list 1 2 3)))))

(deftest pair-equals-test
  (let [x (kons 1 2)
        y (kons 1 2)
        z (kons 3 4)]
    (is (true? (= x x)))
    (is (true? (= x y)))
    (is (false? (= x z))))
  (let [xs (scheme-like-list 1 2 3)
        ys (scheme-like-list 1 2 3)
        zs (scheme-like-list 1 2 4)]
    (is (true? (= xs xs)))
    (is (true? (= xs ys)))
    (is (false? (= xs zs)))))

(deftest pair-hashCode-test
  (let [x (kons 1 2)
        y (kons 1 2)
        z (kons 3 4)]
    (is (= (hash x) (hash x)))
    (is (= (hash x) (hash y)))
    (is (not= (hash x) (hash z))))
  (let [xs (scheme-like-list 1 2 3)
        ys (scheme-like-list 1 2 3)
        zs (scheme-like-list 1 2 4)]
    (is (= (hash xs) (hash xs)))
    (is (= (hash xs) (hash ys)))
    (is (not= (hash xs) (hash zs)))))
