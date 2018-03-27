(ns pruneflare.cli
  (:require
    [pruneflare.prune :as prune]
    [clojure.tools.cli :refer [parse-opts]]
    [clojure.java.io :as io]
    [clojure.string :as string])
  (:import (java.io File))
  (:gen-class))

  (def cli-options
    [["-i" "--input filename" "select an input flare-format json file - if you don't specify one, the program will try to read flare data from standard input for piping"]
     ["-o" "--output filename" "select an output file name (default is STDOUT)"]
     ["-h" "--help"]])

  (defn usage [options-summary]
    (->> ["Utility for prining subdirectories from a d3 prune formatted json file"
          ""
          "Usage: java -jar pruneflare.jar [options] [list of directory names to remove]"
          ""
          "Options:"
          options-summary
          ""
          "The list of directory names is (for now) simple case-sensitive exact matched to directories or files"
          "so, for example, 'java -jar prune2flare -i orig.json -o result.json vendor tmp junk'"
          "will remove any 'vendor' 'tmp' or 'junk' directories and their kids."
          ""
          "Output is a flare JSON file for D3 visualization"
          ""]
         (string/join \newline)))


(defn exit [status msg]
  (binding [*out* *err*]
    (println msg))
  (System/exit status))

(defn error-msg [errors]
  (str "The following errors occurred while parsing your command:\n\n"
       (string/join \newline errors)))

  (defn -main [& args]
    (let [{:keys [options arguments errors summary]} (parse-opts args cli-options)]
      (cond
        (:help options) (exit 0 (usage summary))
        (= (count arguments) 0) (exit 1 (usage summary))
        errors (exit 1 (error-msg errors)))
      (let [in-file (if (:input options)
                        (io/reader (:input options))
                        *in*)
            out-file (if (:output options)
                       (io/writer (:output options))
                       *out*)
            paths arguments]
        (try
          (prune/prune-json-stream in-file out-file paths)
          (finally
            (if (:input options)
              (.close in-file))
            (if (:output options)
              (.close out-file)))))))
