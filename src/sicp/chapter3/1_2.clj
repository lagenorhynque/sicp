(ns sicp.chapter3.1-2
  (:require [clojure.math.numeric-tower :as math]))

;;;; 3.1  Assignment and Local State

;;; 3.1.2  The Benefits of Introducing Assignment

(defn monte-carlo [trials experiment]
  (letfn [(iter [trials-remaining trials-passed]
            (cond
              (zero? trials-remaining) (/ trials-passed trials)
              (experiment) (recur (dec trials-remaining)
                                  (inc trials-passed))
              :else (recur (dec trials-remaining)
                           trials-passed)))]
    (iter trials 0)))

(defn cesaro-test []
  (= (math/gcd (rand-int 1000) (rand-int 1000)) 1))

(defn estimate-pi [trials]
  (->> (monte-carlo trials cesaro-test)
       (/ 6)
       math/sqrt))

;; Exercise 3.5
(defn random-in-range [low high]
  (let [ran (- high low)]
    (+ low (rand ran))))

(defn estimate-integral [p x1 y1 x2 y2 trials]
  (monte-carlo trials #(p (random-in-range x1 x2)
                          (random-in-range y1 y2))))

(defn estimate-pi' [trials]
  (letfn [(p [x y]
            (<= (+ (math/expt x 2)
                   (math/expt y 2))
                1))]
    (->> (estimate-integral p -1 -1 1 1 trials)
         (* 4)
         double)))

;; Exercise 3.6
;; dummy implementation
(def random-init 0)
(defn rand-update [n]
  (inc n))

(def rand'
  (let [r (atom random-init)]
    (fn [m]
      (case m
        :generate (swap! r rand-update)
        :reset #(reset! r %)
        (throw (IllegalArgumentException.
                (str "Unknown request -- rand' "
                     m)))))))
