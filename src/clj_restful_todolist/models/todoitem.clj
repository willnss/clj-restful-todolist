(ns clj-restful-todolist.models.todoitem
  (:require [toucan.models :refer [defmodel]]))

(defmodel Todoitem :todolist)