(ns compojure-intro.test.handler
  (:require [midje.sweet :refer :all]
            [ring.mock.request :refer :all]
            [compojure-intro.test.jetty :refer :all]
            [compojure-intro.handler :refer :all]))

(jetty-facts "for the live server"
  (fact "echo echos json"
    (let [beer {:beer "olvi" :tags ["oldschool"]}
          {:keys [status body]} (post-json "/echo" beer)]
      status => 200
      body => beer)))
