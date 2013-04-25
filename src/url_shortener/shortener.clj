(ns url-shortener.shortener)

(def id-generator
  (atom 1))

(def urls
  (atom {}))

(def next-id
  (swap! id-generator inc))

(defn store [token url]
  (swap! urls assoc token url))

(defn tokenize [id]
  (str id))

(defn shorten [url]
  "Shortens a URL, and stores and returns the token"
  (let [token (tokenize next-id)]
    (store token url)
    token))

(defn expand [token]
  "Expands a token into a full URL"
  (@urls token))