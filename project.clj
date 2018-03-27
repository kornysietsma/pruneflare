(defproject pruneflare "1.0.0"
  :description "simple tool to prune directories from a d3 flare json file"
  :url "https://github.com/kornysietsma/pruneflare"
  :license {:name "Apache 2.0"
            :url  "http://www.apache.org/licenses/LICENSE-2.0"}
  :dependencies [[org.clojure/clojure "1.9.0"]
                 [org.clojure/tools.cli "0.3.5"]
                 [cheshire "5.8.0"]]
  :main pruneflare.cli
  :uberjar-name "pruneflare.jar"
  :profiles {
             :uberjar {:aot :all}
             :dev     {:dependencies [[midje "1.9.1"]]}
             }
  :plugins [[lein-midje "3.2"]]
  )
