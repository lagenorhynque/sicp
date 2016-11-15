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

;; Exercise 2.56
;; TODO

;; Exercise 2.57
;; TODO

;; Exercise 2.58
;; TODO

;;; 2.3.3  Example: Representing Sets

;;; 2.3.4  Example: Huffman Encoding Trees
