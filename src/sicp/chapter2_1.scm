;;;; 2.1  Introduction to Data Abstraction

;;; 2.1.1  Example: Arithmetic Operations for Rational Numbers

(define (add-rat x y)
  (make-rat (+ (* (numer x) (denom y))
               (* (numer y) (denom x)))
            (* (denom x) (denom y))))

(define (sub-rat x y)
  (make-rat (- (* (numer x) (denom y))
               (* (numer y) (denom x)))
            (* (denom x) (denom y))))

(define (mul-rat x y)
  (make-rat (* (numer x) (numer y))
            (* (denom x) (denom y))))

(define (div-rat x y)
  (make-rat (* (numer x) (denom y))
            (* (denom x) (numer y))))

(define (equal-rat? x y)
  (= (* (numer x) (denom y))
     (* (numer y) (denom x))))

(define x (cons 1 2))

(car x)

(cdr x)

(define y (cons 3 4))

(define z (cons x y))

(car (car z))

(car (cdr z))

(define (make-rat n d) (cons n d))

(define (numer x) (car x))

(define (denom x) (cdr x))

(define (print-rat x)
  (display (numer x))
  (display "/")
  (display (denom x))
  (newline))

(define one-half (make-rat 1 2))
(print-rat one-half)

(define one-third (make-rat 1 3))
(print-rat (add-rat one-half one-third))

(print-rat (mul-rat one-half one-third))

(print-rat (add-rat one-third one-third))

(define (make-rat n d)
  (let ((g (gcd n d)))
    (cons (/ n g) (/ d g))))

(print-rat (add-rat one-third one-third))

;; Exercise 2.1
(define (make-rat n d)
  (let ((g (gcd n d)))
    (if (negative? d)
        (cons (/ (- n) g) (/ (- d) g))
        (cons (/ n g) (/ d g)))))

(define (make-rat* n d)
  (let ((g (gcd n d))
        (f (if (negative? d)
               -
               identity)))
    (cons (/ (f n) g) (/ (f d) g))))

;;; 2.1.2  Abstraction Barriers

(define (make-rat n d)
  (cons n d))

(define (numer x)
  (let ((g (gcd (car x) (cdr x))))
    (/ (car x) g)))

(define (denom x)
  (let ((g (gcd (car x) (cdr x))))
    (/ (cdr x) g)))

;; Exercise 2.2
;; TODO

;; Exercise 2.3
;; TODO

;;; 2.1.3  What Is Meant by Data?

;;; 2.1.4  Extended Exercise: Interval Arithmetic

;; Exercise 2.5
(define (cons* x y)
  (* (expt 2 x) (expt 3 y)))

(define (car* z)
  (define (f n acc)
    (if (zero? (modulo n 2))
        (f (quotient n 2) (add1 acc))
        acc))
  (f z 0))

(define (cdr* z)
  (define (f n acc)
    (if (zero? (modulo n 3))
        (f (quotient n 3) (add1 acc))
        acc))
  (f z 0))

;; Exercise 2.6
(define zero (lambda (f) (lambda (x) x)))
(define (add-1 n)
  (lambda (f) (lambda (x) (f ((n f) x)))))

(define one
  (add-1 zero))
(define one
  (let ((n (lambda (f) (lambda (x) x))))
    (lambda (f) (lambda (x) (f ((n f) x))))))
(define one
  (lambda (f) (lambda (x) (f x))))

(define two
  (add-1 one))
(define two
  (let ((n (lambda (f) (lambda (x) (f x)))))
    (lambda (f) (lambda (x) (f ((n f) x))))))
(define two
  (lambda (f) (lambda (x) (f (f x)))))
