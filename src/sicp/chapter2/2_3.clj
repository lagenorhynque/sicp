(ns sicp.chapter2.2-3)

;;;; 2.2  Hierarchical Data and the Closure Property

;;; 2.2.3  Sequences as Conventional Interfaces

(defn accumulate [op initial sequence]
  (if (empty? sequence)
    initial
    (op (first sequence)
        (accumulate op initial (rest sequence)))))

;; Exercise 2.33
(defn map' [p sequence]
  (accumulate (fn [x y] (cons (p x) y)) nil sequence))

(defn append [seq1 seq2]
  (accumulate cons seq2 seq1))

(defn length [sequence]
  (accumulate (fn [_ acc] (inc acc))  0 sequence))

;; Exercise 2.34
(defn horner-eval [x coefficient-sequence]
  (accumulate (fn [this-coeff higher-terms]
                (+ this-coeff (* x higher-terms)))
              0
              coefficient-sequence))

;; Exercise 2.35
(defn count-leaves [t]
  (accumulate + 0 (map (fn [x]
                         (if (not (seq? x))
                           1
                           (count-leaves x)))
                       t)))

;; Exercise 2.36
(defn accumulate-n [op init seqs]
  (if (empty? (first seqs))
    nil
    (cons (accumulate op init (map first seqs))
          (accumulate-n op init (map rest seqs)))))

;; Exercise 2.37
(defn dot-product [v w]
  (accumulate + 0 (map * v w)))

(defn matrix-*-vector [m v]
  (map #(dot-product % v) m))

(defn transpose [mat]
  (accumulate-n cons nil mat))

(defn matrix-*-matrix [m n]
  (let [cols (transpose n)]
    (map #(matrix-*-vector cols %) m)))

;; Exercise 2.38
;; TODO

;; Exercise 2.39
(defn fold-left [op initial sequence]
  (letfn [(iter [result rest']
            (if (empty? rest')
              result
              (recur (op result (first rest'))
                     (rest rest'))))]
    (iter initial sequence)))

(defn reverse' [sequence]
  (fold-left (fn [x y] (cons y x)) nil sequence))

(defn reverse'' [sequence]
  (accumulate (fn [x y] (concat y (list x))) nil sequence))

;; Exercise 2.40
(defn unique-pairs [n]
  (mapcat (fn [i]
            (map (fn [j] [i j])
                 (range 1 i)))
          (range 1 (inc n))))

;; Exercise 2.41
(defn triples [n s]
  (->> (unique-pairs n)
       (mapcat (fn [[i j]]
                 (map (fn [k]
                        [i j k])
                      (range j))))
       (filter (fn [ijk]
                 (= (apply + ijk) s)))))

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
