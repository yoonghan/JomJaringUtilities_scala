name := "JomJaringUtilities_scala"

version := "1.0"

scalaVersion := "2.11.1"

resolvers += "Sonatype Snapshots" at "https://oss.sonatype.org/content/repositories/snapshots/"

libraryDependencies ++= Seq(
  "com.google.code.gson" % "gson" % "2.2.4",
  "com.google.http-client" % "google-http-client" % "1.18.0-rc",
  "com.google.http-client" % "google-http-client-jackson2" % "1.18.0-rc",
  "com.google.apis" % "google-api-services-oauth2" % "v2-rev70-1.18.0-rc",
  "javax.mail" % "mail" % "1.4"
)