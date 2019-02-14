(ns sicp.chapter3.2-3
  (:require [dorothy.core :as d]
            [sicp.common.graph :refer [create-node]]
            [sicp.common.string :refer [strip-margin]]))

;;;; 3.2  The Environment Model of Evaluation

;;; 3.2.3  Frames as the Repository of Local State

;; Exercise 3.10
;; cf. resources/public/img/chapter3_2/exercise3-10_1_define-w1.png
(def make-withdraw-define-w1
  (-> [(d/node-attrs {:shape :record})
       [:g (create-node "global env"
                        (strip-margin "make-withdraw
                                      |W1"))]
       [:e1 (create-node "E1"
                         "initial-amount: 100")]
       [:e2 (create-node "E2"
                         "balance: 100")]

       [:e1 :g]
       [:e2 :e1]]
      d/digraph
      d/dot))

;; cf. resources/public/img/chapter3_2/exercise3-10_2_w1.png
(def make-withdraw-w1
  (-> [(d/node-attrs {:shape :record})
       [:g (create-node "global env"
                        (strip-margin "make-withdraw
                                      |acc"))]
       [:e1 (create-node "E1"
                         "initial-amount: 100")]
       [:e2 (create-node "E2"
                         "balance: 100")]
       [:e3 (create-node "E3"
                         "amount: 50")]

       (d/node-attrs {:shape :note})
       (d/subgraph
        [[:node (create-node
                 (strip-margin "(if (>= balance amount)
                               |    (begin (set! balance (- balance amount))
                               |           balance)
                               |    \"Insufficient funds\")"))]
         :c3])

       [:e1 :g]
       [:e2 :e1]
       [:e3 :e2]

       (d/edge-attrs {:dir :none})
       [:c3 :e3]]
      d/digraph
      d/dot))

;; cf. resources/public/img/chapter3_2/exercise3-10_3_define-w2.png
(def make-withdraw-define-w2
  (-> [(d/node-attrs {:shape :record})
       [:g (create-node "global env"
                        (strip-margin "make-withdraw
                                      |W1"))]
       [:e1 (create-node "E1"
                         "initial-amount: 100")]
       [:e2 (create-node "E2"
                         "balance: 50")]
       [:e4 (create-node "E4"
                         "initial-amount: 100")]
       [:e5 (create-node "E5"
                         "balance: 100")]

       [:e1 :g]
       [:e2 :e1]
       [:e4 :g]
       [:e5 :e4]]
      d/digraph
      d/dot))
