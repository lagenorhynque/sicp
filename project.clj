(defproject sicp "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.10.0"]
                 [org.clojure/math.combinatorics "0.1.5"]
                 [org.clojure/math.numeric-tower "0.0.4"]
                 [dorothy "0.0.7"]]
  :profiles {:dev {:source-paths ["dev/src"]
                   :dependencies [[orchestra "2019.02.06-1"]
                                  [org.clojure/tools.namespace "0.2.11"]]
                   :plugins [[lein-eftest "0.5.7"]]}})
