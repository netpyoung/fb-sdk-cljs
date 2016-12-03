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
        (-> script (.-src)   (set! "//connect.facebook.net/en_US/sdk.js"))

        (let [fst-js (-> doc (.getElementsByTagName "script") (aget 0))
              parent (.-parentNode fst-js)]

          (.insertBefore parent script fst-js))))))

;;
;; Facebook APIs.

(defn init
  "ref: https://developers.facebook.com/docs/javascript/reference/FB.init"
  [params]
  (-> (clj->js params)
      (js/FB.init)))

(defn api
  "ref: https://developers.facebook.com/docs/javascript/reference/FB.api"
  ([path something]
   (js/FB.api path (if (fn? something) (wrap-keywordize-key something) (clj->js something))))
  ([path something resp-cb]
   (js/FB.api path (clj->js something) (wrap-keywordize-key resp-cb)))
  ([path method params resp-cb]
   (js/FB.api path method (clj->js params) (wrap-keywordize-key resp-cb))))


(defn ui
  "ref: https://developers.facebook.com/docs/javascript/reference/FB.ui"
  ([params]         (js/FB.ui (clj->js params)))
  ([params resp-cb] (js/FB.ui (clj->js params) (wrap-keywordize-key resp-cb))))


(defn get-login-status
  "ref: https://developers.facebook.com/docs/reference/javascript/FB.getLoginStatus"
  [resp-cb]
  (js/FB.getLoginStatus
   (wrap-keywordize-key resp-cb)))


(defn login
  "ref: https://developers.facebook.com/docs/reference/javascript/FB.login"
  ([resp-cb]
   (login resp-cb {}))
  ([resp-cb opts]
   (js/FB.login
    (wrap-keywordize-key resp-cb)
    (clj->js opts))))


(defn logout
  "ref: https://developers.facebook.com/docs/reference/javascript/FB.logout"
  [resp-cb]
  (js/FB.logout
   (wrap-keywordize-key resp-cb)))


(defn get-auth-response
  "ref: https://developers.facebook.com/docs/reference/javascript/FB.getAuthResponse"
  [resp-cb]
  (js/FB.getLoginStatus
   (wrap-keywordize-key resp-cb)))


(defn Event:subscribe
  "ref: https://developers.facebook.com/docs/reference/javascript/FB.Event.subscribe"
  [event cb]
  (. js/FB.Event (subscribe event cb)))


(defn Event:unsubscribe
  "ref: https://developers.facebook.com/docs/reference/javascript/FB.Event.unsubscribe"
  [event cb]
  (. js/FB.Event (unsubscribe event cb)))


(defn XFBML:parse
  "ref: https://developers.facebook.com/docs/reference/javascript/FB.XFBML.parse"
  ([]       (. js/FB.XFBML (parse)))
  ([dom]    (. js/FB.XFBML (parse dom)))
  ([dom cb] (. js/FB.XFBML (parse dom cb))))


(defn Canvas!Prefetcher:add-static-resource
  "ref: https://developers.facebook.com/docs/reference/javascript/FB.Canvas.Prefetcher.addStaticResource"
  [resource-path]
  (. js/FB.Canvas.Prefetcher (addStaticResource resource-path)))


(defn Canvas!Prefetcher:set-collection-mode
  "ref: https://developers.facebook.com/docs/reference/javascript/FB.Canvas.Prefetcher.setCollectionMode"
  [mode]
  (. js/FB.Canvas.Prefetcher (setCollectionMode mode)))


(defn Canvas:get-page-info
  "ref: https://developers.facebook.com/docs/reference/javascript/FB.Canvas.getPageInfo"
  [resp-cb]
  (. js/FB.Canvas (getPageInfo (wrap-keywordize-key resp-cb))))


(defn Canvas:set-done-loading
  "ref: https://developers.facebook.com/docs/reference/javascript/FB.Canvas.setDoneLoading"
  ([]        (. js/FB.Canvas (setDoneLoading)))
  ([resp-cb] (. js/FB.Canvas (setDoneLoading (wrap-keywordize-key resp-cb)))))


(defn Canvas:set-auto-grow
  "ref: https://developers.facebook.com/docs/reference/javascript/FB.Canvas.setAutoGrow"
  ([]                    (. js/FB.Canvas (setAutoGrow)))
  ([onOrOff-or-interval] (. js/FB.Canvas (setAutoGrow onOrOff-or-interval))))


(defn Canvas:set-size
  "ref: https://developers.facebook.com/docs/reference/javascript/FB.Canvas.setSize"
  ([]     (. js/FB.Canvas (setSize)))
  ([size] (. js/FB.Canvas (setSize (clj->js size)))))


(defn Canvas:set-url-handler
  "ref: https://developers.facebook.com/docs/reference/javascript/FB.Canvas.setUrlHandler"
  ([]        (. js/FB.Canvas (setUrlHandler)))
  ([resp-cb] (. js/FB.Canvas (setUrlHandler (wrap-keywordize-key resp-cb)))))


(defn Canvas:start-timer
  "ref: https://developers.facebook.com/docs/reference/javascript/FB.Canvas.startTimer"
  []
  (. js/FB.Canvas (startTimer)))


(defn Canvas:stop-timer
  "ref: https://developers.facebook.com/docs/reference/javascript/FB.Canvas.stopTimer"
  ([]        (. js/FB.Canvas (stopTimer)))
  ([resp-cb] (. js/FB.Canvas (stopTimer (wrap-keywordize-key resp-cb)))))
