(ns repl
  (:require
   [clojure.java.io :as io]

   [cemerick.piggieback]
   [weasel.repl.websocket]

   [ring.adapter.jetty]
   [compojure.core :refer [GET POST defroutes]]
   [compojure.route :as route]
   [compojure.handler :as handler]))


(defroutes router
  (GET "/" [] (io/resource "public/index.html"))
  (POST "/" [] (io/resource "public/index.html"))

  (route/resources "/")
  (route/not-found "Page not found"))


(def app
  (-> (handler/site router)))


(defn run! []
  (defonce ^:private server
    (->> {:port 8888
          :join? false
          :ssl? true
          :stacktraces? true
          :auto-reload? true
          :ssl-port 443
          :keystore "fb-sdk-cljs.keystore"
          :key-password "qwe123"
          :want-client-auth true
          :need-client-auth true
          }
         (ring.adapter.jetty/run-jetty #'app)))
  server)


(defn cljs! []
  (cemerick.piggieback/cljs-repl
   :repl-env (weasel.repl.websocket/repl-env
              :ip "0.0.0.0" :port 8889)))
