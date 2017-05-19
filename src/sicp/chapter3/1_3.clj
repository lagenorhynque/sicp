(ns sicp.chapter3.1-3)

;;;; 3.1  Assignment and Local State

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
