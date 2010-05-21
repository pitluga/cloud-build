(ns cloud-build.scm.git
  (:import 
    (net.sourceforge.cruisecontrol.sourcecontrols Git)
    (net.sourceforge.cruisecontrol.bootstrappers GitBootstrapper)
    (java.util Date Calendar)))

(defn- yesterday []
  (.getTime (doto (Calendar/getInstance) (.add Calendar/HOUR -72))))

(defn- today [] (Date.))

(defn- modifications-between [lwc from-date to-date]
  (let [repo (doto (Git.) (.setLocalWorkingCopy lwc))]
    (.getModifications repo from-date to-date)))
  
(defn- pull-from-origin [lwc]
  (doto (GitBootstrapper.) (.setLocalWorkingCopy lwc) (.bootstrap)))
  
(defn modifications [lwc]
  (pull-from-origin lwc)
  (modifications-between lwc (yesterday) (today)))
