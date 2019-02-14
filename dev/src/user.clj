(ns user
  (:require [clojure.repl :refer :all]
            [clojure.spec.alpha :as s]
            [clojure.tools.namespace.repl]
            [orchestra.spec.test :as stest]))

(defn refresh [& options]
  (let [result (apply clojure.tools.namespace.repl/refresh options)]
    (stest/instrument)
    result))
