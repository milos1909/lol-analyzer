(ns lol-analyzer.analysis
  (:require [midje.sweet :refer :all]
            [lol-analyzer.analysis :as analysis]))


(fact "calculate-champion-win-rate calculates the correct win rate of a specific champion"
      (analysis/calculate-champion-win-rate
        [{:championId 268 :win true}
         {:championId 268 :win false}
         {:championId 268 :win true}
         {:championId 268 :win false}
         {:championId 103 :win true}]
        268)
      => 50.0)

(fact "calculate-champion-win-rate returns 100 when all games are won"
      (analysis/calculate-champion-win-rate
        [{:championId 268 :win true}
         {:championId 268 :win true}]
        268)
      => 100.0)

(fact "calculate-champion-win-rate returns 0 when no games are won"
      (analysis/calculate-champion-win-rate
        [{:championId 268 :win false}
         {:championId 268 :win false}]
        268)
      => 0.0)

