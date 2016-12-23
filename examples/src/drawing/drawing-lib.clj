(load-file "math-functions.clj")

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

