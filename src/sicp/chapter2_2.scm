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
