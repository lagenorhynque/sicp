(ns sicp.chapter3.1-2-test
  (:require [clojure.test :as t]
            [sicp.chapter3.1-2 :as sut]))

;;;; 3.1  Assignment and Local State

;;; 3.1.2  The Benefits of Introducing Assignment

;; Exercise 3.5
(t/deftest estimate-pi'-test
  (t/is (< 3 (sut/estimate-pi' 10000) 4)))

;; Exercise 3.6
(t/deftest rand'-test
  (t/is (= 10 ((sut/rand' :reset) 10)))
  (t/is (= 11 (sut/rand' :generate))))
