(ns lol-analyzer.core
  (:require [lol-analyzer.config :as config]
            [lol-analyzer.riot-api :as riot-api]
            [lol-analyzer.match :as match]
            [lol-analyzer.analysis :as analysis]))

(defn win-rate []
  (let [matches (riot-api/get-matches config/puuid)
        player-stats (mapv #(match/extract-player-stats
                              % config/puuid)
                           matches)]
    (println "Azir win rate:"
             (analysis/calculate-champion-win-rate player-stats config/azir-id))))

(defn matchup-analysis []
  (analysis/analyze-matchups
    (riot-api/get-matches config/puuid)
    config/puuid
    config/azir-id))