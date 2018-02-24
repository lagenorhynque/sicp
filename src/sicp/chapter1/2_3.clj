(ns sicp.chapter1.2-3
  (:require [clojure.math.numeric-tower :refer [abs]]))

;;;; 1.2  Procedures and the Processes They Generate

;;; 1.2.3  Orders of Growth

;; Exercise 1.14
;; tracing `cc` with cider-toggle-trace-var
;; sicp.chapter1.2-2> (count-change 11)
;; TRACE t11324: (sicp.chapter1.2-2/cc 11 5)
;; TRACE t11325: | (sicp.chapter1.2-2/cc 11 4)
;; TRACE t11326: | | (sicp.chapter1.2-2/cc 11 3)
;; TRACE t11327: | | | (sicp.chapter1.2-2/cc 11 2)
;; TRACE t11328: | | | | (sicp.chapter1.2-2/cc 11 1)
;; TRACE t11329: | | | | | (sicp.chapter1.2-2/cc 11 0)
;; TRACE t11329: | | | | | => 0
;; TRACE t11330: | | | | | (sicp.chapter1.2-2/cc 10 1)
;; TRACE t11331: | | | | | | (sicp.chapter1.2-2/cc 10 0)
;; TRACE t11331: | | | | | | => 0
;; TRACE t11332: | | | | | | (sicp.chapter1.2-2/cc 9 1)
;; TRACE t11333: | | | | | | | (sicp.chapter1.2-2/cc 9 0)
;; TRACE t11333: | | | | | | | => 0
;; TRACE t11334: | | | | | | | (sicp.chapter1.2-2/cc 8 1)
;; TRACE t11335: | | | | | | | | (sicp.chapter1.2-2/cc 8 0)
;; TRACE t11335: | | | | | | | | => 0
;; TRACE t11336: | | | | | | | | (sicp.chapter1.2-2/cc 7 1)
;; TRACE t11337: | | | | | | | | | (sicp.chapter1.2-2/cc 7 0)
;; TRACE t11337: | | | | | | | | | => 0
;; TRACE t11338: | | | | | | | | | (sicp.chapter1.2-2/cc 6 1)
;; TRACE t11339: | | | | | | | | | | (sicp.chapter1.2-2/cc 6 0)
;; TRACE t11339: | | | | | | | | | | => 0
;; TRACE t11340: | | | | | | | | | | (sicp.chapter1.2-2/cc 5 1)
;; TRACE t11341: | | | | | | | | | | | (sicp.chapter1.2-2/cc 5 0)
;; TRACE t11341: | | | | | | | | | | | => 0
;; TRACE t11342: | | | | | | | | | | | (sicp.chapter1.2-2/cc 4 1)
;; TRACE t11343: | | | | | | | | | | | | (sicp.chapter1.2-2/cc 4 0)
;; TRACE t11343: | | | | | | | | | | | | => 0
;; TRACE t11344: | | | | | | | | | | | | (sicp.chapter1.2-2/cc 3 1)
;; TRACE t11345: | | | | | | | | | | | | | (sicp.chapter1.2-2/cc 3 0)
;; TRACE t11345: | | | | | | | | | | | | | => 0
;; TRACE t11346: | | | | | | | | | | | | | (sicp.chapter1.2-2/cc 2 1)
;; TRACE t11347: | | | | | | | | | | | | | | (sicp.chapter1.2-2/cc 2 0)
;; TRACE t11347: | | | | | | | | | | | | | | => 0
;; TRACE t11348: | | | | | | | | | | | | | | (sicp.chapter1.2-2/cc 1 1)
;; TRACE t11349: | | | | | | | | | | | | | | | (sicp.chapter1.2-2/cc 1 0)
;; TRACE t11349: | | | | | | | | | | | | | | | => 0
;; TRACE t11350: | | | | | | | | | | | | | | | (sicp.chapter1.2-2/cc 0 1)
;; TRACE t11350: | | | | | | | | | | | | | | | => 1
;; TRACE t11348: | | | | | | | | | | | | | | => 1
;; TRACE t11346: | | | | | | | | | | | | | => 1
;; TRACE t11344: | | | | | | | | | | | | => 1
;; TRACE t11342: | | | | | | | | | | | => 1
;; TRACE t11340: | | | | | | | | | | => 1
;; TRACE t11338: | | | | | | | | | => 1
;; TRACE t11336: | | | | | | | | => 1
;; TRACE t11334: | | | | | | | => 1
;; TRACE t11332: | | | | | | => 1
;; TRACE t11330: | | | | | => 1
;; TRACE t11328: | | | | => 1
;; TRACE t11351: | | | | (sicp.chapter1.2-2/cc 6 2)
;; TRACE t11352: | | | | | (sicp.chapter1.2-2/cc 6 1)
;; TRACE t11353: | | | | | | (sicp.chapter1.2-2/cc 6 0)
;; TRACE t11353: | | | | | | => 0
;; TRACE t11354: | | | | | | (sicp.chapter1.2-2/cc 5 1)
;; TRACE t11355: | | | | | | | (sicp.chapter1.2-2/cc 5 0)
;; TRACE t11355: | | | | | | | => 0
;; TRACE t11356: | | | | | | | (sicp.chapter1.2-2/cc 4 1)
;; TRACE t11357: | | | | | | | | (sicp.chapter1.2-2/cc 4 0)
;; TRACE t11357: | | | | | | | | => 0
;; TRACE t11358: | | | | | | | | (sicp.chapter1.2-2/cc 3 1)
;; TRACE t11359: | | | | | | | | | (sicp.chapter1.2-2/cc 3 0)
;; TRACE t11359: | | | | | | | | | => 0
;; TRACE t11360: | | | | | | | | | (sicp.chapter1.2-2/cc 2 1)
;; TRACE t11361: | | | | | | | | | | (sicp.chapter1.2-2/cc 2 0)
;; TRACE t11361: | | | | | | | | | | => 0
;; TRACE t11362: | | | | | | | | | | (sicp.chapter1.2-2/cc 1 1)
;; TRACE t11363: | | | | | | | | | | | (sicp.chapter1.2-2/cc 1 0)
;; TRACE t11363: | | | | | | | | | | | => 0
;; TRACE t11364: | | | | | | | | | | | (sicp.chapter1.2-2/cc 0 1)
;; TRACE t11364: | | | | | | | | | | | => 1
;; TRACE t11362: | | | | | | | | | | => 1
;; TRACE t11360: | | | | | | | | | => 1
;; TRACE t11358: | | | | | | | | => 1
;; TRACE t11356: | | | | | | | => 1
;; TRACE t11354: | | | | | | => 1
;; TRACE t11352: | | | | | => 1
;; TRACE t11365: | | | | | (sicp.chapter1.2-2/cc 1 2)
;; TRACE t11366: | | | | | | (sicp.chapter1.2-2/cc 1 1)
;; TRACE t11367: | | | | | | | (sicp.chapter1.2-2/cc 1 0)
;; TRACE t11367: | | | | | | | => 0
;; TRACE t11368: | | | | | | | (sicp.chapter1.2-2/cc 0 1)
;; TRACE t11368: | | | | | | | => 1
;; TRACE t11366: | | | | | | => 1
;; TRACE t11369: | | | | | | (sicp.chapter1.2-2/cc -4 2)
;; TRACE t11369: | | | | | | => 0
;; TRACE t11365: | | | | | => 1
;; TRACE t11351: | | | | => 2
;; TRACE t11327: | | | => 3
;; TRACE t11370: | | | (sicp.chapter1.2-2/cc 1 3)
;; TRACE t11371: | | | | (sicp.chapter1.2-2/cc 1 2)
;; TRACE t11372: | | | | | (sicp.chapter1.2-2/cc 1 1)
;; TRACE t11373: | | | | | | (sicp.chapter1.2-2/cc 1 0)
;; TRACE t11373: | | | | | | => 0
;; TRACE t11374: | | | | | | (sicp.chapter1.2-2/cc 0 1)
;; TRACE t11374: | | | | | | => 1
;; TRACE t11372: | | | | | => 1
;; TRACE t11375: | | | | | (sicp.chapter1.2-2/cc -4 2)
;; TRACE t11375: | | | | | => 0
;; TRACE t11371: | | | | => 1
;; TRACE t11376: | | | | (sicp.chapter1.2-2/cc -9 3)
;; TRACE t11376: | | | | => 0
;; TRACE t11370: | | | => 1
;; TRACE t11326: | | => 4
;; TRACE t11377: | | (sicp.chapter1.2-2/cc -14 4)
;; TRACE t11377: | | => 0
;; TRACE t11325: | => 4
;; TRACE t11378: | (sicp.chapter1.2-2/cc -39 5)
;; TRACE t11378: | => 0
;; TRACE t11324: => 4
;; 4
;;
;; the order of growth in space: Θ(n)
;; the order of growth in number of steps: Θ(n^5)

;; Exercise 1.15
(defn cube [x] (* x x x))
(defn p [x] (- (* 3 x) (* 4 (cube x))))
(defn sine [angle]
  (if (not (> (abs angle) 0.1))
    angle
    (p (sine (/ angle 3.0)))))

(sine 12.15)

;; tracing `p` with cider-toggle-trace-var
;; sicp.chapter1.2-3> (sine 12.15)
;; TRACE t16132: (sicp.chapter1.2-3/p 0.049999999999999996)
;; TRACE t16132: => 0.1495
;; TRACE t16133: (sicp.chapter1.2-3/p 0.1495)
;; TRACE t16133: => 0.4351345505
;; TRACE t16134: (sicp.chapter1.2-3/p 0.4351345505)
;; TRACE t16134: => 0.9758465331678772
;; TRACE t16135: (sicp.chapter1.2-3/p 0.9758465331678772)
;; TRACE t16135: => -0.7895631144708228
;; TRACE t16136: (sicp.chapter1.2-3/p -0.7895631144708228)
;; TRACE t16136: => -0.39980345741334
;; -0.39980345741334
;;
;; a.
;;   5 times
;;
;; tracing `sine` with cider-toggle-trace-var
;; sicp.chapter1.2-3> (sine 12.15)
;; TRACE t16102: (sicp.chapter1.2-3/sine 12.15)
;; TRACE t16103: | (sicp.chapter1.2-3/sine 4.05)
;; TRACE t16104: | | (sicp.chapter1.2-3/sine 1.3499999999999999)
;; TRACE t16105: | | | (sicp.chapter1.2-3/sine 0.44999999999999996)
;; TRACE t16106: | | | | (sicp.chapter1.2-3/sine 0.15)
;; TRACE t16107: | | | | | (sicp.chapter1.2-3/sine 0.049999999999999996)
;; TRACE t16107: | | | | | => 0.049999999999999996
;; TRACE t16106: | | | | => 0.1495
;; TRACE t16105: | | | => 0.4351345505
;; TRACE t16104: | | => 0.9758465331678772
;; TRACE t16103: | => -0.7895631144708228
;; TRACE t16102: => -0.39980345741334
;; -0.39980345741334
;; sicp.chapter1.2-3> (sine 121.5)
;; TRACE t16110: (sicp.chapter1.2-3/sine 121.5)
;; TRACE t16111: | (sicp.chapter1.2-3/sine 40.5)
;; TRACE t16112: | | (sicp.chapter1.2-3/sine 13.5)
;; TRACE t16113: | | | (sicp.chapter1.2-3/sine 4.5)
;; TRACE t16114: | | | | (sicp.chapter1.2-3/sine 1.5)
;; TRACE t16115: | | | | | (sicp.chapter1.2-3/sine 0.5)
;; TRACE t16116: | | | | | | (sicp.chapter1.2-3/sine 0.16666666666666666)
;; TRACE t16117: | | | | | | | (sicp.chapter1.2-3/sine 0.05555555555555555)
;; TRACE t16117: | | | | | | | => 0.05555555555555555
;; TRACE t16116: | | | | | | => 0.16598079561042522
;; TRACE t16115: | | | | | => 0.4796515524505468
;; TRACE t16114: | | | | => 0.997549345951195
;; TRACE t16113: | | | => -0.9780161231523348
;; TRACE t16112: | | => 0.8079021000407098
;; TRACE t16111: | => 0.3144167435037848
;; TRACE t16110: => 0.818919928903895
;; 0.818919928903895
;; sicp.chapter1.2-3> (sine 1215)
;; TRACE t16120: (sicp.chapter1.2-3/sine 1215)
;; TRACE t16121: | (sicp.chapter1.2-3/sine 405.0)
;; TRACE t16122: | | (sicp.chapter1.2-3/sine 135.0)
;; TRACE t16123: | | | (sicp.chapter1.2-3/sine 45.0)
;; TRACE t16124: | | | | (sicp.chapter1.2-3/sine 15.0)
;; TRACE t16125: | | | | | (sicp.chapter1.2-3/sine 5.0)
;; TRACE t16126: | | | | | | (sicp.chapter1.2-3/sine 1.6666666666666667)
;; TRACE t16127: | | | | | | | (sicp.chapter1.2-3/sine 0.5555555555555556)
;; TRACE t16128: | | | | | | | | (sicp.chapter1.2-3/sine 0.1851851851851852)
;; TRACE t16129: | | | | | | | | | (sicp.chapter1.2-3/sine 0.0617283950617284)
;; TRACE t16129: | | | | | | | | | => 0.0617283950617284
;; TRACE t16128: | | | | | | | | => 0.18424434697360575
;; TRACE t16127: | | | | | | | => 0.527715621699182
;; TRACE t16126: | | | | | | => 0.9953059062333508
;; TRACE t16125: | | | | | => -0.9580171565672959
;; TRACE t16124: | | | | => 0.6430091298398879
;; TRACE t16123: | | | => 0.8655912641984398
;; TRACE t16122: | | => 0.0026028791309826715
;; TRACE t16121: | => 0.007808566855134185
;; TRACE t16120: => 0.023423796096041047
;; 0.023423796096041047
;;
;; b.
;;   the order of growth in space: Θ(log3 n)
;;   the order of growth in number of steps: Θ(log3 n)
