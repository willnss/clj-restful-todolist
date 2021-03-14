(defproject clj-restful-todolist "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}
  :dependencies [[org.clojure/clojure "1.10.1"]
                ; API libs
                 [prismatic/schema "1.1.12"]
                 [metosin/compojure-api "2.0.0-alpha28"]
                 [ring/ring-jetty-adapter "1.9.1"]
                ; Database libs
                 [toucan "1.15.4"]
                 [org.postgresql/postgresql "42.2.4"]
                 [migratus "1.2.7"]]
  :plugins [[migratus-lein "0.7.3"]]
  :main ^:skip-aot clj-restful-todolist.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all
                       :jvm-opts ["-Dclojure.compiler.direct-linking=true"]}}
  :migratus {:store :database
             :migration-dir "migrations"
             :db {:classname "org.postgresql.Driver"
                  :subprotocol "postgresql"
                  :subname "//localhost:5432/todosdb"
                  :user "postgres"
                  :password "pgsql123"}}
  )