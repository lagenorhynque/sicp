(ns sicp.chapter3.1-2-test
  (:require [clojure.test :refer :all]
            [sicp.chapter3.1-2 :refer :all]))

;;;; 3.1  Assignment and Local State

;;; 3.1.2  The Benefits of Introducing Assignment

;; Exercise 3.5
(deftest estimate-pi'-test
  (is (< 3 (estimate-pi' 10000) 4)))

;; Exercise 3.6
(deftest rand'-test
  (is (= 10 ((rand' :reset) 10)))
  (is (= 11 (rand' :generate))))
