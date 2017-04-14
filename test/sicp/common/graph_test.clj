(ns sicp.common.graph-test
  (:require [clojure.test :refer :all]
            [sicp.common.graph :refer :all]
            [sicp.common.string :refer [*newline-char*]]))

(deftest create-node-test
  (binding [*newline-char* "\n"]
    (is {:label
         "a"}
        (create-node "a"))
    (is {:label
         "a

bb

ccc"}
        (create-node "a" "bb" "ccc"))))
