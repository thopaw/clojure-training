(ns recursion.factorial)

(defn factorial-let [x]
      "calculates the factorial value of x"
      (let [
            int-func (fn [acc z]
                         (if (pos? z)
                           (recur (* acc z) (dec z))
                           acc))]


           (int-func 1 x)))


(defn factorial-anonymfn [x]
      "calculates the factorial value of x"
      ((fn [acc z]
           (if (pos? z)
             (recur (* acc z) (dec z))
             acc))

       1 x))


(defn factorial-loop [x]
      "calculates the factorial value of x"
      (loop [acc 1, z x]
            (if (pos? z)
              (recur (* acc z) (dec z))
              acc)))
