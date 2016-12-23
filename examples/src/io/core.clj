(ns io.core (:require [clojure.java.io :as cio]))

; reads file in string
(slurp "README.md")
;(slurp "doc/intro.md")

(with-open
  [xin (cio/reader "project.clj")]
  (loop []
    (let [x (.read xin)]
    (if (pos? x)
      (do
        (print (char x))
        (recur)
      )))))
