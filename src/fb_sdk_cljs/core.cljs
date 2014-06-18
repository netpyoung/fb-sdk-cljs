(ns fb-sdk-cljs.core)


;; ref: https://developers.facebook.com/docs/javascript/


(defn load-sdk [fb-async-init-cb]

  (let [doc js/document uid "fb-sdk-cljs"]
    (when-not (. doc (getElementById uid))

      ;; (-> js/window (.-fbAsyncInit) (set! fb-async-init-cb))

      (let [script (. doc (createElement "script"))]
        (-> script (.-id)    (set! uid))
        (-> script (.-async) (set! true))
        (-> script (.-src)   (set! "//connect.facebook.net/en_US/all.js"))

        (let [fst-js (-> doc (.getElementsByTagName "script") (aget 0))
              parent (.-parentNode fst-js)]

          (.insertBefore parent script fst-js))))))


;; Facebook APIs.

;; https://developers.facebook.com/docs/javascript/reference/FB.init/v2.0
(defn init [params]
  (-> (clj->js params)
      (js/FB.init)))


;; https://developers.facebook.com/docs/reference/javascript/FB.getLoginStatus
(defn get-login-status [resp-fn]
  (js/FB.getLoginStatus
   (fn [resp] (resp-fn (js->clj resp :keywordize-keys true)))))


;; https://developers.facebook.com/docs/reference/javascript/FB.login/v2.0
(defn login
  ([resp-cb]
     (login resp-cb {}))
  ([resp-cb opts]
     (js/FB.login
      (fn [resp] (resp-cb (js->clj resp :keywordize-keys true)))
      (clj->js opts))))


(defn fb-async-init []
  (init {:appId "516615825130703"
         :status true
         :cookies true
         :xfbml true
         })
  (get-login-status
   (fn [response]
     (case (:status response)
       "connected"
       (println "connected")

       "not_authorized"
       (login (fn [x] (println "not-" x)) {:scope "email"})

       (login (fn [x] (def FF x)
                (println "else-" x)) {:scope "email"})
       )))
  )


(load-sdk fb-async-init)

(fb-async-init)
