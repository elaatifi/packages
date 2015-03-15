(set-env!
  :resource-paths #{"resources"}
  :dependencies '[[adzerk/bootlaces   "0.1.9" :scope "test"]
                  [cljsjs/boot-cljsjs "0.4.3" :scope "test"]])

(require '[adzerk.bootlaces :refer :all]
         '[cljsjs.boot-cljsjs.packaging :refer :all])

(def +version+ "0.13.0")
(bootlaces! +version+)

(task-options!
 pom  {:project     'cljsjs/react-bootstrap-datetimepicker
       :version     +version+
       :description "React Bootstrap DateTime picker packaged up with Google Closure externs"
       :url         "https://github.com/quri/react-bootstrap-datetimepicker"
       :scm         {:url "https://github.com/elaatifi/packages"}
       :license     {"MIT" "https://raw.githubusercontent.com/quri/react-bootstrap-datetimepicker/master/LICENSE"}})

(deftask package []
  (comp
    (download :url "https://raw.githubusercontent.com/quri/react-bootstrap-datetimepicker/master/dist/react-bootstrap-datetimepicker.js")
	(download :url "https://raw.githubusercontent.com/quri/react-bootstrap-datetimepicker/master/dist/react-bootstrap-datetimepicker.min.js")
    (sift :move {#"react-bootstrap-datetimepicker.js" "cljsjs/development/react-bootstrap-datetimepicker.inc.js"
                 #"react-bootstrap-datetimepicker.min.js" "cljsjs/production/react-bootstrap-datetimepicker.min.inc.js"})
    (sift :include #{#"^cljsjs"})
    (deps-cljs :name "cljsjs.react-bootstrap-datetimepicker"
			   :requires ["cljsjs.react-bootstrap" "cljsjs.moment"])))
