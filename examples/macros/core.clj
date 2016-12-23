(ns macros.core)

; define macros with defmacro
(defmacro infix [x]
  (list (second x) (first x) (last x)))

(infix (3 + 4)) ; switches argument positions

; macro with de-constriction pattern
(defmacro infix-2 [ [op1 op2 op3] ]
  (list op2 op1 op3))

(infix-2 (3 + 4))

; shows to what compiler expands macro
(macroexpand '(when true 3 4))
(macroexpand '(infix-2 (3 + 4)))
