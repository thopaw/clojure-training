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

; creates a new list
(list 1 2 3 4)

; creates a list and evals it
(+ 1 2 3 4)
(eval (list + 1 2 3 4))

; iterates over a sequence
(defn print-seq [s]
  (if (seq s)
    (do
      (prn (first s))
      (recur (rest s))
    )
  )
)

(print-seq [1 2 3 4])
(print-seq (list 1 2 3 4))


; de-construct a sequence
(defn print-name-let [dek-full-name]
  (let [ [f m l] dek-full-name ]
  (println "first: " f)
  (println "middle: " m)
  (println "last: " l)))

(defn print-name [ [f m l] ]
  (println "first: " f)
  (println "middle: " m)
  (println "last: " l))

(print-name ["a" "b" "c"])
(print-name (list "a" "b" "c"))

; de-constructs with head and tail
(let [ [x & r] '(1 2 3 4)]  {:x x, :r r})

; de-constructs a map
(def dek-map {:first "Donald", :middle "J", :last "Simpson"})
(let [ {first :first, middle :middle, last :last} dek-map]
  (println "first: " first)
  (println "middle: " middle)
  (println "last: " last))
