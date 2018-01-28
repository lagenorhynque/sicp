(ns sicp.chapter3.3-2-test
  (:require [clojure.test :as t]
            [sicp.chapter3.3-2 :as sut]))

;;;; 3.3  Modeling with Mutable Data

;;; 3.3.2  Representing Queues

;; Exercise 3.21
(t/deftest print-queue-test
  (let [q1 (sut/make-queue)]
    (sut/insert-queue! q1 :a)
    (t/is (= "[:a]\n" (with-out-str (sut/print-queue q1))))
    (sut/insert-queue! q1 :b)
    (t/is (= "[:a :b]\n" (with-out-str (sut/print-queue q1))))
    (sut/delete-queue! q1)
    (t/is (= "[:b]\n" (with-out-str (sut/print-queue q1))))
    (sut/delete-queue! q1)
    (t/is (= "[]\n" (with-out-str (sut/print-queue q1))))))

;; Exercise 3.22
(t/deftest make-queue'-test
  (let [q (sut/make-queue')]
    (t/is (true? ((q :empty-queue?))))
    (t/is (thrown-with-msg? IllegalArgumentException
                            #"FRONT called with an empty queue"
                            ((q :front-queue))))
    (t/is (thrown-with-msg? IllegalArgumentException
                            #"DELETE called with an empty queue"
                            ((q :delete-queue!))))
    ((q :insert-queue!) :a)
    (t/is (false? ((q :empty-queue?))))
    (t/is (= :a ((q :front-queue))))
    ((q :insert-queue!) :b)
    ((q :delete-queue!))
    (t/is (= :b ((q :front-queue))))
    ((q :delete-queue!))
    (t/is (true? ((q :empty-queue?))))
    (t/is (thrown-with-msg? IllegalArgumentException
                            #"Unknown operation"
                            ((q :dummy))))))

;; Exercise 3.23
(t/deftest deque-test
  (let [d (sut/make-deque)]
    (t/is (true? (sut/empty-deque? d)))
    (t/is (thrown-with-msg? IllegalArgumentException
                            #"FRONT called with an empty deque"
                            (sut/front-deque d)))
    (t/is (thrown-with-msg? IllegalArgumentException
                            #"REAR called with an empty deque"
                            (sut/rear-deque d)))
    (t/is (thrown-with-msg? IllegalArgumentException
                            #"DELETE called with an empty deque"
                            (sut/front-delete-deque! d)))
    (t/is (thrown-with-msg? IllegalArgumentException
                            #"DELETE called with an empty deque"
                            (sut/rear-delete-deque! d)))
    (sut/front-insert-deque! d :a)
    (t/is (false? (sut/empty-deque? d)))
    (t/is (= :a (sut/front-deque d)))
    (t/is (= :a (sut/rear-deque d)))
    (sut/rear-insert-deque! d :b)
    (t/is (= :a (sut/front-deque d)))
    (t/is (= :b (sut/rear-deque d)))
    (sut/front-delete-deque! d)
    (t/is (= :b (sut/front-deque d)))
    (t/is (= :b (sut/rear-deque d)))
    (sut/rear-delete-deque! d)
    (t/is (true? (sut/empty-deque? d)))))
