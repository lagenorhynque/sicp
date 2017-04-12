(ns sicp.common.string
  (:require [clojure.string :as str]))

(def ^:dynamic *newline-char*
  "&#92;n")

(defn strip-margin
  ([s]
   (strip-margin s "\\|"))
  ([s delimiter]
   (let [p (re-pattern (str "^\\s*" delimiter))]
     (->> s
          (str/split-lines)
          (map #(str/replace-first % p ""))
          (str/join *newline-char*)))))
