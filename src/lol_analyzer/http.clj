(ns lol-analyzer.http
  (:import [java.net URI]
           [java.net.http HttpClient
                          HttpRequest
                          HttpResponse$BodyHandlers]))

(def client (HttpClient/newHttpClient))

(def platform-base-url
  "https://euw1.api.riotgames.com/lol/")

(def match-base-url
  "https://europe.api.riotgames.com/lol/match/v5/matches/")

(defn get-rsp [base-url endpoint]
  (let [api-key (System/getenv "RIOT_API_KEY")
        url (str base-url endpoint)
        request (-> (HttpRequest/newBuilder)
                    (.uri (URI/create url))
                    (.header "X-Riot-Token" api-key)
                    (.GET)
                    (.build))
        response (.send client
                        request
                        (HttpResponse$BodyHandlers/ofString))]
    (.body response)))