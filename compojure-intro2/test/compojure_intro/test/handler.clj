(ns compojure-intro.test.handler
  (:require [midje.sweet :refer :all]
            [ring.mock.request :refer :all]
            [compojure-intro.test.jetty :refer :all]
            [compojure-intro.handler :refer :all]))

(facts "for the live server"
  (background
    (before :contents (start-jetty))
    (after  :contents (stop-jetty)))

  (fact "echo echos json"
    (let [{:keys [status body]} (http :get "/echo" {:beer "olvi"
                                                    :tags [:oldscool]})]
      status => 200
      body => {:a 1 :b [1 2 3]}
      ))
  )
