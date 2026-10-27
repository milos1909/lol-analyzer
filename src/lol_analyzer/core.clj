(ns lol-analyzer.core
  (:require [lol-analyzer.riot-api :as riot-api]))

(defn -main []
  (let [api-key (System/getenv "RIOT_API_KEY")]
    (if api-key
      (riot-api/get-summoner)
      (println "Invalid RIOT_API_KEY"))))