(ns compojure-intro.test.handler
  (:require [clojure.test :refer :all]
            [ring.mock.request :refer :all]
            [compojure-intro.handler :refer :all]))

(deftest test-app
  (testing "main route"
    (let [response (app (request :get "/"))]
      (is (= (:status response) 200))
      (is (= (:body response) "Hello World"))))

  (testing "not-found route"
    (let [response (app (request :get "/invalid"))]
      (is (= (:status response) 404)))))
