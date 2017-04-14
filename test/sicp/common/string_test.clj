(ns sicp.common.string-test
  (:require [clojure.test :refer :all]
            [sicp.common.string :refer :all]))

(deftest strip-margin-test
  (binding [*newline-char* "\n"]
    (is (= "(defn factorial [n]
                            (if (zero? n)
                              1
                              (* n (factorial (dec n)))))"
           (strip-margin "(defn factorial [n]
                            (if (zero? n)
                              1
                              (* n (factorial (dec n)))))")))

    (is (= "(defn factorial [n]
  (if (zero? n)
    1
    (* n (factorial (dec n)))))"
           (strip-margin "(defn factorial [n]
                         |  (if (zero? n)
                         |    1
                         |    (* n (factorial (dec n)))))")))

    (is (= "(defn factorial [n]
  (if (zero? n)
    1
    (* n (factorial (dec n)))))"
           (strip-margin "|(defn factorial [n]
                          |  (if (zero? n)
                          |    1
                          |    (* n (factorial (dec n)))))")))

    (is (= "(defn factorial [n]
  (if (zero? n)
    1
    (* n (factorial (dec n)))))"
           (strip-margin "(defn factorial [n]
                         ;  (if (zero? n)
                         ;    1
                         ;    (* n (factorial (dec n)))))"
                         ";")))))
