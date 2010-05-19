(ns cloud-build.core
  (:use [cloud-build.scm.git :as git]))

(println (git/modifications "/Users/tony/work/build-test"))