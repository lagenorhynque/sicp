(ns sicp.chapter1.2-6
  (:require [sicp.chapter1.1-4 :refer [square]]))

;;;; 1.2  Procedures and the Processes They Generate

;;; 1.2.6  Example: Testing for Primality

(defn divides? [a b]
  (zero? (rem b a)))

(defn find-divisor [n test-divisor]
  (cond
    (> (square test-divisor) n) n
    (divides? test-divisor n) test-divisor
    :else (recur n (inc test-divisor))))

(defn smallest-divisor [n]
  (find-divisor n 2))

(defn prime? [n]
  (== n (smallest-divisor n)))

(defn expmod [base exp m]
  (cond
    (zero? exp) 1
    (even? exp) (rem (square (expmod base (/ exp 2) m))
                     m)
    :else (rem (*' base (expmod base (dec exp) m))
               m)))

(defn fermat-test [n]
  (letfn [(try-it [a]
            (== (expmod a n n) a))]
    (try-it (inc (rand-int (dec n))))))

(defn fast-prime? [n times]
  (cond
    (zero? times) true
    (fermat-test n) (recur n (dec times))
    :else false))

;; Exercise 1.21
(smallest-divisor 199)
;; => 199
(smallest-divisor 1999)
;; => 1999
(smallest-divisor 19999)
;; => 7

;; Exercise 1.22
(defn report-prime [elapsed-time]
  (print " *** ")
  (print (/ elapsed-time 1000.0) "μs"))
(defn start-prime-test [n start-time]
  (when (prime? n)
    (report-prime (- (System/nanoTime) start-time))))
(defn timed-prime-test [n]
  (print n)
  (start-prime-test n (System/nanoTime))
  (println))

(defn search-for-primes [start end]
  (letfn [(search-iter [n]
            (when (< n end)
              (timed-prime-test n)
              (recur (+ n 2))))]
    (search-iter (if (odd? start) start (inc start)))))

(search-for-primes 1000 1050)
;; 1001
;; 1003
;; 1005
;; 1007
;; 1009 *** 10.807 μs
;; 1011
;; 1013 *** 10.156 μs
;; 1015
;; 1017
;; 1019 *** 26.086 μs
;; 1021 *** 32.337 μs
;; 1023
;; 1025
;; 1027
;; 1029
;; 1031 *** 5.139 μs
;; 1033 *** 5.503 μs
;; 1035
;; 1037
;; 1039 *** 5.226 μs
;; 1041
;; 1043
;; 1045
;; 1047
;; 1049 *** 5.336 μs
;; => the three smallest primes larger than 1000: 1009, 1013, 1019
(search-for-primes 10000 10050)
;; 10001
;; 10003
;; 10005
;; 10007 *** 14.395 μs
;; 10009 *** 13.566 μs
;; 10011
;; 10013
;; 10015
;; 10017
;; 10019
;; 10021
;; 10023
;; 10025
;; 10027
;; 10029
;; 10031
;; 10033
;; 10035
;; 10037 *** 14.194 μs
;; 10039 *** 13.756 μs
;; 10041
;; 10043
;; 10045
;; 10047
;; 10049
;; => the three smallest primes larger than 10,000: 10007, 10009, 10037
(search-for-primes 100000 100050)
;; 100001
;; 100003 *** 47.944 μs
;; 100005
;; 100007
;; 100009
;; 100011
;; 100013
;; 100015
;; 100017
;; 100019 *** 41.69 μs
;; 100021
;; 100023
;; 100025
;; 100027
;; 100029
;; 100031
;; 100033
;; 100035
;; 100037
;; 100039
;; 100041
;; 100043 *** 41.538 μs
;; 100045
;; 100047
;; 100049 *** 41.541 μs
;; => the three smallest primes larger than 100,000: 100003, 100019, 100043
(search-for-primes 1000000 1000050)
;; 1000001
;; 1000003 *** 134.45 μs
;; 1000005
;; 1000007
;; 1000009
;; 1000011
;; 1000013
;; 1000015
;; 1000017
;; 1000019
;; 1000021
;; 1000023
;; 1000025
;; 1000027
;; 1000029
;; 1000031
;; 1000033 *** 129.381 μs
;; 1000035
;; 1000037 *** 129.448 μs
;; 1000039 *** 197.748 μs
;; 1000041
;; 1000043
;; 1000045
;; 1000047
;; 1000049
;; => the three smallest primes larger than 1,000,000: 1000003, 1000033, 1000037

;; Exercise 1.23
;; TODO

;; Exercise 1.24
;; TODO

;; Exercise 1.25
;; TODO

;; Exercise 1.26
;; TODO

;; Exercise 1.27
;; TODO

;; Exercise 1.28
;; TODO
