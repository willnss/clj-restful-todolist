(ns clj-restful-todolist.handlers.todoitem
  (:require [schema.core :as s]
            [clj-restful-todolist.string-util :as str]
            [clj-restful-todolist.models.todoitem :refer [Todoitem]]
            [toucan.db :as db]
            [ring.util.http-response :refer [created ok not-found]]
            [compojure.api.sweet :refer [POST GET PUT DELETE]]))

(defn valid-description? [description]
  (str/non-blank-with-max-length? 255 description))

(s/defschema TodoitemRequestSchema
  {:description (s/constrained s/Str valid-description?)
   :done s/Bool})

(defn id->created [id]
  (created (str "/todoitems/" id) {:id id}))

(defn create-todoitem-handler [create-todoitem-req]
  (->> create-todoitem-req
       (db/insert! Todoitem)
       :id
       id->created))

(defn todoitem->response [todoitem]
  (if todoitem
    (ok todoitem)
    (not-found)))

(defn get-todoitem-handler [id]
  (-> (Todoitem id)
      todoitem->response))

(defn get-todoitems-handler []
  (->> (db/select Todoitem)
       ok))

(defn update-todoitem-handler [id update-todoitem-req]
  (db/update! Todoitem id update-todoitem-req)
  (ok))

(defn delete-todoitem-handler [id]
  (db/delete! Todoitem :id id)
  (ok))

(def todoitem-routes
  [(POST "/todoitems" []
     :body [create-todoitem-req TodoitemRequestSchema]
     (create-todoitem-handler create-todoitem-req))
   (GET "/todoitems/" []
     (get-todoitems-handler))
   (GET "/todoitems/:id" []
     :path-params [id :- s/Int]
     (get-todoitem-handler id))   
   (PUT "/todoitems/:id" []
     :path-params [id :- s/Int]
     :body [update-todoitem-req TodoitemRequestSchema]
     (update-todoitem-handler id update-todoitem-req))
   (DELETE "/todoitems/:id" []
     :path-params [id :- s/Int]
     (delete-todoitem-handler id))])