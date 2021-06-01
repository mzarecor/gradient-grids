(defproject gradient-grids "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.10.1"]
                 [quil "3.1.0"]]
  :injections[ (.. System (setProperty "jogl.disable.openglcore" "false"))]
  :java-cmd "/usr/java/jdk1.8.0_281-amd64/bin/java"                 
                 )
