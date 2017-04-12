(ns sicp.common.list)

(defprotocol SchemeLikeList
  (car [this])
  (cdr [this])
  (set-car! [this a'])
  (set-cdr! [this b']))

(deftype Pair [a b]
  SchemeLikeList
  (car [_]
    @a)
  (cdr [_]
    @b)
  (set-car! [this a']
    (reset! a a')
    this)
  (set-cdr! [this b']
    (reset! b b')
    this)

  Object
  (toString [_]
    (str "(" @a " . " @b ")"))
  (equals [this obj]
    (or (identical? this obj)
        (and (instance? Pair obj)
             (= @a @(:a obj))
             (= @b @(:b obj)))))
  (hashCode [_]
    (reduce #(+ (* 31 %1) (hash %2))
            17
            [@a @b])))

(defn kons [a b]
  (->Pair (atom a) (atom b)))

(defn scheme-like-list [& xs]
  (reduce #(kons %2 %1) nil (reverse xs)))

(defn pair? [x]
  (satisfies? SchemeLikeList x))
