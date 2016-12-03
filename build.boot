(set-env!
 :source-paths #{"src"}
 :dependencies
 '[
   ;; for test
   [adzerk/boot-cljs          "1.7.228-2"  :scope "test"]
   [adzerk/boot-cljs-repl     "0.3.3"      :scope "test"]
   [adzerk/boot-reload        "0.4.13"     :scope "test"]
   [pandeiro/boot-http        "0.7.6"      :scope "test"]

   [com.cemerick/piggieback "0.2.1"  :scope "test"]
   [weasel                  "0.7.0"  :scope "test"]
   [org.clojure/tools.nrepl "0.2.12" :scope "test"]

   [adzerk/bootlaces "0.1.13" :scope "test"]

   ;; for compile
   [org.clojure/clojurescript "1.9.293"]

   ;; dependencies
   [cljsjs/facebook "v20150729-0"]
   ])


(require
 '[adzerk.boot-cljs      :refer [cljs]]
 '[adzerk.boot-cljs-repl :refer [cljs-repl start-repl]]
 '[adzerk.boot-reload    :refer [reload]]
 '[adzerk.bootlaces      :refer :all]
 '[pandeiro.boot-http    :refer [serve]]
 )


(def +version+ "0.1.2")
(bootlaces! +version+)

(task-options!
 pom {:project 'netpyoung/fb-sdk-cljs
      :version +version+
      :description "facebook javascript sdk wrapper for clojurescript"
      })



(deftask build []
  (comp (speak)
        (cljs)))

(deftask run []
  (comp (serve :ssl true
               :ssl-props {:port 3443
                           :keystore "boot-http-keystore.jks"
                           :key-password "qwe123"})
        (watch)
        (cljs-repl)
        (reload)
        (build)))

(deftask production []
  (task-options! cljs {:optimizations :advanced})
  identity)

(deftask development []
  (task-options! cljs {:optimizations :none :source-map true})
  identity)

(deftask dev
  "Simple alias to run application in development mode"
  []
  (set-env! :resource-paths #{"resources"})
  (comp (development)
        (run)))
