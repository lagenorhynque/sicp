(ns sicp.chapter2.2-3)

;;;; 2.2  Hierarchical Data and the Closure Property

;;; 2.2.3  Sequences as Conventional Interfaces

;; Exercise 2.33
;; TODO

;; Exercise 2.34
;; TODO

;; Exercise 2.35
;; TODO

;; Exercise 2.36
;; TODO

;; Exercise 2.37
;; TODO

;; Exercise 2.38
;; TODO

;; Exercise 2.39
;; TODO

;; Exercise 2.40
;; TODO

;; Exercise 2.41
;; TODO

;; Exercise 2.42
(defn queens [board-size]
  (let [pos (fn [r c]
              [r c])
        row-pos (fn [p]
                  (first p))
        column-pos (fn [p]
                     (second p))
        empty-board []
        safe? (fn [k positions]
                (let [[k-position & rest-positions] positions
                      k-row (row-pos k-position)
                      k-column (column-pos k-position)]
                  (not-any? (fn [position]
                              (let [row (row-pos position)
                                    column (column-pos position)
                                    row-diff (- k-row row)
                                    column-diff (- k-column column)]
                                (or (zero? row-diff)
                                    (zero? column-diff)
                                    (= (Math/abs row-diff) (Math/abs column-diff)))))
                            rest-positions)))
        adjoin-position (fn [new-row k rest-of-queens]
                          (cons (pos new-row k) rest-of-queens))
        queen-cols (fn queen-cols [k]
                     (if (zero? k)
                       [empty-board]
                       (filter
                        (fn [positions] (safe? k positions))
                        (mapcat
                         (fn [rest-of-queens]
                           (map (fn [new-row]
                                  (adjoin-position new-row k rest-of-queens))
                                (range 1 (inc board-size))))
                         (queen-cols (dec k))))))]
    (queen-cols board-size)))

;; Exercise 2.43
;; TODO
