(set-env!
  :resource-paths #{"resources"}
  :dependencies '[[adzerk/bootlaces   "0.1.9" :scope "test"]
                  [cljsjs/boot-cljsjs "0.4.3" :scope "test"]])

(require '[adzerk.bootlaces :refer :all]
         '[cljsjs.boot-cljsjs.packaging :refer :all])

(def +version+ "0.13.0")
(bootlaces! +version+)

(task-options!
 pom  {:project     'cljsjs/react-bootstrap
       :version     +version+
       :description "ReactBootstrap packaged up with Google Closure externs"
       :url         "http://react-bootstrap.github.io/"
       :scm         {:url "https://github.com/elaatifi/packages"}
       :license     {"MIT" "https://github.com/react-bootstrap/react-bootstrap/blob/master/LICENSE"}})

(deftask package []
  (comp
    (download :url "https://github.com/react-bootstrap/react-bootstrap/releases/download/v0.13.0/react-bootstrap.js"
              ;:checksum "6a242238790b21729a88c26145eca6b9"
			  )
	(download :url "https://github.com/react-bootstrap/react-bootstrap/releases/download/v0.13.0/react-bootstrap.min.js"
              ;:checksum "6a242238790b21729a88c26145eca6b9"
			  )
    (sift :move {#"react-bootstrap.js" "cljsjs/development/react-bootstrap.inc.js"
                 #"react-bootstrap.min.js" "cljsjs/production/react-bootstrap.min.inc.js"})
    (sift :include #{#"^cljsjs"})
    (deps-cljs :name "cljsjs.react-bootstrap"
			   :requires ["cljsjs.react"])))
