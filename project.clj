(defproject lsnape/chord-transit "0.1.0"
  :description "Adds Transit support as a communication format for the Chord library."
  :url "https://github.com/lsnape/chord-transit"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [com.cognitect/transit-clj "0.8.229"]
                 [com.cognitect/transit-cljs "0.8.137"]
                 [jarohen/chord "0.4.2"]])
