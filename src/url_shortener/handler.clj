(ns url-shortener.handler
  (:use compojure.core
        [url-shortener.shortener :only (shorten expand)])
  (:require [compojure.handler :as handler]
            [compojure.route :as route]))

(defn redirect [url]
  {:status 302 :headers {"Location" url}})

(defroutes app-routes
  (GET "/" []
       (redirect "https://github.com/justincampbell/language-exploration"))

  (GET "/shorten" [url]
       (if url
         (let [token (shorten url)
               path (str "/" token)]
           {:status 201 :body path})
         {:status 400}))

  (GET "/:token" [token]
       (let [full-url (expand token)]
         (if full-url
           (redirect full-url)
           {:status 404}))))

(def app
  (handler/site app-routes))
