(ns lol-analyzer.riot-api-test
  (:require
    [midje.sweet :refer :all]
    [lol-analyzer.riot-api :as riot-api]
    [lol-analyzer.http :as http]))

(fact "get-json parses JSON response"
      (riot-api/get-json "https://example.com/" "test")
      => {:name "milos1909"
          :level 479
          :rank {:tier "MASTER"
                 :lp 1000}
          :matches [123 456 789]}

      (provided
        (http/get-rsp "https://example.com/" "test")
        => "{\"name\":\"milos1909\",\"level\":479,\"rank\":{\"tier\":\"MASTER\",\"lp\":1000},\"matches\":[123,456,789]}"))