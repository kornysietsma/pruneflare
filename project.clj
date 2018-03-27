(defproject pruneflare "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "https://github.com/kornysietsma/pruneflare"
  :license {:name "Apache 2.0"
            :url  "http://www.apache.org/licenses/LICENSE-2.0"}
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [org.clojure/tools.cli "0.3.3"]
                 [cheshire "5.5.0"]]
  :main pruneflare.cli
  :uberjar-name "pruneflare.jar"
  :profiles {
             :uberjar {:aot :all}
             :dev     {:dependencies [[midje "1.8.3"]]}
             }
  :plugins [[lein-midje "3.2"]]
  )
