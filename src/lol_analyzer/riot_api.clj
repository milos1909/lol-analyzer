(ns lol-analyzer.riot-api
  (:require [lol-analyzer.config :as config]
            [lol-analyzer.http :as http]
            [cheshire.core :as cheshire]))

(defn get-json [baseurl endpoint]
  (-> (http/get-rsp baseurl endpoint)
      (.body)
      (cheshire/parse-string true)))

(defn get-summoner []
  (get-json http/platform-base-url (str "summoner/v4/summoners/by-puuid/" config/puuid)))

(defn get-match-ids [puuid]
  (get-json http/match-base-url (str "by-puuid/" puuid "/ids?start=0&count=20")))

(defn get-match-by-id [id]
  (get-json http/match-base-url id))

(defn get-matches [puuid]
  (mapv get-match-by-id (get-match-ids puuid)))