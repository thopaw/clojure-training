(ns drawing.math-functions)

; 90 degrees:
(def PI_HALF (/ Math/PI 2))
; 60 degrees:
(def PI_THIRD (/ Math/PI 3))
; 45 degrees:
(def PI_QUARTER (/ Math/PI 4))
; 360 degrees:
(def DOUBLE_PI (* Math/PI 2))
; 120 degrees:
(def TWO_THIRDS_PI (* 2 PI_THIRD))
; 240 degrees:
(def FOUR_THIRDS_PI (* 4 PI_THIRD))

(defn sin [x] (Math/sin x))
(defn cos [x] (Math/cos x))
(defn tan [x] (Math/tan x))
(defn cot [x] (tan (- PI_HALF x)))
(defn sec [x] (/ (cos x)))
(defn csc [x] (/ (sin x)))

(defn sinh [x] (Math/sinh x))
(defn cosh [x] (Math/cosh x))
(defn tanh [x] (/ (Math/sinh x) (Math/cosh x)))
(defn coth [x] (/ (Math/cosh x) (Math/sinh x)))
(defn sech [x] (/ (Math/cosh x)))
(defn csch [x] (/ (Math/sinh x)))

(defn asin [x] (Math/asin x))
(defn acos [x] (Math/acos x))
(defn atan [x] (Math/atan x))
(defn acot [x] (- PI_HALF (Math/atan x)))
(defn asec [x] (Math/acos (/ x)))
(defn acsc [x] (Math/asin (/ x)))

; s = (zz+1)/(2z) -> zz -2sz + 1
; z = s +- (sqrt ss-1)
;

(defn asinh [x] (Math/log (+ s (Math/sqrt (+ (* s s) 1)))))
(defn acosh [x] (Math/log (+ s (Math/sqrt (- (* s s) 1)))))

;(defn asinh [x] (Math/asinh x))
;(defn acosh [x] (Math/acosh x))
;(defn atanh [x] (Math/atanh x))
;(defn acoth [x] (Math/atanh (/ x)))
;(defn asech [x] (Math/acosh (/ x)))
;(defn acsch [x] (Math/asinh (/ x)))

(defn abs [x] (Math/abs x))

(defn atan2 [x y] (if (and (zero? x) (zero? y)) 0
                      (let [xx (abs x)
                            yy (abs y)
                            qa (cond
                                 (zero? yy) 0
                                 (zero? xx) PI_HALF
                                 (== xx yy) PI_QUARTER
                                 (< xx yy) (atan (/ yy xx))
                                 :else (acot (/ xx yy)))]
                        (cond
                          (and (>= x 0) (>= y 0)) qa
                          (and (< x 0) (>= y 0)) (- Math/PI qa)
                          (and (< x 0) (< y 0)) (+ Math/PI qa)
                          :else (- DOUBLE_PI qa)))))

(defn hyp [x y] (Math/sqrt (+ (* x x) (* y y))))

(defn expi [[x y]]
  (let [r (Math/exp x)]
    [(* r (cos y)) (* r (sin y))]))
