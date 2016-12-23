(ns gfx.core)


(defn in-range [x max]
  "checks if x is in range [0,max[ (max is exclusive)"
  (if (< x 0)
    0
    (if (>= x max)
      (- max 1)
      x)))

(defn draw-in-frame [paint-fn]
  "creates a frame and uses the method pain-fn to draw in the frame.
  The Frame is retuned."
  (let [frame (doto (javax.swing.JFrame.)
                (.setSize (java.awt.Dimension. 800 800))
                (.setVisible true)
                (.show))
        gfx (.getGraphics frame)]
      (do
        (Thread/sleep 200)
        (println "frame fertig?")
        (paint-fn gfx)
        frame)))

(def frame-rect (draw-in-frame
              (fn [gfx]
                (do
                   (.setColor gfx (java.awt.Color. 0xff 0xaa 0x55))
                   (.fillRect gfx 100 200 300 400)))))

(def frame-rect-gradient-horizontal (draw-in-frame
  (fn [gfx]
    ((fn [x]
      (if (pos? x)
       (do
        (.setColor gfx (java.awt.Color.
          (mod (* (+ x 0) 2) 255)
          (mod (* (+ x 50) 4) 255)
          (mod (* (+ x 100) 6) 255)))
        (.fillRect gfx 0 0 800 x)
        (recur (dec x)))))
      800))))


(def frame-rect-gradient-vertical (draw-in-frame
  (fn [gfx]
    ((fn [x]
      (if (pos? x)
       (do
        (.setColor gfx (java.awt.Color.
          (mod x 255)
          (mod (+ x 100) 200)
          (mod (+ x 200) 255)))
        (.fillRect gfx 0 0 x 800)
        (recur (dec x)))))
      800))))

(def frame-rect-gradient-diagonal (draw-in-frame
  (fn [gfx]
    ((fn [x]
      (if (pos? x)
       (do
        (.setColor gfx (java.awt.Color.
          (mod x 255)
          (mod (+ x 100) 255)
          (mod (+ x 200) 255)))
        (.fillRect gfx 0 0 x x)
        (recur (dec x)))))
      800))))


(def frame-rect-gradient-sinus (draw-in-frame
  (fn [gfx]
    ((fn [x]
      (if (pos? x)
       (do
        (.setColor gfx (java.awt.Color.
          (mod x 255)
          (mod (+ x 100) 255)
          (mod (+ x 200) 255)))
        (.fillOval gfx x (- 400 (* (Math/sin x) 400 ) ) 10 10)
        (recur (dec x)))))
      800))))


(defn draw-in-frame-with-functions [paint-fns]
  "creates a frame and uses the methods pain-fns to draw in the frame.
  The Frame is retuned."
  (let [frame (doto (javax.swing.JFrame.)
                (.setSize (java.awt.Dimension. 800 800))
                (.setVisible true)
                (.show))
        gfx (.getGraphics frame)]
      (Thread/sleep 200)
      ((fn [fns]
        (if (not-empty fns)
          (do
            ((first fns) gfx)
            (recur (rest fns)
        )))) paint-fns)
        frame))

; draws a simple circle
(draw-in-frame-with-functions [
  (fn [gfx] (.setColor gfx (java.awt.Color. 100 100 100)))
  (fn [gfx] (.fillOval gfx 200 200 100 100))
  ])


(defn set-color [gfx x]
  "sets color in the gfx"
  (.setColor gfx (java.awt.Color.
    (mod x 255)
    (mod (+ x 100) 255)
    (mod (+ x 200) 255))))

(defn rainbow-color [gfx x amplitude center]
  (let [
    calc (fn [shift] (+ (* (Math/sin (+ x shift)) amplitude) center))
    third-pi (/ (Math/PI) 3)
    r (calc (* third-pi 0))
    g (calc (* third-pi 2))
    b (calc (* third-pi 4))]
    (.setColor gfx (java.awt.Color. r g b))))

(draw-in-frame-with-functions [
  (fn [gfx] (
    (loop [x 0]
      (if (>= 800)
        (do
          (rainbow-color gfx x (/ 255 2) (/ 255 2))
          (.drawLine gfx x 0 x 800)
          (recur (inc x)))
      ))))
  ])
