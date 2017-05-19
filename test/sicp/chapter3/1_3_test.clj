(ns sicp.chapter3.1-3-test
  (:require [clojure.test :refer :all]
            [sicp.chapter3.1-3 :refer :all]))

;;;; 3.1  Assignment and Local State

;;; 3.1.3  The Costs of Introducing Assignment

;; Exercise 3.7
(deftest make-joint-test
  (let [peter-acc (make-account''' 100 :open-sesame)]
    (is (thrown-with-msg? IllegalArgumentException
                          #"Incorrect password"
                          (make-joint peter-acc :open-bean :rosebud)))
    (let [paul-acc (make-joint peter-acc :open-sesame :rosebud)]
      (is (= 70 ((paul-acc :rosebud :withdraw) 30)))
      (is (= 120 ((paul-acc :rosebud :deposit) 50)))
      (is (= "Incorrect password" ((paul-acc :roseblue :withdraw) 20))))))

;; Exercise 3.8
(deftest f-test
  (is (= 0 (+ (f 0) (f 1))))
  (is (= 1 (+ (f 1) (f 0)))))
