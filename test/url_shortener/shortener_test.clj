(ns url-shortener.shortener-test
  (:require [clojure.test :refer :all]
            [url-shortener.shortener :refer :all]))

(deftest shortener
  (testing "shortens and expands a URL"
    (let [url "https://justincampbell.me"
          token (shorten url)
          full-url (expand token)]
      (is (= url full-url)))))
