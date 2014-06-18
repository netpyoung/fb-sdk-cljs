(ns fb-sdk-cljs.core)


;; ref: https://developers.facebook.com/docs/javascript/


;; Privates.
(defn- wrap-keywordize-key [func]
  (fn [resp]
    (-> resp
        (js->clj :keywordize-keys true)
        (func))))


;; Publics.
(defn load-sdk [fb-async-init-cb]

  (let [doc js/document uid "fb-sdk-cljs"]

    (when-not (. doc (getElementById uid))

      ;; register onload callback.
      (-> js/window (.-fbAsyncInit) (set! fb-async-init-cb))

      ;; attatch facebook-sdk.
      (let [script (. doc (createElement "script"))]
        (-> script (.-id)    (set! uid))
        (-> script (.-async) (set! true))
        (-> script (.-src)   (set! "//connect.facebook.net/en_US/all.js"))

        (let [fst-js (-> doc (.getElementsByTagName "script") (aget 0))
              parent (.-parentNode fst-js)]

          (.insertBefore parent script fst-js))))))

;;
;; Facebook APIs.

;; https://developers.facebook.com/docs/javascript/reference/FB.init/v2.0
(defn init [params]
  (-> (clj->js params)
      (js/FB.init)))


;; https://developers.facebook.com/docs/javascript/reference/FB.ui
(defn ui [params resp-cb]
  (js/FB.ui
   (clj->js params)
   (wrap-keywordize-key resp-cb)))


;; https://developers.facebook.com/docs/reference/javascript/FB.getLoginStatus
(defn get-login-status [resp-cb]
  (js/FB.getLoginStatus
   (wrap-keywordize-key resp-cb)))


;; https://developers.facebook.com/docs/reference/javascript/FB.login/v2.0
(defn login
  ([resp-cb]
     (login resp-cb {}))
  ([resp-cb opts]
     (js/FB.login
      (wrap-keywordize-key resp-cb)
      (clj->js opts))))
