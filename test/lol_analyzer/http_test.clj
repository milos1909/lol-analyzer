(ns lol-analyzer.http-test
  (:require [midje.sweet :refer :all]
            [lol-analyzer.http :as http]))

(fact "get-rsp successfully communicates with Riot API"
      (let [response (http/get-rsp
                       "https://euw1.api.riotgames.com/lol/"
                       "summoner/v4/summoners/by-puuid/ex1ZBn5n3VS9w7PEPNUldSTwQgAo2co54qEymyxrCCv0_HRZrXiZQFYdCO03QXPzv8SQmFo4LGJ7yw")]
        (.statusCode response) => 200))