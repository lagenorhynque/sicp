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
        exec-if-correct (fn [pass f]
                          (fn [amount]
                            (if (= pass password)
                              (f amount)
                              "Incorrect password")))
        dispatch (fn [pass m]
                   (case m
                     :withdraw (exec-if-correct pass withdraw)
                     :deposit (exec-if-correct pass deposit)
                     (throw (IllegalArgumentException.
                             (str "Unknown request -- make-account "
                                  m)))))]
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
        exec-if-correct (fn [pass f]
                          (fn [amount]
                            (if (= pass password)
                              (do (reset! incorrect-cnt 0)
                                  (f amount))
                              (do (swap! incorrect-cnt inc)
                                  (if (> @incorrect-cnt 7)
                                    (call-the-cops)
                                    "Incorrect password")))))
        dispatch (fn [pass m]
                   (case m
                     :withdraw (exec-if-correct pass withdraw)
                     :deposit (exec-if-correct pass deposit)
                     (throw (IllegalArgumentException.
                             (str "Unknown request -- make-account "
                                  m)))))]
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

;; Exercise 3.5
(defn random-in-range [low high]
  (let [ran (- high low)]
    (+ low (rand ran))))

(defn estimate-integral [p x1 y1 x2 y2 trials]
  (monte-carlo trials #(p (random-in-range x1 x2)
                          (random-in-range y1 y2))))

(defn estimate-pi [trials]
  (letfn [(p [x y]
            (<= (+ (Math/pow (- x 5) 2)
                   (Math/pow (- y 7) 2))
                (Math/pow 3 2)))]
    (-> (estimate-integral p 2 4 8 10 trials)
        (* 4)
        double)))

;; Exercise 3.6
;; TODO

;;; 3.1.3  The Costs of Introducing Assignment
