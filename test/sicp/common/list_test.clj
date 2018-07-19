(ns sicp.common.list-test
  (:require [clojure.test :as t]
            [sicp.common.list :as sut]))

(t/deftest kons-test
  (let [x (sut/kons 1 2)]
    (t/is (= 1 (sut/car x)))
    (t/is (= 2 (sut/cdr x)))
    (sut/set-car! x 3)
    (t/is (= 3 (sut/car x)))
    (t/is (= 2 (sut/cdr x)))
    (sut/set-cdr! x 4)
    (t/is (= 3 (sut/car x)))
    (t/is (= 4 (sut/cdr x)))))

(t/deftest lst-test
  (let [xs (sut/lst)]
    (t/is (nil? xs)))
  (let [xs (sut/lst 1)]
    (t/is (= 1 (sut/car xs)))
    (t/is (nil? (sut/cdr xs))))
  (let [xs (sut/lst 1 2 3)]
    (t/is (= 1 (sut/car xs)))
    (t/is (= (sut/lst 2 3) (sut/cdr xs)))
    (t/is (= 2 (sut/car (sut/cdr xs))))
    (t/is (= (sut/lst 3) (sut/cdr (sut/cdr xs))))
    (t/is (= 3 (sut/car (sut/cdr (sut/cdr xs)))))
    (t/is (nil? (sut/cdr (sut/cdr (sut/cdr xs)))))))

(t/deftest pair?-test
  (t/is (true? (sut/pair? (sut/kons 1 2))))
  (t/is (false? (sut/pair? nil)))
  (t/is (false? (sut/pair? (sut/lst))))
  (t/is (true? (sut/pair? (sut/lst 1))))
  (t/is (true? (sut/pair? (sut/lst 1 2 3))))
  (t/is (false? (sut/pair? [1 2 3]))))

(t/deftest pair-toString-test
  (t/is (= "(1 . 2)" (str (sut/kons 1 2))))
  (t/is (= "(1 . )" (str (sut/kons 1 nil))))
  (t/is (= "( . 2)" (str (sut/kons nil 2))))
  (t/is (= "( . )" (str (sut/kons nil nil))))
  (t/is (= "(1 . (2 . (3 . )))" (str (sut/lst 1 2 3)))))

(t/deftest pair-equals-test
  (let [x (sut/kons 1 2)
        y (sut/kons 1 2)
        z (sut/kons 3 4)]
    (t/is (true? (= x x)))
    (t/is (true? (= x y)))
    (t/is (false? (= x z))))
  (let [xs (sut/lst 1 2 3)
        ys (sut/lst 1 2 3)
        zs (sut/lst 1 2 4)]
    (t/is (true? (= xs xs)))
    (t/is (true? (= xs ys)))
    (t/is (false? (= xs zs)))))

(t/deftest pair-hashCode-test
  (let [x (sut/kons 1 2)
        y (sut/kons 1 2)
        z (sut/kons 3 4)]
    (t/is (= (hash x) (hash x)))
    (t/is (= (hash x) (hash y)))
    (t/is (not= (hash x) (hash z))))
  (let [xs (sut/lst 1 2 3)
        ys (sut/lst 1 2 3)
        zs (sut/lst 1 2 4)]
    (t/is (= (hash xs) (hash xs)))
    (t/is (= (hash xs) (hash ys)))
    (t/is (not= (hash xs) (hash zs)))))
