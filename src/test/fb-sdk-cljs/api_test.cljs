(ns fb-sdk-cljs.api-test
  (:require
   [fb-sdk-cljs.core :as fb]))

(comment
  (fb/load-sdk #(println "loaded"))


  (fb/init {:appId "516615825130703"
            :status true
            :cookies true
            :xfbml true
            :version "v2.0"})


  (fb/get-login-status
   (fn [response]
     (case (:status response)
       "connected"
       (.log js/console "connected")

       ;; else
       (fb/login #(.log js/console "else-" %) {:scope "email"}))))

  ;; ui
  (fb/ui {:method "feed"
          :name "The Facebook SDK for Javascript"
          :caption "Bringing Facebook to the desktop and mobile web"
          :description
          (str
           "A small JavaScript library that allows you to harness "
           "the power of Facebook, bringing the user's identity "
           "social graph and distribution power to your site")
          :link "https://developers.facebook.com/docs/reference/javascript/"
          :picture "http://www.fbrell.com/public/f8.jpg"}

         (fn [response]
           (if (and response (:post_id response))
             (js/alert "Post was published.")
             (js/alert "Post was not published."))))

  (fb/api "/113124472034820"
          println)

  (fb/api "/me" {:fields "last_name"}
          println)

  (fb/api "/me/feed"
          "post"
          {:message "Reading JS SDK documentation"}
          println)

  (fb/logout println)

  (fb/get-auth-response println)
  )
