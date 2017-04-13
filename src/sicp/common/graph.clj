(ns sicp.common.graph
  (:require [clojure.string :as str]
            [sicp.common.string :refer [*newline-char*]]))

(defn create-node [& xs]
  {:label (str/join (str *newline-char* *newline-char*) xs)})
