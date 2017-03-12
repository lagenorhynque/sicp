(ns sicp.chapter3-1
  (:require [clojure.math.numeric-tower :as math]))

;;;; 3.1  Assignment and Local State

;;; 3.1.1  Local State Variables

(def balance (atom 100))

(defn withdraw [amount]
  (if (>= @balance amount)
    (swap! balance - amount)
    "Insufficient funds"))

(def new-withdraw
  (let [balance (atom 100)]
    (fn [amount]
      (if (>= @balance amount)
        (swap! balance - amount)
        "Insufficient funds"))))

(defn make-withdraw [balance]
  (let [balance (atom balance)]
    (fn [amount]
      (if (>= @balance amount)
        (swap! balance - amount)
        "Insufficient funds"))))

(defn make-account [balance]
  (let [balance (atom balance)
        withdraw (fn [amount]
                   (if (>= @balance amount)
                     (swap! balance - amount)
                     "Insufficient funds"))
        deposit (fn [amount]
                  (swap! balance + amount))
        dispatch (fn [m]
                   (case m
                     :withdraw withdraw
                     :deposit deposit
                     (throw (IllegalArgumentException.
                             (str "Unknown request -- make-account "
                                  m)))))]
    dispatch))

;; Exercise 3.1
(defn make-accumulator [init]
  (let [sum (atom init)]
    (fn [n]
      (swap! sum + n))))

;; Exercise 3.2
(defn make-monitored [f]
  (let [cnt (atom 0)]
    (fn [x]
      (case x
        :how-many-calls? @cnt
        :reset-count (reset! cnt 0)
        (do (swap! cnt inc)
            (f x))))))

;; Exercise 3.3
(defn make-account' [balance password]
  (let [balance (atom balance)
        withdraw (fn [amount]
                   (if (>= @balance amount)
                     (swap! balance - amount)
                     "Insufficient funds"))
        deposit (fn [amount]
                  (swap! balance + amount))
        dispatch (fn [pass m]
                   (if (= pass password)
                     (case m
                       :withdraw withdraw
                       :deposit deposit
                       (throw (IllegalArgumentException.
                               (str "Unknown request -- make-account "
                                    m))))
                     (fn [_] "Incorrect password")))]
    dispatch))

;; Exercise 3.4
(defn call-the-cops []
  "Calling the cops...")

(defn make-account'' [balance password]
  (let [balance (atom balance)
        withdraw (fn [amount]
                   (if (>= @balance amount)
                     (swap! balance - amount)
                     "Insufficient funds"))
        deposit (fn [amount]
                  (swap! balance + amount))
        incorrect-cnt (atom 0)
        dispatch (fn [pass m]
                   (if (= pass password)
                     (do (reset! incorrect-cnt 0)
                         (case m
                           :withdraw withdraw
                           :deposit deposit
                           (throw (IllegalArgumentException.
                                   (str "Unknown request -- make-account "
                                        m)))))
                     (do (swap! incorrect-cnt inc)
                         (fn [_]
                           (if (> @incorrect-cnt 7)
                             (call-the-cops)
                             "Incorrect password")))))]
    dispatch))

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

;;; 3.1.3  The Costs of Introducing Assignment

;; Exercise 3.7
(defn make-account''' [balance password]
  (let [balance (atom balance)
        withdraw (fn [amount]
                   (if (>= @balance amount)
                     (swap! balance - amount)
                     "Insufficient funds"))
        deposit (fn [amount]
                  (swap! balance + amount))
        correct? #(= % password)
        dispatch (fn [pass m]
                   (if (= m :correct?)
                     (correct? pass)
                     (if (correct? pass)
                       (case m
                         :withdraw withdraw
                         :deposit deposit
                         (throw (IllegalArgumentException.
                                 (str "Unknown request -- make-account "
                                      m))))
                       (fn [_] "Incorrect password"))))]
    dispatch))

(defn make-joint [account password new-password]
  (if (account password :correct?)
    (fn [pass m]
      (if (= pass new-password)
        (account password m)
        (fn [_] "Incorrect password")))
    (throw (IllegalArgumentException. "Incorrect password"))))

;; Exercise 3.8
(def f
  (let [n (atom 2)]
    (fn [x]
      (swap! n #(- 1 % x)))))
