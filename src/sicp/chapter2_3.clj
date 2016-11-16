(ns sicp.chapter2-3)

;;;; 2.3  Symbolic Data

;;; 2.3.1  Quotation

(defn memq [item x]
  (cond
    (nil? x) false
    (= item (first x)) x
    :else (recur item (next x))))

;; Exercise 2.53
(list 'a 'b 'c)
;=> (a b c
(list (list 'george))
;=> ((george))
(next '((x1 x2) (y1 y2)))
;=> ((y1 y2))
(fnext '((x1 x2) (y1 y2)))
;=> (y1 y2)
(list? (first '(a short list)))
;=> false
(memq 'red '((red shoes) (blue socks)))
;=> false
(memq 'red '(red shoes blue socks))
;=> (red shoes blue socks)

;; Exercise 2.54
(defn equal? [x y]
  (cond
    (and (nil? x) (nil? y)) true
    (and (symbol? x) (symbol? y)) (= x y)
    (and (list? x) (list? y)) (and (equal? (first x) (first y))
                                   (equal? (next x) (next y)))
    :else false))

;; Exercise 2.55
(first ''abracadabra)
;=> (first (quote (quote abracadabra)))
;=> (first '(quote abracadabra))
;=> quote

;;; 2.3.2  Example: Symbolic Differentiation

(defn variable? [x]
  (symbol? x))

(defn same-variable? [v1 v2]
  (and (variable? v1)
       (variable? v2)
       (= v1 v2)))

(defn =number? [exp n]
  (and (number? exp)
       (= exp n)))

(defn make-sum [a1 a2]
  (cond
    (=number? a1 0) a2
    (=number? a2 0) a1
    (and (number? a1) (number? a2)) (+ a1 a2)
    :else (list '+ a1 a2)))

(defn make-product [m1 m2]
  (cond
    (or (=number? m1 0) (=number? m2 0)) 0
    (=number? m1 1) m2
    (=number? m2 1) m1
    (and (number? m1) (number? m2)) (* m1 m2)
    :else (list '* m1 m2)))

(defn sum? [x]
  (and (list? x)
       (= (first x) '+)))

(defn addend [s]
  (fnext s))

(defn augend [s]
  (first (nnext s)))

(defn product? [x]
  (and (list? x)
       (= (first x) '*)))

(defn multiplier [p]
  (fnext p))

(defn multiplicand [p]
  (first (nnext p)))

(defn deriv [exp var]
  (cond
    (number? exp) 0
    (variable? exp) (if (same-variable? exp var) 1 0)
    (sum? exp) (make-sum (deriv (addend exp) var)
                         (deriv (augend exp) var))
    (product? exp) (make-sum (make-product (multiplier exp)
                                           (deriv (multiplicand exp) var))
                             (make-product (deriv (multiplier exp) var)
                                           (multiplicand exp)))
    :else (throw (Exception. (str "unknown expression type -- DERIV " exp)))))

;; Exercise 2.56
(defn make-exponentiation [b e]
  (cond
    (=number? e 0) 1
    (=number? e 1) b
    :else (list '** b e)))

(defn exponentiation? [x]
  (and (list? x)
       (= (first x) '**)))

(defn base [e]
  (fnext e))

(defn exponent [e]
  (first (nnext e)))

(defn deriv' [exp var]
  (cond
    (number? exp) 0
    (variable? exp) (if (same-variable? exp var) 1 0)
    (sum? exp) (make-sum (deriv (addend exp) var)
                         (deriv (augend exp) var))
    (product? exp) (make-sum (make-product (multiplier exp)
                                           (deriv (multiplicand exp) var))
                             (make-product (deriv (multiplier exp) var)
                                           (multiplicand exp)))
    (exponentiation? exp) (make-product (exponent exp)
                                        (make-exponentiation (base exp)
                                                             (dec (exponent exp))))
    :else (throw (Exception. (str "unknown expression type -- DERIV " exp)))))

;; Exercise 2.57
;; TODO

;; Exercise 2.58
;; TODO

;;; 2.3.3  Example: Representing Sets

;;; 2.3.4  Example: Huffman Encoding Trees
