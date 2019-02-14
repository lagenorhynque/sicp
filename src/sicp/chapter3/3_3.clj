(ns sicp.chapter3.3-3
  (:require [dorothy.core :as d]
            [sicp.common.graph :refer [create-node]]
            [sicp.common.list :refer :all]
            [sicp.common.string :refer [strip-margin]]))

;;;; 3.3  Modeling with Mutable Data

;;; 3.3.3  Representing Tables

(defn assok [key records]
  (cond
    (nil? records) false
    (= key (caar records)) (car records)
    :else (recur key (cdr records))))

(defn lookup [key table]
  (if-let [record (assok key (cdr table))]
    (cdr record)
    false))

(defn insert! [key value table]
  (if-let [record (assok key (cdr table))]
    (set-cdr! record value)
    (set-cdr! table
              (kons (kons key value) (cdr table))))
  :ok)

(defn make-table []
  (lst '*table*))

(defn lookup' [key-1 key-2 table]
  (if-let [subtable (assok key-1 (cdr table))]
    (if-let [record (assok key-2 (cdr subtable))]
      (cdr record)
      false)
    false))

(defn insert!' [key-1 key-2 value table]
  (if-let [subtable (assok key-1 (cdr table))]
    (if-let [record (assok key-2 (cdr subtable))]
      (set-cdr! record value)
      (set-cdr! subtable
                (kons (kons key-2 value)
                      (cdr subtable))))
    (set-cdr! table
              (kons (lst key-1
                         (kons key-2 value))
                    (cdr table))))
  :ok)

(defn make-table' []
  (let [local-table (lst '*table*)
        lookup (fn [key-1 key-2]
                 (if-let [subtable (assok key-1 (cdr local-table))]
                   (if-let [record (assok key-2 (cdr subtable))]
                     (cdr record)
                     false)
                   false))
        insert! (fn [key-1 key-2 value]
                  (if-let [subtable (assok key-1 (cdr local-table))]
                    (if-let [record (assok key-2 (cdr subtable))]
                      (set-cdr! record value)
                      (set-cdr! subtable
                                (kons (kons key-2 value)
                                      (cdr subtable))))
                    (set-cdr! local-table
                              (kons (lst key-1
                                         (kons key-2 value))
                                    (cdr local-table))))
                  :ok)
        dispatch (fn [m]
                   (case m
                     :lookup-proc lookup
                     :insert-proc! insert!
                     (throw (IllegalArgumentException.
                             (str "Unknown operation -- TABLE " m)))))]
    dispatch))

;; Exercise 3.24
(defn make-table'' [& {:keys [same-key?] :or {same-key? =}}]
  (let [local-table (lst '*table*)
        assok (fn assok [key records]
                (cond
                  (nil? records) false
                  (same-key? key (caar records)) (car records)
                  :else (recur key (cdr records))))
        lookup (fn [key-1 key-2]
                 (if-let [subtable (assok key-1 (cdr local-table))]
                   (if-let [record (assok key-2 (cdr subtable))]
                     (cdr record)
                     false)
                   false))
        insert! (fn [key-1 key-2 value]
                  (if-let [subtable (assok key-1 (cdr local-table))]
                    (if-let [record (assok key-2 (cdr subtable))]
                      (set-cdr! record value)
                      (set-cdr! subtable
                                (kons (kons key-2 value)
                                      (cdr subtable))))
                    (set-cdr! local-table
                              (kons (lst key-1
                                         (kons key-2 value))
                                    (cdr local-table))))
                  :ok)
        dispatch (fn [m]
                   (case m
                     :lookup-proc lookup
                     :insert-proc! insert!
                     (throw (IllegalArgumentException.
                             (str "Unknown operation -- TABLE " m)))))]
    dispatch))

;; Exercise 3.25
(defn lookup'' [keys table]
  (loop [keys keys
         table table]
    (if (empty? keys)
      (car table)
      (if-let [subtable (assok (first keys) (cdr table))]
        (recur (rest keys) (cdr subtable))
        false))))

(defn insert!'' [keys value table]
  (loop [keys keys
         table table]
    (if (empty? keys)
      (set-car! table value)
      (if-let [subtable (assok (first keys) (cdr table))]
        (recur (rest keys) (cdr subtable))
        (let [subtable (lst (first keys) false)]
          (set-cdr! table (kons subtable (cdr table)))
          (recur (rest keys) (cdr subtable))))))
  :ok)

;; Exercise 3.26
(defn new-node [key]
  (lst key nil nil false))
(defn node-key [node]
  (car node))
(defn node-left [node]
  (cadr node))
(defn node-right [node]
  (caddr node))
(defn node-subtree [node]
  (cdddr node))
(defn set-node-left! [node value]
  (set-car! (cdr node) value))
(defn set-node-right! [node value]
  (set-car! (cddr node) value))

(defn search-node [key tree]
  (if (nil? tree)
    false
    (let [k (node-key tree)]
      (cond
        (neg? (compare key k)) (recur key (node-left tree))
        (pos? (compare key k)) (recur key (node-right tree))
        :else tree))))

(defn lookup''' [keys table]
  (loop [keys keys
         table table]
    (if (empty? keys)
      (car table)
      (if-let [subtable (search-node (first keys) (cdr table))]
        (recur (rest keys) (node-subtree subtable))
        false))))

(defn insert-node! [node tree]
  (if (nil? tree)
    node
    (let [nk (node-key node)
          tk (node-key tree)]
      (cond
        (neg? (compare nk tk)) (set-node-left!
                                tree
                                (insert-node! node (node-left tree)))
        (pos? (compare nk tk)) (set-node-right!
                                tree
                                (insert-node! node (node-right tree))))
      tree)))

(defn insert!''' [keys value table]
  (loop [keys keys
         table table]
    (if (empty? keys)
      (set-car! table value)
      (if-let [subtable (search-node (first keys) (cdr table))]
        (recur (rest keys) (node-subtree subtable))
        (let [subtable (new-node (first keys))]
          (set-cdr! table (insert-node! subtable (cdr table)))
          (recur (rest keys) (node-subtree subtable))))))
  :ok)

;; Exercise 3.27
(defn fib [n]
  (case n
    0 0
    1 1
    (+ (fib (- n 1))
       (fib (- n 2)))))

(defn memoise [f]
  (let [table (make-table)]
    (fn [x]
      (let [previously-computed-result (lookup x table)]
        (or previously-computed-result
            (let [result (f x)]
              (insert! x result table)
              result))))))

(def memo-fib
  (memoise (fn [n]
             (case n
               0 0
               1 1
               (+ (memo-fib (- n 1))
                  (memo-fib (- n 2)))))))

;; cf. resources/public/img/chapter3_3/exercise3-27_memo-fib.png
(def memo-fib-3
  (-> [(d/node-attrs {:shape :record})
       [:g (create-node "global env"
                        (strip-margin "memoise
                                      |memo-fib"))]
       (d/node-attrs {:shape :note})
       [:t (create-node "table"
                        (strip-margin "1 -> 1
                                      |0 -> 0
                                      |2 -> 1
                                      |3 -> 2"))]
       [:e1 (create-node "E1"
                         (strip-margin "x: 3
                                       |
                                       |(+ (mem-fib 2) (mem-fib 1))
                                       |-> insert table"))]
       [:e2 (create-node "E2"
                         (strip-margin "x: 2
                                       |
                                       |(+ (mem-fib 1) (mem-fib 0))
                                       |-> insert table"))]
       [:e3 (create-node "E3"
                         (strip-margin "x: 1
                                       |
                                       |1
                                       |-> insert table"))]
       [:e4 (create-node "E4"
                         (strip-margin "x: 0
                                       |
                                       |0
                                       |-> insert table"))]
       [:e5 (create-node "E5"
                         (strip-margin "x: 1
                                       |
                                       |<- lookup table"))]

       (d/subgraph
        [[:node (create-node
                 (strip-margin "(let ((previously-computed-result (lookup x table)))
                               |  (or previously-computed-result
                               |      (let ((result (f x)))
                               |        (insert! x result table)
                               |        result)))"))]
         :c1 :c2 :c3 :c4 :c5])

       [:t :g]
       [:e1 :g]
       [:e2 :g]
       [:e3 :g]
       [:e4 :g]
       [:e5 :g]

       (d/edge-attrs {:dir :none})
       [:c1 :e1]
       [:c2 :e2]
       [:c3 :e3]
       [:c4 :e4]
       [:c5 :e5]]
      d/digraph
      d/dot))

;;; 3.3.4  A Simulator for Digital Circuits

(declare get-signal set-signal! add-action!
         after-delay inverter-delay and-gate-delay or-gate-delay
         make-wire)

(defn logical-not [s]
  (case s
    0 1
    1 0
    (throw (IllegalArgumentException. (str "Invalid signal " s)))))

(defn inverter [input output]
  (letfn [(invert-input []
            (let [new-value (logical-not (get-signal input))]
              (after-delay inverter-delay
                           #(set-signal! output new-value))))]
    (add-action! input invert-input)
    :ok))

(defn logical-and [s1 s2]
  (case [s1 s2]
    [0 0] 0
    [0 1] 0
    [1 0] 0
    [1 1] 1
    (throw (IllegalArgumentException. (str "Invalid signal "
                                           {:s1 s1 :s2 s2})))))

(defn and-gate [a1 a2 output]
  (letfn [(and-action-procedure []
            (let [new-value (logical-and (get-signal a1)
                                         (get-signal a2))]
              (after-delay and-gate-delay
                           #(set-signal! output new-value))))]
    (add-action! a1 and-action-procedure)
    (add-action! a2 and-action-procedure)
    :ok))

;; Exercise 3.28
(defn logical-or [s1 s2]
  (case [s1 s2]
    [0 0] 0
    [0 1] 1
    [1 0] 1
    [1 1] 1
    (throw (IllegalArgumentException. (str "Invalid signal "
                                           {:s1 s1 :s2 s2})))))

(defn or-gate [a1 a2 output]
  (letfn [(or-action-procedure []
            (let [new-value (logical-or (get-signal a1)
                                        (get-signal a2))]
              (after-delay or-gate-delay
                           #(set-signal! output new-value))))]
    (add-action! a1 or-action-procedure)
    (add-action! a2 or-action-procedure)
    :ok))

;; Exercise 3.29
(defn or-gate' [a1 a2 output]
  (let [b1 (make-wire)
        b2 (make-wire)
        c (make-wire)]
    (inverter a1 b1)
    (inverter a2 b2)
    (and-gate b1 b2 c)
    (inverter c output)
    :ok))

;; the delay time of or-gate' = 2 * inverter-delay + and-gate-delay

;;; 3.3.5  Propagation of Constraints
