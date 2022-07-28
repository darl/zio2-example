scalaVersion := "3.1.3"
organization := "dev.zio"
name := "zio-quickstart-hello-world"

libraryDependencies += "dev.zio" %% "zio" % "2.0.0"
libraryDependencies += "dev.zio" %% "zio-test" % "2.0.0" % "test"
libraryDependencies += "dev.zio" %% "zio-test-sbt" % "2.0.0" % "test"

testFrameworks += new TestFramework("zio.test.sbt.ZTestFramework")
