(ns sicp.chapter3.3-2-test
  (:require [clojure.test :refer :all]
            [sicp.chapter3.3-2 :refer :all]))

;;;; 3.3  Modeling with Mutable Data

;;; 3.3.2  Representing Queues

;; Exercise 3.21
(deftest print-queue-test
  (let [q1 (make-queue)]
    (insert-queue! q1 :a)
    (is (= "[:a]\n" (with-out-str (print-queue q1))))
    (insert-queue! q1 :b)
    (is (= "[:a :b]\n" (with-out-str (print-queue q1))))
    (delete-queue! q1)
    (is (= "[:b]\n" (with-out-str (print-queue q1))))
    (delete-queue! q1)
    (is (= "[]\n" (with-out-str (print-queue q1))))))

;; Exercise 3.22
(deftest make-queue'-test
  (let [q (make-queue')]
    (is (true? ((q :empty-queue?))))
    (is (thrown-with-msg? IllegalArgumentException
                          #"FRONT called with an empty queue"
                          ((q :front-queue))))
    (is (thrown-with-msg? IllegalArgumentException
                          #"DELETE called with an empty queue"
                          ((q :delete-queue!))))
    ((q :insert-queue!) :a)
    (is (false? ((q :empty-queue?))))
    (is (= :a ((q :front-queue))))
    ((q :insert-queue!) :b)
    ((q :delete-queue!))
    (is (= :b ((q :front-queue))))
    ((q :delete-queue!))
    (is (true? ((q :empty-queue?))))
    (is (thrown-with-msg? IllegalArgumentException
                          #"Unknown operation"
                          ((q :dummy))))))

;; Exercise 3.23
(deftest deque-test
  (let [d (make-deque)]
    (is (true? (empty-deque? d)))
    (is (thrown-with-msg? IllegalArgumentException
                          #"FRONT called with an empty deque"
                          (front-deque d)))
    (is (thrown-with-msg? IllegalArgumentException
                          #"REAR called with an empty deque"
                          (rear-deque d)))
    (is (thrown-with-msg? IllegalArgumentException
                          #"DELETE called with an empty deque"
                          (front-delete-deque! d)))
    (is (thrown-with-msg? IllegalArgumentException
                          #"DELETE called with an empty deque"
                          (rear-delete-deque! d)))
    (front-insert-deque! d :a)
    (is (false? (empty-deque? d)))
    (is (= :a (front-deque d)))
    (is (= :a (rear-deque d)))
    (rear-insert-deque! d :b)
    (is (= :a (front-deque d)))
    (is (= :b (rear-deque d)))
    (front-delete-deque! d)
    (is (= :b (front-deque d)))
    (is (= :b (rear-deque d)))
    (rear-delete-deque! d)
    (is (true? (empty-deque? d)))))
