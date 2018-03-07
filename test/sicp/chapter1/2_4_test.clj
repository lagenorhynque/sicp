(ns sicp.chapter1.2-4-test
  (:require [clojure.test :as t]
            [sicp.chapter1.2-4 :as sut]))

;;;; 1.2  Procedures and the Processes They Generate

;;; 1.2.4  Exponentiation

;; Exercies 1.16
(t/deftest fast-expt'-test
  (t/is (= [1 2 4 8 16 32 64 128 256 512 1024]
           (map #(sut/fast-expt' 2 %) (range (inc 10))))))
