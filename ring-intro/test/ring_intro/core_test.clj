(ns ring-intro.core-test
  (:require [clojure.test :refer :all]
            [ring.mock.request :refer :all]
            [ring-intro.core :refer :all]))

(deftest test-app
  (testing "main route"
    (let [response (handler (request :get "/hello"))]
      (is (= (:status response) 200))
      (is (= (:body response) "Hello Programmer"))))

  (testing "not-found route"
    (let [response (handler (request :get "/invalid"))]
      (is (= (:status response) 404)))))
