(defproject fb-sdk-cljs "0.1.0-SNAPSHOT"

  :description "facebook javascript sdk wrapper for clojurescript"

  :url "https://github.com/netpyoung/fb-sdk-cljs"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}

  :dependencies
  [[org.clojure/clojure "1.6.0"]
   [org.clojure/clojurescript "0.0-2234"]]

  :plugins
  [[lein-cljsbuild "1.0.3"]]

  :clean-targets ^{:protect false}
  [:target-path "out" "resources/public/fb-sdk-cljs.js" "resources/public/out"]

  :profiles
  {:dev
   {:dependencies
    [[ring "1.3.0"]
     [compojure "1.1.8"]
     [com.cemerick/piggieback "0.1.3"]
     [weasel "0.2.1"]]

    :source-paths
    ["dev/clj"]

    :repl-options
    {:init-ns repl
     :nrepl-middleware [cemerick.piggieback/wrap-cljs-repl]
     :init (run!)}

    :cljsbuild
    {:builds
     [{:id "dev"
       :source-paths ["src/main" "dev/cljs"]
       :compiler {:output-to "resources/public/fb-sdk-cljs.js"
                  :output-dir "resources/public/out/"
                  :optimizations :none
                  :pretty-print true
                  :static-fns false
                  :source-map true}}

      {:id "adv"
       :source-paths ["src/main" "src/test"]
       :compiler {:output-to "resources/public/fb-sdk-cljs.js"
                  :optimizations :advanced
                  :pretty-print true
                  :static-fns true}}
      ] ;; builds
     }  ;; cljsbuild
    }   ;; dev
   }    ;; profiles
  )     ;; projects
