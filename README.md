# Chord Transit

[Chord](https://github.com/james-henderson/chord) is a
Clojure/ClojureScript abstraction layer that presents WebSocket
connections as core.async channels.

[Transit](https://github.com/cognitect/transit-format) is a new data
interchange format from Cognitect, similar to EDN. It benefits heavily
from the performance of existing JSON message parsers, yet allows
extensibility in the form of custom types.

Chord Transit adds Transit support to the Chord library.

## Usage

Include the following in your *project.clj*:

```clojure
	[jarohen/chord "0.4.2"]
	[lsnape/chord-transit "0.1.0"]
```

### Clojure server

Initialise your message handler on the server:

```clojure
(ns example.handler
  (:require [chord-transit.format]
            [chord.http-kit :refer [wrap-websocket-handler]]
            [clojure.core.async :refer [<! >! put! close! go-loop]]))

(defn my-ws-handler [{:keys [ws-channel] :as req}]
  (go
   (when-let [{:keys [message error] :as msg} (<! ws-channel)]
     (prn "Message received from client:" msg)
     (close! ws-channel)))
```

And then use the `wrap-websocket-handler` as follows:

```clojure
(-> my-ws-handler
	(wrap-websocket-handler {:format :transit)
```

### ClojureScript client

```clojure
(:require [chord.client :refer [ws-ch]]
           chord-transit.format
           [cljs.core.async :refer [<! >! put! close!]])
 (:require-macros [cljs.core.async.macros :refer [go]])

 (go
   (let [{:keys [ws-channel]} (<! (ws-ch "ws://localhost:3000/ws" {:format :transit}))]
    (>! ws-channel "Hello server from client!")))
```

## Caveats

Reading and writing of custom types is currently not supported.

## A Big Thanks

* [Jeff Rose](https://github.com/rosejn), implementer of the [chord-fressian](https://github.com/thinktopic/chord-fressian) library, which I heavily referred to
* [James Henderson](https://github.com/james-henderson), for guidance whilst writing this library
