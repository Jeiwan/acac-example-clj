(ns acac-example-clj.pages
  (:require [hiccup.page :refer [html5 include-js]]))

(def foo-index
  (html5
   [:head
    (include-js "https://cdn.jsdelivr.net/npm/jquery@3.2.1/dist/jquery.min.js"
                "/js/js.js")]
   [:body
    [:h1 "Hello, foo!"]
    [:h3 "bar.example.dev cookies:"]
    [:div {:id "cookies"}]]))
