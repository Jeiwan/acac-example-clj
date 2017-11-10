(ns acac-example-clj.handler
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]
            [acac-example-clj.pages :as pages]
            [ring.middleware.cors :refer [wrap-cors]]))

(defroutes foo-routes
  (GET "/"
       {cookies :cookies}
       {:status 200
        :cookies {"current-time" {:value (quot (System/currentTimeMillis) 1000)
                                  :domain "example.dev"}}
        :headers {"Content-Type" "text/html"}
        :body pages/foo-index})

  (POST "/cookies"
       {cookies :cookies}
       {:status 200
        :headers {"Content-Type" "text/html"}
        :body (pr-str cookies)})

  (route/not-found "Not Found"))

(defroutes bar-routes
  (GET "/"
       {cookies :cookies}
       {:status 200
        :cookies {"current-time" {:value (quot (System/currentTimeMillis) 1000)
                                  :domain "example.dev"}}
        :headers {"Content-Type" "text/html"}
        :body "<h1>Hello, bar!</h1>"})

  (POST "/cookies"
       {cookies :cookies}
       {:status 200
        :headers {"Content-Type" "text/html"}
        :body (pr-str cookies)})

  (route/not-found "Not Found"))

(defn domain-routing [domain-routes-map]
  (fn [req]
    (when-let [route (get domain-routes-map (get-in req [:headers "host"]))]
      (route req))))

(def app-routes
  (domain-routing {"foo.example.dev:3000" foo-routes
                   "bar.example.dev:3000"
                   (wrap-cors bar-routes
                              :access-control-allow-origin [#"http://foo.example.dev:3000"]
                              :access-control-allow-methods [:get :post])}))

(def app
  (wrap-defaults app-routes (assoc-in site-defaults [:security :anti-forgery] false)))
