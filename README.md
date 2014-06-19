# facebook sdk for cljs
* facebook javascript sdk wrapper for clojurescript

* [![Clojars Project](http://clojars.org/fb-sdk-cljs/latest-version.svg)](http://clojars.org/fb-sdk-cljs)

* [![Dependencies Status](http://jarkeeper.com/netpyoung/fb-sdk-cljs/status.png)](http://jarkeeper.com/netpyoung/fb-sdk-cljs)



## feature.
* just [facebook](https://developers.facebook.com/docs/javascript/) sdk.js wrapper.

* all response callback data converted by `(js->clj response :keywordize-keys true)`

* all input dictionary data converted by `(clj->js dicionary)`


```clojure
(fb/get-login-status
 (fn [response]
   (case (:status response)
     "connected"
     (.log js/console "connected")
     ;; else
     (fb/login #(.log js/console "else-" %) {:scope "email"}))))
```


## externs.

* http://www.dotnetwise.com/Code/Externs/

## Progress

* Core Method
  - [x] init()
  - [x] api()
  - [x] ui()

* Facebook Login Methods
  - [x] getLoginStatus() => `get-login-status`
  - [x] login()
  - [x] logout()
  - [x] getAuthResponse() => `get-auth-response`

* Event Handling Methods
  - [x] .Event.subscribe() => `Event:subscribe`
  - [x] .Event.unsubscribe() => `Event:unsubscribe`

* XFBML Methods
  - [x] .XFBML.parse => `XFBML:parse`

* Canvas Methods - currently not supported. maybe forever ...
  - [ ] .Canvas.Prefetcher.addStaticResource()
  - [ ] .Canvas.Prefetcher.setCollectionMode()
  - [ ] .Canvas.hideFlashElement()
  - [ ] .Canvas.showFlashElement()
  - [ ] .Canvas.scrollTo()
  - [ ] .Canvas.setAutoGrow()
  - [ ] .Canvas.setSize()
  - [ ] .Canvas.setUrlHandler()
  - [ ] .Canvas.setDoneLoading()
  - [ ] .Canvas.startTimer()
  - [ ] .Canvas.stopTimer()


# TODO
* remove test helper dependencies.
