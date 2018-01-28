(ns sicp.chapter3.1-1-test
  (:require [clojure.test :as t]
            [sicp.chapter3.1-1 :as sut]))

;;;; 3.1  Assignment and Local State

;;; 3.1.1  Local State Variables

;; Exercise 3.1
(t/deftest make-accumulator-test
  (let [acc (sut/make-accumulator 5)]
    (t/is (= 15 (acc 10)))
    (t/is (= 25 (acc 10)))))

;; Exercise 3.2
(t/deftest make-monitored-test
  (let [s (sut/make-monitored #(Math/sqrt %))]
    (t/is (= 10.0 (s 100)))
    (t/is (= 1 (s :how-many-calls?)))))

;; Exercise 3.3
(t/deftest make-account'-test
  (let [acc (sut/make-account' 100 :secret-password)]
    (t/is (= 60 ((acc :secret-password :withdraw) 40)))
    (t/is (= "Incorrect password" ((acc :some-other-password :deposit) 50)))))

;; Exercise 3.4
(t/deftest make-account''-test
  (let [acc (sut/make-account'' 100 :secret-password)]
    (t/is (every? #(= % "Incorrect password")
                  (repeatedly 7 #((acc :some-other-password :withdraw) 40))))
    (t/is (= "Calling the cops..." ((acc :some-other-password :withdraw) 40)))))
