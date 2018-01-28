(ns sicp.common.graph-test
  (:require [clojure.test :as t]
            [sicp.common.graph :as sut]
            [sicp.common.string :refer [*newline-char*]]))

(t/deftest create-node-test
  (binding [*newline-char* "\n"]
    (t/is {:label
           "a"}
          (sut/create-node "a"))
    (t/is {:label
           "a

bb

ccc"}
          (sut/create-node "a" "bb" "ccc"))))
