name := "utilities-scala"

organization := "com.walcron"

version := "1.0.0"

scalaVersion := "2.11.1"

publishTo := Some(Resolver.file("file",  new File(Path.userHome.absolutePath+"/.m2/repository")))

resolvers += "spray" at "http://repo.spray.io/"

libraryDependencies ++= Seq(
  "com.google.code.gson" % "gson" % "2.2.4",
  "com.google.http-client" % "google-http-client" % "1.18.0-rc",
  "com.google.http-client" % "google-http-client-jackson2" % "1.18.0-rc",
  "com.google.apis" % "google-api-services-oauth2" % "v2-rev70-1.18.0-rc",
  "javax.mail" % "mail" % "1.4",
  "org.imgscalr" % "imgscalr-lib" % "4.2",
  "junit" % "junit" % "4.4",
  "org.scalatest" % "scalatest_2.12" % "3.0.1"
)

scalacOptions += "-feature"
