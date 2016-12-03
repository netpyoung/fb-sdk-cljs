# facebook sdk for cljs
* facebook javascript sdk wrapper for clojurescript

* [![Clojars Project](http://clojars.org/netpyoung/fb-sdk-cljs/latest-version.svg)](http://clojars.org/netpyoung/fb-sdk-cljs)

* [![Dependencies Status](http://jarkeeper.com/netpyoung/fb-sdk-cljs/status.png)](http://jarkeeper.com/netpyoung/fb-sdk-cljs)



## Feature.
* just [facebook](https://developers.facebook.com/docs/javascript/) sdk.js wrapper.

* all response callback data converted by `(js->clj response :keywordize-keys true)`

* all input dictionary data converted by `(clj->js dicionary)`


## Usage
```clojure
(fb/get-login-status
 (fn [response]
   (case (:status response)
     "connected"
     (.log js/console "connected")
     ;; else
     (fb/login #(.log js/console "else-" %) {:scope "email"}))))
```

## example.
please check this file - https://github.com/netpyoung/fb-sdk-cljs/blob/master/test/fb_sdk_cljs/api_test.cljs


## Debuging this project

* `boot dev` in command line.
* connect `https://localhost:3443/`
* clojure repl connect
* connect cljs repl (https://github.com/adzerk-oss/boot-cljs-repl)

``` clojure
boot.user=> (start-repl)
```


## Externs.
* https://github.com/cljsjs/packages/tree/master/facebook
* http://www.dotnetwise.com/Code/Externs/

## Api

* Core Method
  - [init()](FB.init)
  - [api()](FB.api)
  - [ui()](FB.ui)

* Facebook Login Methods
  - get-login-status - [FB.getLoginStatus]
  - [login()](FB.login)
  - [logout()](FB.logout)
  - get-auth-response - [FB.getAuthResponse]

* Event Handling Methods
  - Event:subscribe - [FB.Event.subscribe]
  - Event:unsubscribe - [FB.Event.unsubscribe]

* XFBML Methods
  - XFBML:parse - [FB.XFBML.parse]

* Canvas Methods
  - Canvas!Prefetcher:add-static-resource - [FB.Canvas.Prefetcher.addStaticResource]
  - Canvas!Prefetcher:set-collection-mode - [FB.Canvas.Prefetcher.setCollectionMode]
  - Canvas:hide-flash-element - [FB.Canvas.hideFlashElement]
  - Canvas:show-flash-element - [FB.Canvas.showFlashElement]
  - Canvas:scroll-to - [FB.Canvas.scrollTo]
  - Canvas:get-page-info - [FB.Canvas.getPageInfo]
  - Canvas:set-done-loading - [FB.Canvas.setDoneLoading]
  - Canvas:set-auto-grow - [FB.Canvas.setAutoGrow]
  - Canvas:set-size - [FB.Canvas.setSize]
  - Canvas:set-url-handler - [FB.Canvas.setUrlHandler]
  - Canvas:start-timer - [FB.Canvas.startTimer]
  - Canvas:stop-timer - [FB.Canvas.stopTimer]


# TODO
* [ ] docker
* [ ] headless browser test

# REF.
* https://www.linode.com/docs/security/ssl/how-to-provide-encrypted-access-to-resources-using-ssl-certificated-on-nginx


[FB.init]: https://developers.facebook.com/docs/javascript/reference/FB.init
[FB.api]: https://developers.facebook.com/docs/javascript/reference/FB.api
[FB.ui]: https://developers.facebook.com/docs/javascript/reference/FB.ui
[FB.getLoginStatus]: https://developers.facebook.com/docs/reference/javascript/FB.getLoginStatus
[FB.login]: https://developers.facebook.com/docs/reference/javascript/FB.login
[FB.logout]: https://developers.facebook.com/docs/reference/javascript/FB.logout
[FB.getAuthResponse]: https://developers.facebook.com/docs/reference/javascript/FB.getAuthResponse
[FB.Event.subscribe]: https://developers.facebook.com/docs/reference/javascript/FB.Event.subscribe
[FB.Event.unsubscribe]: https://developers.facebook.com/docs/reference/javascript/FB.Event.unsubscribe
[FB.XFBML.parse]: https://developers.facebook.com/docs/reference/javascript/FB.XFBML.parse
[FB.Canvas.Prefetcher.addStaticResource]: https://developers.facebook.com/docs/reference/javascript/FB.Canvas.Prefetcher.addStaticResource
[FB.Canvas.Prefetcher.setCollectionMode]: https://developers.facebook.com/docs/reference/javascript/FB.Canvas.Prefetcher.setCollectionMode
[FB.Canvas.getPageInfo]: https://developers.facebook.com/docs/reference/javascript/FB.Canvas.getPageInfo
[FB.Canvas.setDoneLoading]: https://developers.facebook.com/docs/reference/javascript/FB.Canvas.setDoneLoading
[FB.Canvas.setAutoGrow]: https://developers.facebook.com/docs/reference/javascript/FB.Canvas.setAutoGrow
[FB.Canvas.setSize]: https://developers.facebook.com/docs/reference/javascript/FB.Canvas.setSize
[FB.Canvas.setUrlHandler]: https://developers.facebook.com/docs/reference/javascript/FB.Canvas.setUrlHandler
[FB.Canvas.startTimer]: https://developers.facebook.com/docs/reference/javascript/FB.Canvas.startTimer
[FB.Canvas.stopTimer]: https://developers.facebook.com/docs/reference/javascript/FB.Canvas.stopTimer
