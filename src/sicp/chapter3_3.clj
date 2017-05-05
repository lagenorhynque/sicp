(ns sicp.chapter3-3
  (:require [dorothy.core :as d]
            [sicp.common.graph :refer [create-node]]
            [sicp.common.list :refer :all]))

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
                        #sicp/s "cons
                                |car
                                |cdr
                                |set-car!
                                |set-cdr!
                                |x")]
       [:e1 (create-node "E1"
                         #sicp/s "x: 1
                                 |y: 2
                                 |set-x!
                                 |set-y!
                                 |dispatch")]

       [:e1 :g]]
      d/digraph
      d/dot))

;; cf. resources/public/img/chapter3_3/exercise3-20_2_define-z.png
(def cons-define-z
  (-> [(d/node-attrs {:shape :record})
       [:g (create-node "global env"
                        #sicp/s "cons
                                |car
                                |cdr
                                |set-car!
                                |set-cdr!
                                |x
                                |z")]
       [:e1 (create-node "E1"
                         #sicp/s "x: 1
                                 |y: 2
                                 |set-x!
                                 |set-y!
                                 |dispatch")]
       [:e2 (create-node "E2"
                         #sicp/s "x: E1
                                 |y: E1
                                 |set-x!
                                 |set-y!
                                 |dispatch")]
       [:e1 :g]
       [:e2 :g]]
      d/digraph
      d/dot))

;; cf. resources/public/img/chapter3_3/exercise3-20_3_cdr-z.png
(def cons-cdr-z
  (-> [(d/node-attrs {:shape :record})
       [:g (create-node "global env"
                        #sicp/s "cons
                                |car
                                |cdr
                                |set-car!
                                |set-cdr!
                                |x
                                |z")]
       [:e1 (create-node "E1"
                         #sicp/s "x: 1
                                 |y: 2
                                 |set-x!
                                 |set-y!
                                 |dispatch")]
       [:e2 (create-node "E2"
                         #sicp/s "x: E1
                                 |y: E1
                                 |set-x!
                                 |set-y!
                                 |dispatch")]
       [:e3 (create-node "E3"
                         #sicp/s "z: E2")]

       (d/node-attrs {:shape :note})
       (d/subgraph
        [[:node (create-node
                 #sicp/s "(z 'cdr)")]
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
                        #sicp/s "cons
                                |car
                                |cdr
                                |set-car!
                                |set-cdr!
                                |x
                                |z")]
       [:e1 (create-node "E1"
                         #sicp/s "x: 1
                                 |y: 2
                                 |set-x!
                                 |set-y!
                                 |dispatch")]
       [:e2 (create-node "E2"
                         #sicp/s "x: E1
                                 |y: E1
                                 |set-x!
                                 |set-y!
                                 |dispatch")]
       [:e3 (create-node "E3"
                         #sicp/s "z: E2")]
       [:e4 (create-node "E4"
                         #sicp/s "z: E1
                                 |new-value: 17")]

       (d/node-attrs {:shape :note})
       (d/subgraph
        [[:node (create-node
                 #sicp/s "(z 'cdr)")]
         :c3])
       (d/subgraph
        [[:node (create-node
                  #sicp/s "((z 'set-car!) new-value)
                          |z")]
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
                        #sicp/s "cons
                                |car
                                |cdr
                                |set-car!
                                |set-cdr!
                                |x
                                |z")]
       [:e1 (create-node "E1"
                         #sicp/s "x: 17
                                 |y: 2
                                 |set-x!
                                 |set-y!
                                 |dispatch")]
       [:e2 (create-node "E2"
                         #sicp/s "x: E1
                                 |y: E1
                                 |set-x!
                                 |set-y!
                                 |dispatch")]
       [:e5 (create-node "E5"
                         #sicp/s "z: E1")]

       (d/node-attrs {:shape :note})
       (d/subgraph
        [[:node (create-node
                 #sicp/s "(z 'car)")]
         :c5])

       [:e1 :g]
       [:e2 :g]
       [:e5 :g]

       (d/edge-attrs {:dir :none})
       [:c5 :e5]]
      d/digraph
      d/dot))

;;; 3.3.2  Representing Queues

(defn front-ptr [queue]
  (car queue))
(defn rear-ptr [queue]
  (cdr queue))
(defn set-front-ptr! [queue item]
  (set-car! queue item))
(defn set-rear-ptr! [queue item]
  (set-cdr! queue item))

(defn empty-queue? [queue]
  (nil? (front-ptr queue)))

(defn make-queue []
  (kons nil nil))

(defn front-queue [queue]
  (if (empty-queue? queue)
    (throw (IllegalArgumentException.
            (str "FRONT called with an empty queue " queue)))
    (car (front-ptr queue))))

(defn insert-queue! [queue item]
  (let [new-pair (kons item nil)]
    (if (empty-queue? queue)
      (do (set-front-ptr! queue new-pair)
          (set-rear-ptr! queue new-pair))
      (do (set-cdr! (rear-ptr queue) new-pair)
          (set-rear-ptr! queue new-pair)))
    queue))

(defn delete-queue! [queue]
  (if (empty-queue? queue)
    (throw (IllegalArgumentException.
            (str "DELETE called with an empty queue " queue)))
    (do (set-front-ptr! queue (cdr (front-ptr queue)))
        queue)))

;; Exercise 3.21
(defn queue->vector [queue]
  (loop [coll (front-ptr queue)
         v []]
    (if (nil? coll)
      v
      (recur (cdr coll) (conj v (car coll))))))

(defn print-queue [queue]
  (println (queue->vector queue)))

;; Exercise 3.22
(defn make-queue' []
  (let [front-ptr (atom nil)
        rear-ptr (atom nil)
        empty-queue? (fn []
                       (nil? @front-ptr))
        front-queue (fn []
                      (if (empty-queue?)
                        (throw (IllegalArgumentException.
                                (str "FRONT called with an empty queue "
                                     @front-ptr)))
                        (car @front-ptr)))
        insert-queue! (fn [item]
                        (let [new-pair (kons item nil)]
                          (if (empty-queue?)
                            (do (reset! front-ptr new-pair)
                                (reset! rear-ptr new-pair))
                            (do (set-cdr! @rear-ptr new-pair)
                                (reset! rear-ptr new-pair)))
                          @front-ptr))
        delete-queue! (fn []
                        (if (empty-queue?)
                          (throw (IllegalArgumentException.
                                  (str "DELETE called with an empty queue "
                                       @front-ptr)))
                          (do (reset! front-ptr (cdr @front-ptr))
                              @front-ptr)))
        dispatch (fn [m]
                   (case m
                     :empty-queue? empty-queue?
                     :front-queue front-queue
                     :insert-queue! insert-queue!
                     :delete-queue! delete-queue!
                     (throw (IllegalArgumentException.
                             (str "Unknown operation " m)))))]
    dispatch))

;; Exercise 3.23
(defn make-deque []
  (kons nil nil))

(defn empty-deque? [deque]
  (nil? (front-ptr deque)))

(defn deque-element [item]
  (scheme-like-list item nil nil))
(defn deque-item [deque]
  (car deque))
(defn deque-next [deque]
  (cadr deque))
(defn deque-previous [deque]
  (caddr deque))
(defn set-deque-next! [deque ptr]
  (set-car! (cdr deque) ptr))
(defn set-deque-previous! [deque ptr]
  (set-car! (cddr deque) ptr))

(defn deque->vector [deque]
  (loop [coll (front-ptr deque)
         v []]
    (if (nil? coll)
      v
      (recur (deque-next coll) (conj v (deque-item coll))))))

(defn front-deque [deque]
  (if (empty-deque? deque)
    (throw (IllegalArgumentException.
            (str "FRONT called with an empty deque "
                 (deque->vector deque))))
    (deque-item (front-ptr deque))))

(defn rear-deque [deque]
  (if (empty-deque? deque)
    (throw (IllegalArgumentException.
            (str "REAR called with an empty deque "
                 (deque->vector deque))))
    (deque-item (rear-ptr deque))))

(defn front-insert-deque! [deque item]
  (let [new-elem (deque-element item)]
    (if (empty-deque? deque)
      (do (set-front-ptr! deque new-elem)
          (set-rear-ptr! deque new-elem))
      (do (set-deque-next! new-elem (front-ptr deque))
          (set-deque-previous! (front-ptr deque) new-elem)
          (set-front-ptr! deque new-elem)))
    (deque->vector deque)))

(defn rear-insert-deque! [deque item]
  (let [new-elem (deque-element item)]
    (if (empty-deque? deque)
      (do (set-front-ptr! deque new-elem)
          (set-rear-ptr! deque new-elem))
      (do (set-deque-next! (rear-ptr deque) new-elem)
          (set-deque-previous! new-elem (rear-ptr deque))
          (set-rear-ptr! deque new-elem)))
    (deque->vector deque)))

(defn front-delete-deque! [deque]
  (if (empty-deque? deque)
    (throw (IllegalArgumentException.
            (str "DELETE called with an empty deque "
                 (deque->vector deque))))
    (do (if-let [next (deque-next (front-ptr deque))]
          (do (set-deque-previous! next nil)
              (set-front-ptr! deque next))
          (do (set-front-ptr! deque nil)
              (set-rear-ptr! deque nil)))
        (deque->vector deque))))

(defn rear-delete-deque! [deque]
  (if (empty-deque? deque)
    (throw (IllegalArgumentException.
            (str "DELETE called with an empty deque "
                 (deque->vector deque))))
    (do (if-let [previous (deque-previous (rear-ptr deque))]
          (do (set-deque-next! previous nil)
              (set-rear-ptr! deque previous))
          (do (set-front-ptr! deque nil)
              (set-rear-ptr! deque nil)))
        (deque->vector deque))))

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
  (scheme-like-list '*table*))

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
              (kons (scheme-like-list key-1
                                      (kons key-2 value))
                    (cdr table))))
  :ok)

(defn make-table' []
  (let [local-table (scheme-like-list '*table*)
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
                              (kons (scheme-like-list key-1
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
  (let [local-table (scheme-like-list '*table*)
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
                              (kons (scheme-like-list key-1
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
        (let [subtable (scheme-like-list (first keys) false)]
          (set-cdr! table (kons subtable (cdr table)))
          (recur (rest keys) (cdr subtable))))))
  :ok)

;; Exercise 3.26
(defn new-node [key]
  (scheme-like-list key nil nil false))
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
;; TODO

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
