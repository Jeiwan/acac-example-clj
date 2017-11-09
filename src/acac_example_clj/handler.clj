(ns acac-example-clj.handler
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]))

(defroutes foo-routes
  (GET "/"
       {cookies :cookies}
       {:status 200
        :headers {"Content-Type" "text/html"}
        :body "<h1>Hello, foo!</h1>"})

  (GET "/cookies"
       {cookies :cookies}
       {:status 200
        :headers {"Content-Type" "text/html"}
        :body (pr-str cookies)})

  (route/not-found "Not Found"))

(defroutes bar-routes
  (GET "/"
       {cookies :cookies}
       {:status 200
        :headers {"Content-Type" "text/html"}
        :body "<h1>Hello, bar!</h1>"})

  (GET "/cookies"
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
                   "bar.example.dev:3000" bar-routes}))

(def app
  (wrap-defaults app-routes site-defaults))
