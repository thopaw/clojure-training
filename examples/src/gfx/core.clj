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
