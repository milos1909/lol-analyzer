(ns lol-analyzer.analysis
  (:require [lol-analyzer.config :as config]))

(defn calculate-win-rate [matches]
  (let [azir-matches (filter #(= (:championId %) config/champion-id) matches)
        total-games (count azir-matches)
        wins (count (filter :win azir-matches))]
    (* 100.0 (/ wins total-games))))
