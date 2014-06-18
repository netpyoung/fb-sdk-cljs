(ns repl
  (:require
   [weasel.repl :as ws-repl]))

(enable-console-print!)

(ws-repl/connect "ws://localhost:8889" :verbose true)
