(ns drawing.drawing-example)

;(load-file "drawing-lib.clj")

(def frame (make-frame))

;(defn fun [x y] (* (sin x) (cos y) (tan (* x y))))

;(def drawing-fun (partial draw-function-color-circle fun 100))

;(draw-on-frame frame drawing-fun)

;(def drawing-fun (partial draw-function-color-circle fun 0.01))

; (draw-on-frame frame drawing-fun)

;(def drawing-fun (partial draw-function-color-circle fun 0.1))

;(draw-on-frame frame drawing-fun)

(defn fun [x y] (* (sin (/ x 100.0)) (cos (/ y 100.0)) (tan (/ (* x y) 300.0))))

(def drawing-fun (partial draw-function-color-circle fun 0.1))

(draw-on-frame frame drawing-fun)
