(ns sicp.chapter3.3-1
  (:require [dorothy.core :as d]
            [sicp.common.graph :refer [create-node]]
            [sicp.common.list :refer :all]
            [sicp.common.string :refer [strip-margin]]))

;;;; 3.3  Modeling with Mutable Data

;;; 3.3.1  Mutable List Structure

;; Exercise 3.12
;; cf. resources/public/img/chapter3_3/exercise3-12_1_define-x.png
(def append-define-x
  (-> [(d/node-attrs {:shape :record})
       [:c1 (create-node "<car>|<cdr>")]
       [:c2 (create-node "<car>|<cdr>")]

       (d/node-attrs {:shape :square})
       [:v1 (create-node "a")]
       [:v2 (create-node "b")]

       (d/node-attrs {:shape :none})
       [:s1 (create-node "x")]

       [:s1 :c1]
       [:c1:car :v1]
       [:c1:cdr :c2]
       [:c2:car :v2]]
      d/digraph
      d/dot))

;; cf. resources/public/img/chapter3_3/exercise3-12_2_define-y.png
(def append-define-y
  (-> [(d/node-attrs {:shape :record})
       [:c1 (create-node "<car>|<cdr>")]
       [:c2 (create-node "<car>|<cdr>")]
       [:c3 (create-node "<car>|<cdr>")]
       [:c4 (create-node "<car>|<cdr>")]

       (d/node-attrs {:shape :square})
       [:v1 (create-node "a")]
       [:v2 (create-node "b")]
       [:v3 (create-node "c")]
       [:v4 (create-node "d")]

       (d/node-attrs {:shape :none})
       [:s1 (create-node "x")]
       [:s2 (create-node "y")]

       [:s1 :c1]
       [:c1:car :v1]
       [:c1:cdr :c2]
       [:c2:car :v2]

       [:s2 :c3]
       [:c3:car :v3]
       [:c3:cdr :c4]
       [:c4:car :v4]]
      d/digraph
      d/dot))

;; cf. resources/public/img/chapter3_3/exercise3-12_3_define-z.png
(def append-define-z
  (-> [(d/node-attrs {:shape :record})
       [:c1 (create-node "<car>|<cdr>")]
       [:c2 (create-node "<car>|<cdr>")]
       [:c3 (create-node "<car>|<cdr>")]
       [:c4 (create-node "<car>|<cdr>")]
       [:c5 (create-node "<car>|<cdr>")]
       [:c6 (create-node "<car>|<cdr>")]

       (d/node-attrs {:shape :square})
       [:v1 (create-node "a")]
       [:v2 (create-node "b")]
       [:v3 (create-node "c")]
       [:v4 (create-node "d")]

       (d/node-attrs {:shape :none})
       [:s1 (create-node "x")]
       [:s2 (create-node "y")]
       [:s3 (create-node "z")]

       [:s1 :c1]
       [:c1:car :v1]
       [:c1:cdr :c2]
       [:c2:car :v2]

       [:s2 :c3]
       [:c3:car :v3]
       [:c3:cdr :c4]
       [:c4:car :v4]

       [:s3 :c5]
       [:c5:car :v1]
       [:c5:cdr :c6]
       [:c6:car :v2]
       [:c6:cdr :c3]]
      d/digraph
      d/dot))

;; cf. resources/public/img/chapter3_3/exercise3-12_4_define-w.png
(def append-define-w
  (-> [(d/node-attrs {:shape :record})
       [:c1 (create-node "<car>|<cdr>")]
       [:c2 (create-node "<car>|<cdr>")]
       [:c3 (create-node "<car>|<cdr>")]
       [:c4 (create-node "<car>|<cdr>")]
       [:c5 (create-node "<car>|<cdr>")]
       [:c6 (create-node "<car>|<cdr>")]

       (d/node-attrs {:shape :square})
       [:v1 (create-node "a")]
       [:v2 (create-node "b")]
       [:v3 (create-node "c")]
       [:v4 (create-node "d")]

       (d/node-attrs {:shape :none})
       [:s1 (create-node "x")]
       [:s2 (create-node "y")]
       [:s3 (create-node "z")]
       [:s4 (create-node "w")]

       [:s1 :c1]
       [:c1:car :v1]
       [:c1:cdr :c2]
       [:c2:car :v2]
       [:c2:cdr :c3]

       [:s2 :c3]
       [:c3:car :v3]
       [:c3:cdr :c4]
       [:c4:car :v4]

       [:s3 :c5]
       [:c5:car :v1]
       [:c5:cdr :c6]
       [:c6:car :v2]
       [:c6:cdr :c3]

       [:s4 :c1]]
      d/digraph
      d/dot))

;; Exercise 3.13
;; cf. resources/public/img/chapter3_3/exercise3-13_1_list.png
(def make-cycle-list
  (-> [(d/node-attrs {:shape :record})
       [:c1 (create-node "<car>|<cdr>")]
       [:c2 (create-node "<car>|<cdr>")]
       [:c3 (create-node "<car>|<cdr>")]

       (d/node-attrs {:shape :square})
       [:v1 (create-node "a")]
       [:v2 (create-node "b")]
       [:v3 (create-node "c")]

       [:c1:car :v1]
       [:c1:cdr :c2]
       [:c2:car :v2]
       [:c2:cdr :c3]
       [:c3:car :v3]]
      d/digraph
      d/dot))

;; cf. resources/public/img/chapter3_3/exercise3-13_2_define-z.png
(def make-cycle-define-z
  (-> [(d/node-attrs {:shape :record})
       [:c1 (create-node "<car>|<cdr>")]
       [:c2 (create-node "<car>|<cdr>")]
       [:c3 (create-node "<car>|<cdr>")]

       (d/node-attrs {:shape :square})
       [:v1 (create-node "a")]
       [:v2 (create-node "b")]
       [:v3 (create-node "c")]

       (d/node-attrs {:shape :none})
       [:s1 (create-node "z")]

       [:s1 :c1]
       [:c1:car :v1]
       [:c1:cdr :c2]
       [:c2:car :v2]
       [:c2:cdr :c3]
       [:c3:car :v3]
       [:c3:cdr :c1]]
      d/digraph
      d/dot))

;; Exercise 3.14
;; cf. resources/public/img/chapter3_3/exercise3-14_1_define-v.png
(def mystery-define-v
  (-> [(d/node-attrs {:shape :record})
       [:c1 (create-node "<car>|<cdr>")]
       [:c2 (create-node "<car>|<cdr>")]
       [:c3 (create-node "<car>|<cdr>")]
       [:c4 (create-node "<car>|<cdr>")]

       (d/node-attrs {:shape :square})
       [:v1 (create-node "a")]
       [:v2 (create-node "b")]
       [:v3 (create-node "c")]
       [:v4 (create-node "d")]

       (d/node-attrs {:shape :none})
       [:s1 (create-node "v")]

       [:s1 :c1]
       [:c1:car :v1]
       [:c1:cdr :c2]
       [:c2:car :v2]
       [:c2:cdr :c3]
       [:c3:car :v3]
       [:c3:cdr :c4]
       [:c4:car :v4]]
      d/digraph
      d/dot))

;; cf. resources/public/img/chapter3_3/exercise3-14_2_define-w.png
(def mystery-define-w
  (-> [(d/node-attrs {:shape :record})
       [:c1 (create-node "<car>|<cdr>")]
       [:c2 (create-node "<car>|<cdr>")]
       [:c3 (create-node "<car>|<cdr>")]
       [:c4 (create-node "<car>|<cdr>")]

       (d/node-attrs {:shape :square})
       [:v1 (create-node "a")]
       [:v2 (create-node "b")]
       [:v3 (create-node "c")]
       [:v4 (create-node "d")]

       (d/node-attrs {:shape :none})
       [:s1 (create-node "v")]
       [:s2 (create-node "w")]

       [:s1 :c1]
       [:s2 :c4]
       [:c1:car :v1]
       [:c2:car :v2]
       [:c2:cdr :c1]
       [:c3:car :v3]
       [:c3:cdr :c2]
       [:c4:car :v4]
       [:c4:cdr :c3]]
      d/digraph
      d/dot))

;; Exercise 3.15
;; z1
;; cf. resources/public/img/chapter3_3/exercise3-15_z1_1_define.png
(def z1-define
  (-> [(d/node-attrs {:shape :record})
       [:c1 (create-node "<car>|<cdr>")]
       [:c2 (create-node "<car>|<cdr>")]
       [:c3 (create-node "<car>|<cdr>")]

       (d/node-attrs {:shape :square})
       [:v1 (create-node "a")]
       [:v2 (create-node "b")]

       (d/node-attrs {:shape :none})
       [:s1 (create-node "x")]
       [:s2 (create-node "z1")]

       [:s1 :c1]
       [:c1:car :v1]
       [:c1:cdr :c2]
       [:c2:car :v2]
       [:s2 :c3]
       [:c3:car :c1]
       [:c3:cdr :c1]]
      d/digraph
      d/dot))

;; cf. resources/public/img/chapter3_3/exercise3-15_z1_2_set-to-wow.png
(def z1-set-to-wow
  (-> [(d/node-attrs {:shape :record})
       [:c1 (create-node "<car>|<cdr>")]
       [:c2 (create-node "<car>|<cdr>")]
       [:c3 (create-node "<car>|<cdr>")]

       (d/node-attrs {:shape :square})
       [:v1 (create-node "a")]
       [:v2 (create-node "b")]
       [:v3 (create-node "wow")]

       (d/node-attrs {:shape :none})
       [:s1 (create-node "x")]
       [:s2 (create-node "z1")]

       [:s1 :c1]
       [:c1:car :v3]
       [:c1:cdr :c2]
       [:c2:car :v2]
       [:s2 :c3]
       [:c3:car :c1]
       [:c3:cdr :c1]]
      d/digraph
      d/dot))

;; z2
;; cf. resources/public/img/chapter3_3/exercise3-15_z2_1_define.png
(def z2-define
  (-> [(d/node-attrs {:shape :record})
       [:c1 (create-node "<car>|<cdr>")]
       [:c2 (create-node "<car>|<cdr>")]
       [:c3 (create-node "<car>|<cdr>")]
       [:c4 (create-node "<car>|<cdr>")]
       [:c5 (create-node "<car>|<cdr>")]

       (d/node-attrs {:shape :square})
       [:v1 (create-node "a")]
       [:v2 (create-node "b")]

       (d/node-attrs {:shape :none})
       [:s1 (create-node "z2")]

       [:c1:car :v1]
       [:c1:cdr :c2]
       [:c2:car :v2]
       [:c3:car :v1]
       [:c3:cdr :c4]
       [:c4:car :v2]
       [:s1 :c5]
       [:c5:car :c1]
       [:c5:cdr :c3]]
      d/digraph
      d/dot))

;; cf. resources/public/img/chapter3_3/exercise3-15_z2_2_set-to-wow.png
(def z2-set-to-wow
  (-> [(d/node-attrs {:shape :record})
       [:c1 (create-node "<car>|<cdr>")]
       [:c2 (create-node "<car>|<cdr>")]
       [:c3 (create-node "<car>|<cdr>")]
       [:c4 (create-node "<car>|<cdr>")]
       [:c5 (create-node "<car>|<cdr>")]

       (d/node-attrs {:shape :square})
       [:v1 (create-node "a")]
       [:v2 (create-node "b")]
       [:v3 (create-node "wow")]

       (d/node-attrs {:shape :none})
       [:s1 (create-node "z2")]

       [:c1:car :v3]
       [:c1:cdr :c2]
       [:c2:car :v2]
       [:c3:car :v1]
       [:c3:cdr :c4]
       [:c4:car :v2]
       [:s1 :c5]
       [:c5:car :c1]
       [:c5:cdr :c3]]
      d/digraph
      d/dot))

;; Exercise 3.16
(defn count-pairs [x]
  (if-not (pair? x)
    0
    (+ (count-pairs (car x))
       (count-pairs (cdr x))
       1)))

;; cf. resources/public/img/chapter3_3/exercise3-16_1_return-3.png
(def count-pairs-return-3
  (-> [(d/node-attrs {:shape :record})
       [:c1 (create-node "<car>|<cdr>")]
       [:c2 (create-node "<car>|<cdr>")]
       [:c3 (create-node "<car>|<cdr>")]

       (d/node-attrs {:shape :square})
       [:v1 (create-node "a")]
       [:v2 (create-node "b")]
       [:v3 (create-node "c")]

       (d/node-attrs {:shape :none})
       [:s1 (create-node "x")]
       [:s2 (create-node "y")]
       [:s3 (create-node "z")]

       [:s1 :c1]
       [:s2 :c2]
       [:s3 :c3]
       [:c1:car :v1]
       [:c1:cdr :c2]
       [:c2:car :v2]
       [:c2:cdr :c3]
       [:c3:car :v3]]
      d/digraph
      d/dot))

;; cf. resources/public/img/chapter3_3/exercise3-16_2_return-4.png
(def count-pairs-return-4
  (-> [(d/node-attrs {:shape :record})
       [:c1 (create-node "<car>|<cdr>")]
       [:c2 (create-node "<car>|<cdr>")]
       [:c3 (create-node "<car>|<cdr>")]

       (d/node-attrs {:shape :square})
       [:v1 (create-node "a")]
       [:v2 (create-node "b")]
       [:v3 (create-node "c")]

       (d/node-attrs {:shape :none})
       [:s1 (create-node "x")]
       [:s2 (create-node "y")]
       [:s3 (create-node "z")]

       [:s1 :c1]
       [:s2 :c2]
       [:s3 :c3]
       [:c1:car :v1]
       [:c1:cdr :c2]
       [:c2:car :c3]
       [:c2:cdr :c3]
       [:c3:car :v3]]
      d/digraph
      d/dot))

;; cf. resources/public/img/chapter3_3/exercise3-16_3_return-7.png
(def count-pairs-return-7
  (-> [(d/node-attrs {:shape :record})
       [:c1 (create-node "<car>|<cdr>")]
       [:c2 (create-node "<car>|<cdr>")]
       [:c3 (create-node "<car>|<cdr>")]

       (d/node-attrs {:shape :square})
       [:v1 (create-node "a")]
       [:v2 (create-node "b")]
       [:v3 (create-node "c")]

       (d/node-attrs {:shape :none})
       [:s1 (create-node "x")]
       [:s2 (create-node "y")]
       [:s3 (create-node "z")]

       [:s1 :c1]
       [:s2 :c2]
       [:s3 :c3]
       [:c1:car :c2]
       [:c1:cdr :c2]
       [:c2:car :c3]
       [:c2:cdr :c3]
       [:c3:car :v3]]
      d/digraph
      d/dot))

;; cf. resources/public/img/chapter3_3/exercise3-16_4_never-return.png
(def count-pairs-never-return
  (-> [(d/node-attrs {:shape :record})
       [:c1 (create-node "<car>|<cdr>")]
       [:c2 (create-node "<car>|<cdr>")]
       [:c3 (create-node "<car>|<cdr>")]

       (d/node-attrs {:shape :square})
       [:v1 (create-node "a")]
       [:v2 (create-node "b")]
       [:v3 (create-node "c")]

       (d/node-attrs {:shape :none})
       [:s1 (create-node "x")]
       [:s2 (create-node "y")]
       [:s3 (create-node "z")]

       [:s1 :c1]
       [:s2 :c2]
       [:s3 :c3]
       [:c1:car :v1]
       [:c1:cdr :c2]
       [:c2:car :v2]
       [:c2:cdr :c3]
       [:c3:car :v3]
       [:c3:cdr :c1]]
      d/digraph
      d/dot))

;; Exercise 3.17
(defn count-pairs' [x]
  (let [memo (atom [])
        f (fn f [x]
            (if (or (not (pair? x))
                    (some #(identical? x %) @memo))
              0
              (do (swap! memo conj x)
                  (+ (f (car x))
                     (f (cdr x))
                     1))))]
    (f x)))

;; Exercise 3.18
(defn contains-cycle? [x]
  (let [memo (atom [])
        f (fn f [x]
            (cond
              (not (pair? (cdr x))) false
              (some #(identical? x %) @memo) true
              :else (do (swap! memo conj x)
                        (recur (cdr x)))))]
    (f x)))

;; Exercise 3.19
(defn contains-cycle?' [x]
  (letfn [(f [a b]
            (cond
              (identical? a b) true
              (or (nil? (cdr a))
                  (nil? (cddr b))) false
              :else (recur (cdr a)
                           (cddr b))))]
    (if (and (pair? x)
             (pair? (cdr x)))
      (f x (cdr x))
      false)))

;; Exercise 3.20
;; cf. resources/public/img/chapter3_3/exercise3-20_1_define-x.png
(def cons-define-x
  (-> [(d/node-attrs {:shape :record})
       [:g (create-node "global env"
                        (strip-margin "cons
                                      |car
                                      |cdr
                                      |set-car!
                                      |set-cdr!
                                      |x"))]
       [:e1 (create-node "E1"
                         (strip-margin "x: 1
                                       |y: 2
                                       |set-x!
                                       |set-y!
                                       |dispatch"))]

       [:e1 :g]]
      d/digraph
      d/dot))

;; cf. resources/public/img/chapter3_3/exercise3-20_2_define-z.png
(def cons-define-z
  (-> [(d/node-attrs {:shape :record})
       [:g (create-node "global env"
                        (strip-margin "cons
                                      |car
                                      |cdr
                                      |set-car!
                                      |set-cdr!
                                      |x
                                      |z"))]
       [:e1 (create-node "E1"
                         (strip-margin "x: 1
                                       |y: 2
                                       |set-x!
                                       |set-y!
                                       |dispatch"))]
       [:e2 (create-node "E2"
                         (strip-margin "x: E1
                                       |y: E1
                                       |set-x!
                                       |set-y!
                                       |dispatch"))]
       [:e1 :g]
       [:e2 :g]]
      d/digraph
      d/dot))

;; cf. resources/public/img/chapter3_3/exercise3-20_3_cdr-z.png
(def cons-cdr-z
  (-> [(d/node-attrs {:shape :record})
       [:g (create-node "global env"
                        (strip-margin "cons
                                      |car
                                      |cdr
                                      |set-car!
                                      |set-cdr!
                                      |x
                                      |z"))]
       [:e1 (create-node "E1"
                         (strip-margin "x: 1
                                       |y: 2
                                       |set-x!
                                       |set-y!
                                       |dispatch"))]
       [:e2 (create-node "E2"
                         (strip-margin "x: E1
                                       |y: E1
                                       |set-x!
                                       |set-y!
                                       |dispatch"))]
       [:e3 (create-node "E3"
                         (strip-margin "z: E2"))]

       (d/node-attrs {:shape :note})
       (d/subgraph
        [[:node (create-node
                 (strip-margin "(z 'cdr)"))]
         :c3])

       [:e1 :g]
       [:e2 :g]
       [:e3 :g]

       (d/edge-attrs {:dir :none})
       [:c3 :e3]]
      d/digraph
      d/dot))

;; cf. resources/public/img/chapter3_3/exercise3-20_4_set-car!.png
(def cons-set-car!
  (-> [(d/node-attrs {:shape :record})
       [:g (create-node "global env"
                        (strip-margin "cons
                                      |car
                                      |cdr
                                      |set-car!
                                      |set-cdr!
                                      |x
                                      |z"))]
       [:e1 (create-node "E1"
                         (strip-margin "x: 1
                                       |y: 2
                                       |set-x!
                                       |set-y!
                                       |dispatch"))]
       [:e2 (create-node "E2"
                         (strip-margin "x: E1
                                       |y: E1
                                       |set-x!
                                       |set-y!
                                       |dispatch"))]
       [:e3 (create-node "E3"
                         (strip-margin "z: E2"))]
       [:e4 (create-node "E4"
                         (strip-margin "z: E1
                                       |new-value: 17"))]

       (d/node-attrs {:shape :note})
       (d/subgraph
        [[:node (create-node
                 (strip-margin "(z 'cdr)"))]
         :c3])
       (d/subgraph
        [[:node (create-node
                 (strip-margin "((z 'set-car!) new-value)
                               |z"))]
         :c4])

       [:e1 :g]
       [:e2 :g]
       [:e3 :g]
       [:e4 :g]

       (d/edge-attrs {:dir :none})
       [:c3 :e3]
       [:c4 :e4]]
      d/digraph
      d/dot))

;; cf. resources/public/img/chapter3_3/exercise3-20_5_car.png
(def cons-car
  (-> [(d/node-attrs {:shape :record})
       [:g (create-node "global env"
                        (strip-margin "cons
                                      |car
                                      |cdr
                                      |set-car!
                                      |set-cdr!
                                      |x
                                      |z"))]
       [:e1 (create-node "E1"
                         (strip-margin "x: 17
                                       |y: 2
                                       |set-x!
                                       |set-y!
                                       |dispatch"))]
       [:e2 (create-node "E2"
                         (strip-margin "x: E1
                                       |y: E1
                                       |set-x!
                                       |set-y!
                                       |dispatch"))]
       [:e5 (create-node "E5"
                         (strip-margin "z: E1"))]

       (d/node-attrs {:shape :note})
       (d/subgraph
        [[:node (create-node
                 (strip-margin "(z 'car)"))]
         :c5])

       [:e1 :g]
       [:e2 :g]
       [:e5 :g]

       (d/edge-attrs {:dir :none})
       [:c5 :e5]]
      d/digraph
      d/dot))
