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
; can be used for API parameters
(def dek-map {:first "Homer", :middle "J", :last "Simpson"})
(let [ {first :first, middle :middle, last :last} dek-map]
  (println "first: " first)
  (println "middle: " middle)
  (println "last: " last))
(let [ {:keys [first, middle, last]} dek-map]
  (println "first: " first)
  (println "middle: " middle)
  (println "last: " last))

; de-construction of array
(def dek-arr ["Homer" "J" "Simpson"])
(let [{first 0, middle 1, last 2} dek-arr]
  (println "first: " first)
  (println "middle: " middle)
  (println "last: " last))


; count elements
(count (list 1 2 3 4))
(count {1 1, 2 2, 3 3})
(count #{ 1 2 3 4 })
(count "12345")

; access collection elements
([1 2 3 4] 3)
({1 1, 2 2, 3 3} 2)
(#{1 2 3 4} 2)

; use map as function
(def args {1 1, 2 2, 3 3, 4 4})
(fn [x] (x 2) args)

; combine lists
(cons 999 '(2 3 4))
(cons 999 #{2 3 4})
; cons of map :-|
(cons 999 {1 1, 2 2, 3 3})

; combine with conj - preserve collection type - and insert depends on type
(conj '(2 3 4 5) 1) ; list to head
(conj [ 2 3 4] 1) ; vector at tail
(conj [ 2 3 4] 1 5 6 7) ; several items
(conj { 1 1, 2 2, 3 3} [7 7]) ; map random position
(conj #{ 1 2 3 4} 8 9) ; set random position


; lazy collections
(range 10000) ; list from o to 10000-1
(seq (range 10000)) ; EAGER - all is evaluated

; iterate over lists
(first (list 1 2 3 4))
(next (list 1 2 3 4))
(next (list)) ; evaluates to nil
(rest (list 1 2 3 4))
(rest (list)) ; evaluates to () empty list

(seq [1 2 3 4]) ; creates a list
(nth (list 1 2 3 4) 3)
(reverse (list 1 2 3 4))

; use list and vector as list
; peek pop conj
(peek (list 1 2 3 4))
(pop (list 1 2 3 4))
(conj (list 1 2 3 4) 0)
(peek [1 2 3 4])
(pop [1 2 3 4])
(conj [1 2 3 4] 0)

; convert collections
(into #{} (range 100))
(into [] (range 100))

; kinds of maps
(hash-map 1 1, 2 2, 3 3)
(sorted-map 1 1, 2 2, 3 3)
(array-map 1 1, 2 2, 3 3)

; sets
(hash-set 1 2 3 4)
(sorted-set 1 2 3 4)
