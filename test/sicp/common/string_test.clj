(ns sicp.common.string-test
  (:require [clojure.test :as t]
            [sicp.common.string :as sut]))

(t/deftest strip-margin-test
  (binding [sut/*newline-char* "\n"]
    (t/is (= "(defn factorial [n]
                            (if (zero? n)
                              1
                              (* n (factorial (dec n)))))"
             (sut/strip-margin "(defn factorial [n]
                            (if (zero? n)
                              1
                              (* n (factorial (dec n)))))")))

    (t/is (= "(defn factorial [n]
  (if (zero? n)
    1
    (* n (factorial (dec n)))))"
             (sut/strip-margin "(defn factorial [n]
                         |  (if (zero? n)
                         |    1
                         |    (* n (factorial (dec n)))))")))

    (t/is (= "(defn factorial [n]
  (if (zero? n)
    1
    (* n (factorial (dec n)))))"
             (sut/strip-margin "|(defn factorial [n]
                          |  (if (zero? n)
                          |    1
                          |    (* n (factorial (dec n)))))")))

    (t/is (= "(defn factorial [n]
  (if (zero? n)
    1
    (* n (factorial (dec n)))))"
             (sut/strip-margin "(defn factorial [n]
                         ;  (if (zero? n)
                         ;    1
                         ;    (* n (factorial (dec n)))))"
                               ";")))))
