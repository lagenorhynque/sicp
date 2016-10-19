;;;; 2.2  Hierarchical Data and the Closure Property

;;; 2.2.1  Representing Sequences

;;; 2.2.2  Hierarchical Structures

;; Exercise 2.24
(list 1 (list 2 (list 3 4)))
;=> (1 (2 (3 4)))

;; Exercise 2.25
(car (cdaddr'(1 3 (5 7) 9)))

(caar '((7)))

(cadadr (cadadr (cadadr '(1 (2 (3 (4 (5 (6 7)))))))))

;; Exercise 2.26
(define x (list 1 2 3))
(define y (list 4 5 6))

(concat x y)
;=> (1 2 3 4 5 6)

(cons x y)
;=> ((1 2 3) 4 5 6)

(list x y)
;=> ((1 2 3) (4 5 6))

;; Exercise 2.27
(define (reverse coll)
  (define (rev coll acc)
    (if (null? coll)
        acc
        (rev (cdr coll)
             (cons (car coll) acc))))
  (rev coll '()))

(define (deep-reverse coll)
  (define (rev coll acc)
    (cond ((not (list? coll)) coll)
          ((null? coll) acc)
          (else (rev (cdr coll)
                     (cons (deep-reverse (car coll)) acc)))))
  (rev coll '()))

;; TODO: mapで書き換える

(define x (list (list 1 2) (list 3 4)))
(reverse x)
(deep-reverse x)

;; Exercise 2.28
(define (fringe tree)
  (cond ((not (list? tree)) (list tree))
        ((null? tree) '())
        (else (append (fringe (car tree))
                      (fringe (cadr tree))))))

(define x (list (list 1 2) (list 3 4)))
(fringe x)
(fringe (list x x))

;; TODO: consを使うように書き換え

;; Exercise 2.29
(define (make-mobile left right)
  (list left right))

(define (make-branch length structure)
  (list length structure))

;; a.
(define (left-branch mobile)
  (car mobile))

(define (right-branch mobile)
  (cadr mobile))

(define (branch-length branch)
  (car branch))

(define (branch-structure branch)
  (cadr branch))

;; b.
(define (total-weight mobile)
  (define (branch-weight branch)
    (let ((s (branch-structure branch)))
      (if (number? s)
          s
          (total-weight s))))
  (+ (branch-weight (left-branch mobile))
     (branch-weight (right-branch mobile))))

;; c.
;; TODO

;; Exercise 2.30
;; directly
(define (square-tree tree)
  (cond ((null? tree) '())
        ((not (pair? tree)) (* tree tree))
        (else (cons (square-tree (car tree))
                    (square-tree (cdr tree))))))

;; by using map and recursion
(define (square-tree tree)
  (map (lambda (t)
         (if (pair? t)
             (square-tree t)
             (* t t)))
       tree))

;; Exercise 2.31
(define (tree-map f tree)
  (map (lambda (t)
         (if (pair? t)
             (tree-map f t)
             (f t)))
       tree))

;; Exercise 2.32
(define (subsets s)
  (if (null? s)
      (list '())
      (let ((rest (subsets (cdr s))))
        (append rest (map (lambda (x) (cons (car s) x)) rest)))))

;;; 2.2.3  Sequences as Conventional Interfaces

(define (accumulate op initial sequence)
  (if (null? sequence)
      initial
      (op (car sequence)
          (accumulate op initial (cdr sequence)))))

;; Exercise 2.33
(define (map p sequence)
  (accumulate (lambda (x y) (cons (p x) y)) '() sequence))

(define (append seq1 seq2)
  (accumulate cons seq2 seq1))

(define (length sequence)
  (accumulate (lambda (_ x) (+ 1 x)) 0 sequence))

;; Exercise 2.34
(define (horner-eval x coefficient-sequence)
  (accumulate (lambda (this-coeff higher-terms)
                (+ (* higher-terms x)
                   this-coeff))
              0
              coefficient-sequence))

;; Exercise 2.35
(define (enumerate-tree tree)
  (cond ((null? tree) '())
        ((not (pair? tree)) (list tree))
        (else (append (enumerate-tree (car tree))
                      (enumerate-tree (cdr tree))))))

(define (count-leaves t)
  (accumulate + 0 (map (lambda (_) 1) (enumerate-tree t))))

;; Exercise 2.36
(define (accumulate-n op init seqs)
  (if (null? (car seqs))
      '()
      (cons (accumulate op init (map car seqs))
            (accumulate-n op init (map cdr seqs)))))

;; Exercise 2.37
(define (dot-product v w)
  (accumulate + 0 (map * v w)))

(define (matrix-*-vector m v)
  (map (lambda (w) (dot-product w v)) m))

(define (transpose mat)
  (accumulate-n cons '() mat))

(define (matrix-*-matrix m n)
  (let ((cols (transpose n)))
    (map (lambda (v) (matrix-*-vector cols v)) m)))

;; Exercise 2.38
(define fold-right accumulate)

(define (fold-left op initial sequence)
  (define (iter result rest)
    (if (null? rest)
        result
        (iter (op result (car rest))
              (cdr rest))))
  (iter initial sequence))

(fold-right / 1 (list 1 2 3))
;=> 3/2
(fold-left / 1 (list 1 2 3))
;=> 1/6
(fold-right list '() (list 1 2 3))
;=> (1 (2 (3 ())))
(fold-left list '() (list 1 2 3))
;=> (((() 1) 2) 3)

;; Exercise 2.39
(define (reverse sequence)
  (fold-right (lambda (x y) (append y (list x))) '() sequence))

(define (reverse sequence)
  (fold-left (lambda (x y) (cons y x)) '() sequence))

;; Exercise 2.40
(define (enumerate-interval low high)
  (if (> low high)
      '()
      (cons low (enumerate-interval (+ low 1) high))))

(define (flatmap proc seq)
  (accumulate append '() (map proc seq)))

(define (unique-pairs n)
  (flatmap
   (lambda (i)
     (map (lambda (j)
            (list i j))
          (enumerate-interval 1 (- i 1))))
   (enumerate-interval 2 n)))

(define (prime? n)
  (define (divides? a b)
    (= (remainder b a) 0))
  (define (find-divisor n test-divisor)
    (cond ((> (* test-divisor test-divisor) n) n)
            ((divides? test-divisor n) test-divisor)
            (else (find-divisor n (+ test-divisor 1)))))
  (define (smallest-divisor n)
    (find-divisor n 2))
  (= n (smallest-divisor n)))

(define (prime-sum-pairs n)
  (define (prime-sum? pair)
    (prime? (+ (car pair) (cadr pair))))
  (define (make-pair-sum pair)
    (list (car pair) (cadr pair) (+ (car pair) (cadr pair))))
  (map make-pair-sum (filter prime-sum? (unique-pairs n))))

;; Exercise 2.41

;; Exercise 2.42

;; Exercise 2.43

;;; 2.2.4  Example: A Picture Language
