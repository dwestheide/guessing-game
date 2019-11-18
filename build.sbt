scalaVersion := "2.13.1"

libraryDependencies += "io.monix" %% "minitest" % "2.7.0" % "test"

testFrameworks += new TestFramework("minitest.runner.Framework")

enablePlugins(JavaAppPackaging)