(ns chord-transit.format
  (:require [chord.format :refer [wrap-format try-read]]
            [clojure.core.async :as a]
            [cognitect.transit :as t])
  (:import [java.io ByteArrayInputStream ByteArrayOutputStream]))

(defmethod chord.format/wrap-format :transit
  [{:keys [read-ch write-ch]} _]
  
  (let [reader (t/reader :json)
        writer (t/writer :json)]
    
    {:read-ch (a/map< #(t/read reader %) read-ch)
     :write-ch (a/map< #(t/write writer %) write-ch)}))
