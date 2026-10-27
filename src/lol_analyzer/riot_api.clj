(ns lol-analyzer.riot-api
  (:require [lol-analyzer.config :as config]
            [lol-analyzer.http :as http]
            [cheshire.core :as cheshire]))

(defn get-json [baseurl endpoint]
  (-> (http/get-rsp baseurl endpoint)
      (cheshire/parse-string true)))

(defn get-summoner []
  (get-json http/platform-base-url (str "summoner/v4/summoners/by-puuid/" config/puuid)))

(defn get-matches []
  (get-json http/match-base-url (str "by-puuid/" config/puuid "/ids?start=0&count=20")))

(defn get-match-by-id [id]
  (get-json http/match-base-url id))