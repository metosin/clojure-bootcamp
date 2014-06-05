(ns workflow.handler
  (:require [compojure.api.sweet :refer :all]
            [ring.util.http-response :refer :all]
            [workflow.domain :as domain]))

(defapi app
  (swagger-ui)
  (swagger-docs
    :title "workflow Api"
    :description "workflow Api description")
  (swaggered "math"
    :description "math with parameters"
    (GET* "/stuff" []
      (ok (domain/get-stuff)))
    (GET* "/plus" []
      :return domain/Result
      :query-params [x :- Long, y :- Long]
      :summary "x+y"
      (ok {:result (+ x y)}))))
