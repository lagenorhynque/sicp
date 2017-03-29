(ns sicp.chapter3-2
  (:require [clojure.string :as str]
            [dorothy.core :as d]))

;;;; 3.2  The Environment Model of Evaluation

;;; 3.2.1  The Rules for Evaluation

;;; 3.2.2  Applying Simple Procedures

;; Exercise 3.9
(def ^:dynamic *newline-char*
  "&#92;n")

(defn strip-margin
  ([s]
   (strip-margin s "\\|"))
  ([s delimiter]
   (let [p (re-pattern (str "^\\s*" delimiter))]
     (->> s
          (str/split-lines)
          (map #(str/replace-first % p ""))
          (str/join *newline-char*)))))

(defn create-node [& xs]
  {:label (str/join (str *newline-char* *newline-char*) xs)})

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
                 #sicp/s "(if (= n 1)
                         |    1
                         |    (* n (factorial (- n 1))))")]
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
                        #sicp/s "factorial
                                |fact-iter")]
       [:e1 (create-node "E1"
                         "n: 6")]
       [:e2 (create-node "E2"
                         #sicp/s "product: 1
                                 |counter: 1
                                 |max-count: 6")]

       [:e3 (create-node "E3"
                         #sicp/s "product: 1
                                 |counter: 2
                                 |max-count: 6")]
       [:e4 (create-node "E4"
                         #sicp/s "product: 2
                                 |counter: 3
                                 |max-count: 6")]
       [:e5 (create-node "E5"
                         #sicp/s "product: 6
                                 |counter: 4
                                 |max-count: 6")]
       [:e6 (create-node "E6"
                         #sicp/s "product: 24
                                 |counter: 5
                                 |max-count: 6")]
       [:e7 (create-node "E7"
                         #sicp/s "product: 120
                                 |counter: 6
                                 |max-count: 6")]
       [:e8 (create-node "E8"
                         #sicp/s "product: 720
                                 |counter: 7
                                 |max-count: 6")]
       (d/node-attrs {:shape :note})
       (d/subgraph
        [[:node (create-node
                 "(fact-iter 1 1 n)")]
         :c1])
       (d/subgraph
        [[:node (create-node
                 #sicp/s "(if (> counter max-count)
                         |      product
                         |      (fact-iter (* counter product)
                         |                 (+ counter 1)
                         |                 max-count))")]
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

;;; 3.2.3  Frames as the Repository of Local State

;; Exercise 3.10
(def make-withdraw-define-w1
  (-> [(d/node-attrs {:shape :record})
       [:g (create-node "global env"
                        #sicp/s "make-withdraw
                                |W1")]
       [:e1 (create-node "E1"
                         "initial-amount: 100")]
       [:e2 (create-node "E2"
                         "balance: 100")]

       [:e1 :g]
       [:e2 :e1]]
      d/digraph
      d/dot))

(def make-withdraw-w1
  (-> [(d/node-attrs {:shape :record})
       [:g (create-node "global env"
                        #sicp/s "make-withdraw
                                |acc")]
       [:e1 (create-node "E1"
                         "initial-amount: 100")]
       [:e2 (create-node "E2"
                         "balance: 100")]
       [:e3 (create-node "E3"
                         "amount: 50")]

       (d/node-attrs {:shape :note})
       (d/subgraph
        [[:node (create-node
                 #sicp/s "(if (>= balance amount)
                         |    (begin (set! balance (- balance amount))
                         |           balance)
                         |    \"Insufficient funds\")")]
         :c3])

       [:e1 :g]
       [:e2 :e1]
       [:e3 :e2]

       (d/edge-attrs {:dir :none})
       [:c3 :e3]]
      d/digraph
      d/dot))

(def make-withdraw-define-w2
  (-> [(d/node-attrs {:shape :record})
       [:g (create-node "global env"
                        #sicp/s "make-withdraw
                                |W1")]
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

;;; 3.2.4  Internal Definitions

;; Exercise 3.11
(def make-account-define-acc
  (-> [(d/node-attrs {:shape :record})
       [:g (create-node "global env"
                        #sicp/s "make-account
                                |acc")]
       [:e1 (create-node "E1"
                         #sicp/s "balance: 50
                                 |withdraw
                                 |deposit
                                 |dispatch")]

       [:e1 :g]]
      d/digraph
      d/dot))

(def make-account-acc-deposit
  (-> [(d/node-attrs {:shape :record})
       [:g (create-node "global env"
                        #sicp/s "make-account
                                |acc")]
       [:e1 (create-node "E1"
                         #sicp/s "balance: 50
                                 |withdraw
                                 |deposit
                                 |dispatch")]
       [:e2 (create-node "E2"
                         "m: 'deposit")]

       [:e3 (create-node "E3"
                         "amount: 40")]

       (d/node-attrs {:shape :note})
       (d/subgraph
        [[:node (create-node
                 #sicp/s "(cond ((eq? m 'withdraw) withdraw)
                         |      ((eq? m 'deposit) deposit)
                         |      (else (error \"Unknown request -- MAKE-ACCOUNT\"
                         |                   m)))")]
         :c2])
       (d/subgraph
        [[:node (create-node
                 #sicp/s "(set! balance (+ balance amount))
                         |balance")]
         :c3])

       [:e1 :g]
       [:e2 :e1]
       [:e3 :e1]

       (d/edge-attrs {:dir :none})
       [:c2 :e2]
       [:c3 :e3]]
      d/digraph
      d/dot))

(def make-account-acc-withdraw
  (-> [(d/node-attrs {:shape :record})
       [:g (create-node "global env"
                        #sicp/s "make-account
                                |acc")]
       [:e1 (create-node "E1"
                         #sicp/s "balance: 90
                                 |withdraw
                                 |deposit
                                 |dispatch")]
       [:e4 (create-node "E4"
                         "m: 'withdraw")]
       [:e5 (create-node "E5"
                         "amount: 60")]

       (d/node-attrs {:shape :note})
       (d/subgraph
        [[:node (create-node
                 #sicp/s "(cond ((eq? m 'withdraw) withdraw)
                         |      ((eq? m 'deposit) deposit)
                         |      (else (error \"Unknown request -- MAKE-ACCOUNT\"
                         |                   m)))")]
         :c4])
       (d/subgraph
        [[:node (create-node
                 #sicp/s "(if (>= balance amount)
                         |    (begin (set! balance (- balance amount))
                         |           balance)
                         |    \"Insufficient funds\")")]
         :c5])

       [:e1 :g]
       [:e4 :e1]
       [:e5 :e1]

       (d/edge-attrs {:dir :none})
       [:c4 :e4]
       [:c5 :e5]]
      d/digraph
      d/dot))

(def make-account-define-acc2
  (-> [(d/node-attrs {:shape :record})
       [:g (create-node "global env"
                        #sicp/s "make-account
                                |acc
                                |acc2")]
       [:e1 (create-node "E1"
                         #sicp/s "balance: 30
                                 |withdraw
                                 |deposit
                                 |dispatch")]
       [:e6 (create-node "E6"
                         #sicp/s "balance: 100
                                 |withdraw
                                 |deposit
                                 |dispatch")]

       [:e1 :g]
       [:e6 :g]]
      d/digraph
      d/dot))
