(ns sicp.chapter1.2-3)

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
;; TODO
