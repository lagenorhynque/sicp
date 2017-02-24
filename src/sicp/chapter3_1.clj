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
