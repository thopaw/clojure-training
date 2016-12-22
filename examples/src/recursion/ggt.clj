(ns recursion.ggt)

(defn abs [x]
  "gives the absolute value of x"
  (if (pos? x)
    x
    (- x)))

(defn ggt [x y]
  "calculates the ggt (greatest common devisor) of x and y"
  (loop [xx (abs x), yy (abs y)]
    (if (zero? yy) xx
      (if (zero? xx) yy
        (recur yy (mod xx yy))))))
