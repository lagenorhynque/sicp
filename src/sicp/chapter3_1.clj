(ns sicp.chapter3-1)

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
                     (throw (Exception. (str "Unknown request -- make-account "
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
