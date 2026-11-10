(ns lol-analyzer.match)

(defn extract-player-stats [match puuid]
  (let [participants (get-in match [:info :participants])]
    (some #(when (= (:puuid %) puuid) %) participants)))

(defn extract-lane-opponent [match puuid]
  (let [player (extract-player-stats match puuid)
        participants (get-in match [:info :participants])]
    (some #(when (and (= (:teamPosition %) (:teamPosition player))
                      (not= (:teamId %) (:teamId player)))
             %)
          participants)))