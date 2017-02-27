(ns sicp.chapter3-1-test
  (:require [clojure.test :refer :all]
            [sicp.chapter3-1 :refer :all]))

;;;; 3.1  Assignment and Local State

;;; 3.1.1  Local State Variables

;; Exercise 3.1
(deftest make-accumulator-test
  (let [acc (make-accumulator 5)]
    (is 15 (acc 10))
    (is 25 (acc 10))))

;; Exercise 3.2
(deftest make-monitored-test
  (let [s (make-monitored #(Math/sqrt %))]
    (is 10.0 (s 100))
    (is 1 (s :how-many-calls?))))

;; Exercise 3.3
(deftest make-account'-test
  (let [acc (make-account' 100 :secret-password)]
    (is 60 ((acc :secret-password :withdraw) 40))
    (is "Incorrect password" ((acc :some-other-password :deposit) 50))))

;; Exercise 3.4
(deftest make-account''-test
  (let [acc (make-account'' 100 :secret-password)]
    (is "Incorrect password" (do (dotimes [_ 6]
                                    ((acc :some-other-password :withdraw) 40))
                                 ((acc :some-other-password :withdraw) 40)))
    (is "Calling the cops" ((acc :some-other-password :withdraw) 40))))
