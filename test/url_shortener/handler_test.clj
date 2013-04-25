(ns url-shortener.handler-test
  (:use clojure.test
        ring.mock.request
        url-shortener.handler))

(deftest test-app
  (testing "/"
    (let [response (app (request :get "/"))
          status (:status response)
          headers (:headers response)]
      (is (= status 302))
      (is (= (headers "Location")
             "https://github.com/justincampbell/language-exploration"))))

  (testing "/shorten"
    (let [url "http://justincampbell.me"
          response (app (request :get (str "/shorten?url=" url)))]
      (is (= (:status response) 201))
      (is (= (:body response) "/1")))

    (testing "with no url"
      (let [response (app (request :get "/shorten"))]
        (is (= (:status response) 400)))))

  (testing "/:token"
    (let [url "http://justincampbell.me"
          token "1"
          path (str "/" token)
          response (app (request :get path))]
      (is (= (:status response) 302))
      (is (= ((:headers response) "Location") url))

    (testing "when not found"
      (let [response (app (request :get "/noexistant"))]
        (is (= (:status response) 404)))))))
