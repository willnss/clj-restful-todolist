(ns clj-restful-todolist.core
  (:require [toucan.db :as db]
            [toucan.models :as models]
            [ring.adapter.jetty :refer [run-jetty]]
            [compojure.api.sweet :refer [api routes]]
            [clj-restful-todolist.todoitem :refer [todoitem-routes]])
  (:gen-class))

(def db-spec
  {:dbtype "postgres"
   :dbname "todosdb"
   :user "postgres"
   :password "pgsql123"})

(def swagger-config
  {:ui "/swagger"
   :spec "/swagger.json"
   :options {:ui {:validatorUrl nil}
             :data {:info {:version "1.0.0", :title "restful api clojure challenge"}}}})

(def app (api {:swagger swagger-config} (apply routes todoitem-routes)))

(defn -main
  [& _args]
  (db/set-default-db-connection! db-spec)
  (models/set-root-namespace! 'clj-restful-todolist.models)
  (run-jetty app {:port 3000})
  (println "ok"))
