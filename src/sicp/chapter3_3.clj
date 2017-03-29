(ns sicp.chapter3-3
  (:require [dorothy.core :as d]
            [sicp.chapter3-2 :refer [create-node]]))

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
;; TODO

;; Exercise 3.14
;; TODO

;; Exercise 3.15
;; TODO

;;; 3.3.2  Representing Queues

;;; 3.3.3  Representing Tables

;;; 3.3.4  A Simulator for Digital Circuits

;;; 3.3.5  Propagation of Constraints
