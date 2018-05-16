(ns sicp.chapter1.2-6-test
  (:require [clojure.test :as t]
            [sicp.chapter1.2-6 :as sut]))

;;;; 1.2  Procedures and the Processes They Generate

;;; 1.2.6  Example: Testing for Primality

;; Exercise 1.27
(t/deftest prime-by-fermat-test?-test
  (t/testing "Carmichael numbers"
    (t/is (= [true true true true true true]
             (map sut/prime-by-fermat-test?
                  [561 1105 1729 2465 2821 6601]))))
  (t/is (= [2 3 5 7 11 13 17 19]
           (->> (range 2 20)
                (map (juxt identity sut/prime-by-fermat-test?))
                (keep (fn [[n prime?]] (when prime? n)))))))

;; Exercise 1.28
(t/deftest prime-by-miller-rabin-fermat-test?-test
  (t/testing "Carmichael numbers"
    (t/is (= [false false false false false false]
             (map sut/prime-by-miller-rabin-fermat-test?
                  [561 1105 1729 2465 2821 6601]))))
  (t/is (= [2 3 5 7 11 13 17 19]
           (->> (range 2 20)
                (map (juxt identity sut/prime-by-miller-rabin-fermat-test?))
                (keep (fn [[n prime?]] (when prime? n)))))))
