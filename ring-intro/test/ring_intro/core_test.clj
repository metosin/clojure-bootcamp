(ns ring-intro.core-test
  (:require [clojure.test :refer :all]
            [ring.mock.request :refer :all]
            [ring-intro.core :refer :all]))

(deftest test-app
  (testing "ping route"
    (let [response (handler (request :get "/ping"))]
      (is (= (:status response) 200))
      (is (= (:body response) "Hello Programmer"))))

  ;; write a pong-test

  (testing "not-found route"
    (let [response (handler (request :get "/invalid"))]
      (is (= (:status response) 404)))))
