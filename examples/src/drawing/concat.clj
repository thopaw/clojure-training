(ns drawing.concat)

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

(defn asinh [x] (Math/log (+ x (Math/sqrt (+ (* x x) 1)))))
(defn acosh [x] (Math/log (+ x (Math/sqrt (- (* x x) 1)))))

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

(defn make-frame []
  (let [frame
	    (doto (javax.swing.JFrame.)
            (.setSize (java.awt.Dimension. 1000 1000))
            (.setVisible true))]
   frame))

(defn draw-on-frame [frame & draw-functions]
    (let [gfx (.getGraphics frame)]
       (loop [remaining-draw-functions draw-functions]
           (when remaining-draw-functions
                ((first remaining-draw-functions) gfx)
                (recur
                  (next remaining-draw-functions))))))

(defn draw-function-color-circle [fun divisor gfx]
      (let [safe-fun (fn [x y] (try (fun x y) (catch Exception e 0)))
            offset-g (/ divisor 3)
            offset-b (* 2 offset-g)
            field-fun (fn [max-x max-y] (for [x (range max-x) y (range max-y)] [x y (safe-fun x y)]))
            normalize (fn [val] (bit-and 0xff (int (* 127.5 (+ 1 (Math/sin (/ (* Math/PI val) divisor)))))))
      ]
            (doseq [[x y x-y] (field-fun 1000 1000)]
                   (let [r (normalize x-y)
                         g (normalize (+ x-y offset-g))
                         b (normalize (+ x-y offset-b))
                         color (java.awt.Color. r g b)]
                         (.setColor gfx color)
                         (.fillRect gfx x y 1 1)))))


(def frame (make-frame))

 ;(defn fun [x y] (* (sin x) (cos y) (tan (* x y))))

 ;(def drawing-fun (partial draw-function-color-circle fun 100))

 ;(draw-on-frame frame drawing-fun)

 ;(def drawing-fun (partial draw-function-color-circle fun 0.01))

 ;(draw-on-frame frame drawing-fun)

 ;(def drawing-fun (partial draw-function-color-circle fun 0.1))

 ;(draw-on-frame frame drawing-fun)

 ;(defn fun [x y] (* (sin (/ x 100.0)) (cos (/ y 100.0)) (tan (/ (* x y) 300.0))))

 ;(def drawing-fun (partial draw-function-color-circle fun 0.1))

 ;(draw-on-frame frame drawing-fun)

(draw-on-frame frame
  (fn [gfx]
      (do
        (.setColor gfx (java.awt.Color. 50 0 0))
        (.fillRect gfx 0 0 (.getWidth frame) (.getHeight frame))))
  (fn [gfx]
    (for [x (range (.getWidth frame))
          y (range (.getHeight frame))]
        (do
          (.setColor gfx (java.awt.Color. (mod x 255) (mod x 255) (mod x 255) ))
          (.fillRect gfx x y 5 5))
    ))
)
