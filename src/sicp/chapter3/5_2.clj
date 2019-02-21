(ns sicp.chapter3.5-2)

;;;; 3.5  Streams

;;; 3.5.2  Infinite Streams

;; Exercise 3.53
(def s (lazy-seq (cons 1 (map + s s))))

;; Exercise 3.54
(defn mul-streams [s1 s2]
  (map * s1 s2))

(def integers (rest (range)))

(def factorials
  (lazy-seq (cons 1 (mul-streams (rest integers) factorials))))
