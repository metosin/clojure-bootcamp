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
    (let [beer {:beer "olvi" :tags ["oldschool"]}
          {:keys [status body]} (http :post "/echo"
                                      {:content-type :json
                                       :form-params beer})]
      status => 200
      body => beer
      )))
