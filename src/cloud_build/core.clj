(ns cloud-build.core
  (:import 
    (net.sourceforge.cruisecontrol Progress)
    (net.sourceforge.cruisecontrol.builders ExecBuilder))
  (:use [cloud-build.scm.git :as git]))

(println (git/modifications "/Users/tony/work/build-test"))

(def nil-progress (proxy [Progress] [] 
  (setValue [s] (println s))))


(defn exec [workingDir command args]
  (println workingDir command args)
  (let [builder (doto (ExecBuilder.) (.setWorkingDir workingDir) (.setCommand command) (.setArgs args))]
    (.build builder {} nil-progress)))


(defn build-loop []
  (loop [changes (git/modifications "/Users/tony/work/build-test")]
    (if (empty? changes)
      (Thread/sleep 10000)
      (println (exec "/Users/tony/work/build-test" "rake" "test:failing")))
      (recur (git/modifications "/Users/tony/work/build-test"))))
    
(build-loop)