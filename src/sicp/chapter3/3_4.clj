(ns sicp.chapter3.3-4)

;;;; 3.3  Modeling with Mutable Data

;;; 3.3.4  A Simulator for Digital Circuits

(declare get-signal set-signal! add-action!
         after-delay inverter-delay and-gate-delay or-gate-delay
         make-wire
         or-gate and-gate inverter)

(defn half-adder [a b s c]
  (let [d (make-wire)
        e (make-wire)]
    (or-gate a b d)
    (and-gate a b c)
    (inverter c e)
    (and-gate d e s)
    :ok))

(defn full-adder [a b c-in sum c-out]
  (let [s (make-wire)
        c1 (make-wire)
        c2 (make-wire)]
    (half-adder b c-in s c1)
    (half-adder a s sum c2)
    (or-gate c1 c2 c-out)
    :ok))

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

;; Exercise 3.30
(defn ripple-carry-adder [as bs ss c]
  (loop [[a & as] as
         [b & bs] bs
         [c-in & cs] (repeat (make-wire))
         [s & ss] ss
         c-out c]
    (when a
      (full-adder a b c-in s c-out)
      (recur as bs cs ss c-in)))
  :ok)

(defn call-each [procedures]
  (loop [[p & ps] procedures]
    (when p
      (p)
      (recur ps)))
  :done)

(defn make-wire []
  (let [signal-value (atom 0)
        action-procedures (atom ())]
    (letfn [(set-my-signal! [new-value]
              (if (not= @signal-value new-value)
                (do (reset! signal-value new-value)
                    (call-each @action-procedures))
                :done))
            (accept-action-procedure! [proc]
              (swap! action-procedures conj proc)
              (proc))
            (dispatch [m]
              (case m
                :get-signal @signal-value
                :set-signal! set-my-signal!
                :add-action! accept-action-procedure!
                (throw (IllegalArgumentException.
                        (str "Unknown operation -- WIRE " m)))))]
      dispatch)))

(defn get-signal [wire]
  (wire :get-signal))
(defn set-signal! [wire new-value]
  ((wire :set-signal!) new-value))
(defn add-action! [wire action-procedure]
  ((wire :add-action!) action-procedure))

;; Exercise 3.31
;; TODO

;; Exercise 3.32
;; TODO
