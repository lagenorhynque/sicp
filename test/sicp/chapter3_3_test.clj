(ns sicp.chapter3-3-test
  (:require [clojure.test :refer :all]
            [sicp.chapter3-3 :refer :all]
            [sicp.common.list :refer :all]))

;;;; 3.3  Modeling with Mutable Data

;;; 3.3.1  Mutable List Structure

;; Exercise 3.16
(deftest count-pairs-test
  (let [x (kons 'a nil)
        y (kons 'b nil)
        z (kons 'c nil)]
    (set-cdr! x y)
    (set-cdr! y z)
    (is (= 3 (count-pairs x)))
    (set-car! y z)
    (is (= 4 (count-pairs x)))
    (set-car! x y)
    (is (= 7 (count-pairs x)))
    (set-car! x 'a)
    (set-car! y 'b)
    (set-cdr! z x)
    (is (thrown? StackOverflowError (count-pairs x)))))

;; Exercise 3.17
(deftest count-pairs'-test
  (let [x (kons 'a nil)
        y (kons 'b nil)
        z (kons 'c nil)]
    (set-cdr! x y)
    (set-cdr! y z)
    (is (= 3 (count-pairs' x)))
    (set-car! y z)
    (is (= 3 (count-pairs' x)))
    (set-car! x y)
    (is (= 3 (count-pairs' x)))
    (set-car! x 'a)
    (set-car! y 'b)
    (set-cdr! z x)
    (is (= 3 (count-pairs' x)))))

;; Exercise 3.18
(deftest contains-cycle?-test
  (let [x (kons 'a nil)
        y (kons 'b nil)
        z (kons 'c nil)]
    (set-cdr! x y)
    (set-cdr! y z)
    (is (false? (contains-cycle? x)))
    (set-cdr! z x)
    (is (true? (contains-cycle? x)))))

;; Exercise 3.19
(deftest contains-cycle?'-test
  (let [x (kons 'a nil)
        y (kons 'b nil)
        z (kons 'c nil)]
    (set-cdr! x y)
    (set-cdr! y z)
    (is (false? (contains-cycle?' x)))
    (set-cdr! z x)
    (is (true? (contains-cycle?' x)))))

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

;;; 3.3.3  Representing Tables

;; Exercise 3.24
(deftest make-table''-test
  (let [t (make-table'' :same-key? ==)]
    (is (false? ((t :lookup-proc) 1 2)))
    ((t :insert-proc!) 1 2 :a)
    (is (= :a ((t :lookup-proc) 1 2)))
    (is (= :a ((t :lookup-proc) 1.0 2.0)))
    (is (false? ((t :lookup-proc) 1.0 2.1)))))

;; Exercise 3.25
(deftest multi-dimensional-table-test
  (let [t (make-table)]
    (is (false? (lookup'' [:a] t)))
    (is (false? (lookup'' [:b] t)))
    (is (false? (lookup'' [:a :b] t)))
    (is (false? (lookup'' [:a :b :c] t)))
    (is (false? (lookup'' [:a :b :d] t)))
    (insert!'' [:a] 1 t)
    (is (= 1 (lookup'' [:a] t)))
    (is (false? (lookup'' [:b] t)))
    (is (false? (lookup'' [:a :b] t)))
    (is (false? (lookup'' [:a :b :c] t)))
    (is (false? (lookup'' [:a :b :d] t)))
    (insert!'' [:b] 2 t)
    (is (= 1 (lookup'' [:a] t)))
    (is (= 2 (lookup'' [:b] t)))
    (is (false? (lookup'' [:a :b] t)))
    (is (false? (lookup'' [:a :b :c] t)))
    (is (false? (lookup'' [:a :b :d] t)))
    (insert!'' [:a :b] 3 t)
    (is (= 1 (lookup'' [:a] t)))
    (is (= 2 (lookup'' [:b] t)))
    (is (= 3 (lookup'' [:a :b] t)))
    (is (false? (lookup'' [:a :b :c] t)))
    (is (false? (lookup'' [:a :b :d] t)))
    (insert!'' [:a :b :c] 4 t)
    (is (= 1 (lookup'' [:a] t)))
    (is (= 2 (lookup'' [:b] t)))
    (is (= 3 (lookup'' [:a :b] t)))
    (is (= 4 (lookup'' [:a :b :c] t)))
    (is (false? (lookup'' [:a :b :d] t)))
    (insert!'' [:a :b :d] 5 t)
    (is (= 1 (lookup'' [:a] t)))
    (is (= 2 (lookup'' [:b] t)))
    (is (= 3 (lookup'' [:a :b] t)))
    (is (= 4 (lookup'' [:a :b :c] t)))
    (is (= 5 (lookup'' [:a :b :d] t)))
    (insert!'' [:a :b :c] 6 t)
    (is (= 1 (lookup'' [:a] t)))
    (is (= 2 (lookup'' [:b] t)))
    (is (= 3 (lookup'' [:a :b] t)))
    (is (= 6 (lookup'' [:a :b :c] t)))
    (is (= 5 (lookup'' [:a :b :d] t)))))

;;; 3.3.4  A Simulator for Digital Circuits

;;; 3.3.5  Propagation of Constraints
