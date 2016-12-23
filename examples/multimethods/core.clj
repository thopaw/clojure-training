(ns multimethods.core)

; takes function and applies it to first parameter
(defmulti create-plant :area)
(defmethod create-plant ::desert [m n] (repeat "cactus"))
(create-plant ::desert 5)
