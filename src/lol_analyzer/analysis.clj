(ns lol-analyzer.analysis
  (:require [lol-analyzer.match :as match]))

(defn calculate-champion-win-rate [matches champion-id]
  (let [champion-matches (filter #(= (:championId %) champion-id) matches)
        total-games (count champion-matches)
        wins (count (filter :win champion-matches))]
    (* 100.0 (/ wins total-games))))

(defn group-matchups [matches puuid champion-id]
  (let [matchups (keep (fn [match]
                         (let [player (match/extract-player-stats match puuid)
                               opponent (match/extract-lane-opponent match puuid)]
                           (when (= (:championId player) champion-id)
                             {:position (:teamPosition player)
                              :opponent (:championName opponent)
                              :win (:win player)})))
                       matches)]
    (into {}
          (map (fn [[position matches]]
                 [position (group-by :opponent matches)]))
          (group-by :position matchups))))

(defn calculate-matchup-stats [matches]
  (let [games (count matches)
        wins (count (filter :win matches))
        win-rate (* (/ wins games) 100.0)]
    {:games games
     :wins wins
     :win-rate win-rate}))

(defn calculate-all-matchups-stats [grouped-matches]
  (into {}
        (map (fn [[position matchups]]
               [position
                (into {}
                      (map (fn [[opponent matches]]
                             [opponent (calculate-matchup-stats matches)]))
                      matchups)]))
        grouped-matches))

(defn analyze-matchups [matches puuid champion-id]
  (-> matches
      (group-matchups puuid champion-id)
      calculate-all-matchups-stats))