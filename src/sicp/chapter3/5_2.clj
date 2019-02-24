(ns sicp.chapter3.5-2)

;;;; 3.5  Streams

;;; 3.5.2  Infinite Streams

;; Exercise 3.53
(defn add-streams [s1 s2]
  (map + s1 s2))

(def s (lazy-seq (cons 1 (add-streams s s))))

#_(take 10 s)

;; Exercise 3.54
(defn mul-streams [s1 s2]
  (map * s1 s2))

(def integers (rest (range)))

(def factorials
  (lazy-seq (cons 1 (mul-streams (rest integers) factorials))))

#_(take 10 factorials)
