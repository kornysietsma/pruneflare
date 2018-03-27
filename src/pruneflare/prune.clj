(ns pruneflare.prune
  (:require [clojure.java.io :as io]
            [clojure.walk :refer [keywordize-keys]]
            [cheshire.core :as cheshire]
            [clojure.pprint :refer [pprint]])
  (:import (java.io Reader Writer File)))

(defn prune-json
  [node paths]
  (if (:children node)
    (assoc node :children
                (->> (:children node)
                     (remove (fn [child]
                               (paths (:name child))))
                     (map (fn [node] (prune-json node paths)))))
    node))

(defn prune-json-stream
  [in-file out-file paths]
  (let [data (cheshire/parse-stream in-file true)
        pathSet (set paths)]
    (-> data
        (prune-json pathSet)
        (cheshire/generate-stream out-file {:pretty true}))))
