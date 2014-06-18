(defproject fb-sdk-cljs "0.1.0-SNAPSHOT"

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
       :source-paths ["src" "dev/cljs"]
       :compiler {
                  :output-to "resources/public/fb-sdk-cljs.js"
                  :output-dir "resources/public/out/"
                  :optimizations :none
                  :static-fns false
                  :source-map true}}]
     }
    }

   }
  )
