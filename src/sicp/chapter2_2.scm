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
;; TODO
(define (subsets s)
  (if (null? s)
      (list '())
      (let ((rest (subsets (cdr s))))
        (append rest (map <??> rest)))))

;;; 2.2.3  Sequences as Conventional Interfaces
(define (accumulate op initial sequence)
  (if (null? sequence)
      initial
      (op (car sequence)
          (accumulate op initial (cdr sequence)))))

;;; Exercise 2.33
(define (map p sequence)
  (accumulate (lambda (x y) (cons (p x) y)) '() sequence))

(define (append seq1 seq2)
  (accumulate cons seq2 seq1))

(define (length sequence)
  (accumulate (lambda (_ x) (+ 1 x)) 0 sequence))

;;; Exercise 2.34
;; TODO

;;; Exercise 2.35
;; TODO
(define (count-leaves t)
  (accumulate <??> <??> (map <??> <??>)))

;;; 2.2.4  Example: A Picture Language
