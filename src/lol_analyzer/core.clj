(ns lol-analyzer.core
  (:require [lol-analyzer.config :as config]
            [lol-analyzer.riot-api :as riot-api]
            [lol-analyzer.match :as match]
            [lol-analyzer.analysis :as analysis]))

(defn -main []
  (let [match-ids (riot-api/get-matches)
        player-stats (mapv (fn [match-id]
                             (match/extract-player-stats
                               (riot-api/get-match-by-id match-id)
                               config/puuid))
                           match-ids)]
    (println "Azir win rate:"
             (analysis/calculate-win-rate player-stats))))