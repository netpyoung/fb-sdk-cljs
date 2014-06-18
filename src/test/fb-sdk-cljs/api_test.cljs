(ns fb-sdk-cljs.api-test
  (:require
   [fb-sdk-cljs.core :as fb]))


(defn ^:export ttt []
  (sdk/load-sdk (fn []
                  (println "loaded")))

  (sdk/fb-async-init)
  (sdk/ui 1 1)
  (sdk/get-login-status 1)
  (sdk/login 1))


(defn fb-async-init []
  (fb/init {:appId "516615825130703"
            :status true
            :cookies true
            :xfbml true
            })
  (fb/get-login-status
   (fn [response]
     (case (:status response)
       "connected"
       (.log js/console "connected")

       "not_authorized"
       (fb/login (fn [x]
                   (.log js/console "not-" x)) {:scope "email"})

       (fb/login (fn [x]
                (.log js/console "else-" x)) {:scope "email"})
       )))
  )
