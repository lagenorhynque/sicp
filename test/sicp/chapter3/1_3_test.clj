(ns sicp.chapter3.1-3-test
  (:require [clojure.test :as t]
            [sicp.chapter3.1-3 :as sut]))

;;;; 3.1  Assignment and Local State

;;; 3.1.3  The Costs of Introducing Assignment

;; Exercise 3.7
(t/deftest make-joint-test
  (let [peter-acc (sut/make-account''' 100 :open-sesame)]
    (t/is (thrown-with-msg? IllegalArgumentException
                            #"Incorrect password"
                            (sut/make-joint peter-acc :open-bean :rosebud)))
    (let [paul-acc (sut/make-joint peter-acc :open-sesame :rosebud)]
      (t/is (= 70 ((paul-acc :rosebud :withdraw) 30)))
      (t/is (= 120 ((paul-acc :rosebud :deposit) 50)))
      (t/is (= "Incorrect password" ((paul-acc :roseblue :withdraw) 20))))))

;; Exercise 3.8
(t/deftest f-test
  (t/is (= 0 (+ (sut/f 0) (sut/f 1))))
  (t/is (= 1 (+ (sut/f 1) (sut/f 0)))))
