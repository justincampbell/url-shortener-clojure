(defproject url-shortener "0.1.0-SNAPSHOT"
  :description "URL Shortener"
  :url "https://github.com/justincampbell/url-shortener-clojure"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.5.1"]
                 [compojure "1.1.5"]]
  :plugins [[lein-ring "0.8.3"]]
  :ring {:handler url-shortener.handler/app}
  :profiles
  {:dev {:dependencies [[ring-mock "0.1.3"]]}})
