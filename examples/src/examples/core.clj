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

; collect rest during de-construction
(let [
  my-map {:first "Homer", :last "Simpson", :a "a", :b "b"}
  {first :first, last :last, :as rest } my-map]
  (println "first : " first)
  (println "last : " last)
  (println "rest : " rest)
  nil)

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

; mutable collections
(transient [])
(transient {})
(transient #{})

(let [ v (transient [1 2 3])
       w (loop [vl v, i 0]
         (if (< i 10)
          (recur (conj! vl (* i i)) (inc i))
          vl))]
       (persistent! w))

; map
(map (fn [x] (*' x x x)) (list 1 2 3 4 5))
(map (fn [x] (*' x x x)) [1 2 3 4 5])
(map (fn [x] (*' x x x)) #{1 2 3 4 5})
(take 10 (map (fn [x] (*' x x x)) (range 1000))) ; respects lazynes

; reduce
(reduce + 0 (range 10))
(reduce + 0 ())
(reduce str "" (list 1 2 3 4))
(reduce (fn [x y] (str x "-" y)) "" (range 100))
; without start reduce uses the 1st two elements
(reduce + (range 10))

; filter
(filter odd? (range 20))
(filter even? (range 20))

; maps and uses vector
(mapv inc (range 10))

; maps in parallel
(pmap dec (range 100))

; flatmap
(map (fn [x] (list 1 x (* x x))) (range 5))
(mapcat (fn [x] (list 1 x (* x x))) (range 5))

; group-by
(group-by (fn [x] (mod x 3)) (range 12))

; apply
(apply min [3 4 5 6 7])

; fak with apply
(defn fact [n]
  (apply *' (range 1 (inc n))))
(fact 5)

; do for sequence aka forEach
(doseq [x (range 100)] (println x))
