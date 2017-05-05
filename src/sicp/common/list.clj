(ns sicp.common.list
  (:require [clojure.math.combinatorics :as comb]
            [clojure.string :as str]))

(defprotocol SchemeLikeList
  (car [this])
  (cdr [this])
  (set-car! [this a'])
  (set-cdr! [this b']))

(deftype Pair [a b]
  SchemeLikeList
  (car [_]
    @a)
  (cdr [_]
    @b)
  (set-car! [this a']
    (reset! a a')
    this)
  (set-cdr! [this b']
    (reset! b b')
    this)

  Object
  (toString [_]
    (str "(" @a " . " @b ")"))
  (equals [this obj]
    (or (identical? this obj)
        (and (instance? Pair obj)
             (= @a @(.a obj))
             (= @b @(.b obj)))))
  (hashCode [_]
    (reduce #(+ (* 31 %1) (hash %2))
            17
            [@a @b])))

(defn kons [a b]
  (->Pair (atom a) (atom b)))

(defn scheme-like-list [& xs]
  (reduce #(kons %2 %1) nil (reverse xs)))

(defn pair? [x]
  (satisfies? SchemeLikeList x))

(defmacro def-cxrs [from to]
  (letfn [(f [n]
            (map (fn [cs]
                   [(symbol (str \c (str/join cs) \r))
                    (reduce #(list ({\a 'car \d 'cdr} %2) %1)
                            'x
                            (reverse cs))])
                 (comb/selections [\a \d] n)))]
    `(do ~@(map (fn [[name body]]
                  `(defn ~name [~'x]
                     ~body))
                (mapcat f (range from (inc to)))))))
(def-cxrs 2 4)
