(ns examples.core)

; Create Java Objects and initialize them
(doto (new java.util.HashMap)
    (.put "HOME" "Test")
    (.put "Hallo" "Welt"))


; Hanlde try catch finally
(defn t-c-f [f]
  "handles try catch finally"
  (try
    (f)
    "finished f successfull"
    (catch Exception e (str "error: " e))
    (finally (println "returning"))))

; Calls of t-c-f
(t-c-f (fn [] (println "hello")))
(t-c-f (fn [] (throw  (Exception. "buh"))))


(list 1 2 3 4)

(+ 1 2 3 4)

(eval (list + 1 2 3 4))

(defn print-seq [s]
  (if (seq s)
    (do
      (prn (first s))
      (recur (rest s))
    )
  )
)

(print-seq [1 2 3 4])

(defn print-name [dek-full-name]
  (let [ [f m l] dek-full-name ]
  (println "first: " f)
  (println "middle: " m)
  (println "last: " l)))

(print-name ["a" "b" "c"])

(print-name (list "a" "b" "c"))
