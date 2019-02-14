(ns sicp.chapter3.2-2
  (:require [dorothy.core :as d]
            [sicp.common.graph :refer [create-node]]
            [sicp.common.string :refer [strip-margin]]))

;;;; 3.2  The Environment Model of Evaluation

;;; 3.2.2  Applying Simple Procedures

;; Exercise 3.9
;; recursive version
;; cf. resources/public/img/chapter3_2/exercise3-9_recursive.png
(def factorial-recursive
  (-> [(d/node-attrs {:shape :record})
       [:g (create-node "global env"
                        "factorial")]
       [:e1 (create-node "E1"
                         "n: 6")]
       [:e2 (create-node "E2"
                         "n: 5")]
       [:e3 (create-node "E3"
                         "n: 4")]
       [:e4 (create-node "E4"
                         "n: 3")]
       [:e5 (create-node "E5"
                         "n: 2")]
       [:e6 (create-node "E6"
                         "n: 1")]

       (d/node-attrs {:shape :note})
       (d/subgraph
        [[:node (create-node
                 (strip-margin "(if (= n 1)
                               |    1
                               |    (* n (factorial (- n 1))))"))]
         :c1 :c2 :c3 :c4 :c5 :c6])

       [:e1 :g]
       [:e2 :g]
       [:e3 :g]
       [:e4 :g]
       [:e5 :g]
       [:e6 :g]

       (d/edge-attrs {:dir :none})
       [:c1 :e1]
       [:c2 :e2]
       [:c3 :e3]
       [:c4 :e4]
       [:c5 :e5]
       [:c6 :e6]]
      d/digraph
      d/dot))

;; iterative version
;; cf. resources/public/img/chapter3_2/exercise3-9_iterative.png
(def factorial-iterative
  (-> [(d/node-attrs {:shape :record})
       [:g (create-node "global env"
                        (strip-margin "factorial
                                      |fact-iter"))]
       [:e1 (create-node "E1"
                         "n: 6")]
       [:e2 (create-node "E2"
                         (strip-margin "product: 1
                                       |counter: 1
                                       |max-count: 6"))]

       [:e3 (create-node "E3"
                         (strip-margin "product: 1
                                       |counter: 2
                                       |max-count: 6"))]
       [:e4 (create-node "E4"
                         (strip-margin "product: 2
                                       |counter: 3
                                       |max-count: 6"))]
       [:e5 (create-node "E5"
                         (strip-margin "product: 6
                                       |counter: 4
                                       |max-count: 6"))]
       [:e6 (create-node "E6"
                         (strip-margin "product: 24
                                       |counter: 5
                                       |max-count: 6"))]
       [:e7 (create-node "E7"
                         (strip-margin "product: 120
                                       |counter: 6
                                       |max-count: 6"))]
       [:e8 (create-node "E8"
                         (strip-margin "product: 720
                                       |counter: 7
                                       |max-count: 6"))]
       (d/node-attrs {:shape :note})
       (d/subgraph
        [[:node (create-node
                 "(fact-iter 1 1 n)")]
         :c1])
       (d/subgraph
        [[:node (create-node
                 (strip-margin "(if (> counter max-count)
                               |      product
                               |      (fact-iter (* counter product)
                               |                 (+ counter 1)
                               |                 max-count))"))]
         :c2 :c3 :c4 :c5 :c6 :c7 :c8])

       [:e1 :g]
       [:e2 :g]
       [:e3 :g]
       [:e4 :g]
       [:e5 :g]
       [:e6 :g]
       [:e7 :g]
       [:e8 :g]

       (d/edge-attrs {:dir :none})
       [:c1 :e1]
       [:c2 :e2]
       [:c3 :e3]
       [:c4 :e4]
       [:c5 :e5]
       [:c6 :e6]
       [:c7 :e7]
       [:c8 :e8]]
      d/digraph
      d/dot))
