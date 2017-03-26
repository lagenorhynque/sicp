(ns sicp.chapter3-2
  (:require [clojure.string :as str]
            [dorothy.core :as d]))

;; Exercise 3.9
(defn strip-margin
  ([s]
   (strip-margin s "\\|"))
  ([s delimiter]
   (->> s
        (str/split-lines)
        (map #(str/replace-first
               %
               (re-pattern (str "^\\s*" delimiter))
               ""))
        (str/join "\n"))))

;; recursive version
(def factorial-recursive
  (-> [(d/node-attrs {:shape :record})
       [:g {:label "global env"}]
       [:e1 {:label "E1"}]
       [:e2 {:label "E2"}]
       [:e3 {:label "E3"}]
       [:e4 {:label "E4"}]
       [:e5 {:label "E5"}]
       [:e6 {:label "E6"}]
       [:e1 :g]
       [:e2 :g]
       [:e3 :g]
       [:e4 :g]
       [:e5 :g]
       [:e6 :g]]
      d/digraph
      d/dot))

;; iterative version
