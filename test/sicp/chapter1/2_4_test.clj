(ns sicp.chapter1.2-4-test
  (:require [clojure.test :as t]
            [sicp.chapter1.2-4 :as sut]))

;;;; 1.2  Procedures and the Processes They Generate

;;; 1.2.4  Exponentiation

;; Exercise 1.16
(t/deftest fast-expt'-test
  (t/is (= [1 2 4 8 16 32 64 128 256 512 1024]
           (map #(sut/fast-expt' 2 %) (range (inc 10))))))

;; Exercise 1.17
(t/deftest fast-*-test
  (t/is (= [0 2 4 6 8 10 12 14 16 18 20]
           (map sut/fast-* (repeat 2) (range (inc 10))))))

;; Exercise 1.18
(t/deftest fast-*'-test
  (t/is (= [0 2 4 6 8 10 12 14 16 18 20]
           (map sut/fast-*' (repeat 2) (range (inc 10))))))

;; Exercise 1.19
(t/deftest fib-test
  (t/is (= [0 1 1 2 3 5 8 13 21 34 55]
           (map sut/fib (range (inc 10))))))
