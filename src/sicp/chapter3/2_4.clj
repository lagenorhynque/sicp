(ns sicp.chapter3.2-4
  (:require [dorothy.core :as d]
            [sicp.common.graph :refer [create-node]]
            [sicp.common.string :refer [strip-margin]]))

;;;; 3.2  The Environment Model of Evaluation

;;; 3.2.4  Internal Definitions

;; Exercise 3.11
;; cf. resources/public/img/chapter3_2/exercise3-11_1_define-acc.png
(def make-account-define-acc
  (-> [(d/node-attrs {:shape :record})
       [:g (create-node "global env"
                        (strip-margin "make-account
                                      |acc"))]
       [:e1 (create-node "E1"
                         (strip-margin "balance: 50
                                       |withdraw
                                       |deposit
                                       |dispatch"))]

       [:e1 :g]]
      d/digraph
      d/dot))

;; cf. resources/public/img/chapter3_2/exercise3-11_2_acc-deposit.png
(def make-account-acc-deposit
  (-> [(d/node-attrs {:shape :record})
       [:g (create-node "global env"
                        (strip-margin "make-account
                                      |acc"))]
       [:e1 (create-node "E1"
                         (strip-margin "balance: 50
                                       |withdraw
                                       |deposit
                                       |dispatch"))]
       [:e2 (create-node "E2"
                         "m: 'deposit")]

       [:e3 (create-node "E3"
                         "amount: 40")]

       (d/node-attrs {:shape :note})
       (d/subgraph
        [[:node (create-node
                 (strip-margin "(cond ((eq? m 'withdraw) withdraw)
                               |      ((eq? m 'deposit) deposit)
                               |      (else (error \"Unknown request -- MAKE-ACCOUNT\"
                               |                   m)))"))]
         :c2])
       (d/subgraph
        [[:node (create-node
                 (strip-margin "(set! balance (+ balance amount))
                               |balance"))]
         :c3])

       [:e1 :g]
       [:e2 :e1]
       [:e3 :e1]

       (d/edge-attrs {:dir :none})
       [:c2 :e2]
       [:c3 :e3]]
      d/digraph
      d/dot))

;; cf. resources/public/img/chapter3_2/exercise3-11_3_acc-withdraw.png
(def make-account-acc-withdraw
  (-> [(d/node-attrs {:shape :record})
       [:g (create-node "global env"
                        (strip-margin "make-account
                                      |acc"))]
       [:e1 (create-node "E1"
                         (strip-margin "balance: 90
                                       |withdraw
                                       |deposit
                                       |dispatch"))]
       [:e4 (create-node "E4"
                         "m: 'withdraw")]
       [:e5 (create-node "E5"
                         "amount: 60")]

       (d/node-attrs {:shape :note})
       (d/subgraph
        [[:node (create-node
                 (strip-margin "(cond ((eq? m 'withdraw) withdraw)
                               |      ((eq? m 'deposit) deposit)
                               |      (else (error \"Unknown request -- MAKE-ACCOUNT\"
                               |                   m)))"))]
         :c4])
       (d/subgraph
        [[:node (create-node
                 (strip-margin "(if (>= balance amount)
                               |    (begin (set! balance (- balance amount))
                               |           balance)
                               |    \"Insufficient funds\")"))]
         :c5])

       [:e1 :g]
       [:e4 :e1]
       [:e5 :e1]

       (d/edge-attrs {:dir :none})
       [:c4 :e4]
       [:c5 :e5]]
      d/digraph
      d/dot))

;; cf. resources/public/img/chapter3_2/exercise3-11_4_define-acc2.png
(def make-account-define-acc2
  (-> [(d/node-attrs {:shape :record})
       [:g (create-node "global env"
                        (strip-margin "make-account
                                      |acc
                                      |acc2"))]
       [:e1 (create-node "E1"
                         (strip-margin "balance: 30
                                       |withdraw
                                       |deposit
                                       |dispatch"))]
       [:e6 (create-node "E6"
                         (strip-margin "balance: 100
                                       |withdraw
                                       |deposit
                                       |dispatch"))]

       [:e1 :g]
       [:e6 :g]]
      d/digraph
      d/dot))
