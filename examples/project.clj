(defproject examples "0.1.0-SNAPSHOT"
            :description "Examples of the training"
            :dependencies [[org.clojure/clojure "1.8.0"]]
            :main ^:skip-aot recursion.core
            :target-path "target/%s"
            :profiles {:uberjar {:aot :all}})
