(ns lol-analyzer.match-test
  (:require [lol-analyzer.match :as match]
            [midje.sweet :refer :all]))

(def test-match
  {:info
   {:participants
    [{:puuid "player-1"
      :championName "Azir"
      :teamPosition "MIDDLE"
      :teamId 100
      :win true}

     {:puuid "player-2"
      :championName "Ahri"
      :teamPosition "MIDDLE"
      :teamId 200
      :win false}

     {:puuid "player-3"
      :championName "Jinx"
      :teamPosition "BOTTOM"
      :teamId 200
      :win false}]}})

(fact "extract-player-stats finds the correct player"
      (match/extract-player-stats test-match "player-1")
      =>
      {:puuid "player-1"
       :championName "Azir"
       :teamPosition "MIDDLE"
       :teamId 100
       :win true})

(fact "extract-player-stats returns nil when player is not found"
      (match/extract-player-stats test-match "unknown-player")
      =>
      nil)

(fact "extract-lane-opponent finds the enemy in the same lane"
      (match/extract-lane-opponent test-match "player-1")
      =>
      {:puuid "player-2"
       :championName "Ahri"
       :teamPosition "MIDDLE"
       :teamId 200
       :win false})