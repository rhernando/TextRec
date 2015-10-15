name := "TextRec"

version := "1.0"

organization := "com.stratio"

scalaVersion := "2.10.6"

scalacOptions ++= Seq("-unchecked", "-deprecation", "-feature")

javacOptions  ++= Seq("-source", "1.7", "-target", "1.7")

addCommandAlias("sanity", ";clean ;compile ;scalastyle ;coverage ;test")

val sparkVersion = "1.5.1"

resolvers += "conjars.org" at "http://conjars.org/repo"

libraryDependencies ++= Seq(
  "org.apache.spark" %% "spark-core" % sparkVersion % "compile,test,provided",
  "org.apache.spark" %% "spark-sql" % sparkVersion % "compile,test,provided",
  "org.apache.spark" %% "spark-streaming" % sparkVersion % "compile,test,provided",
  "org.apache.spark" %% "spark-graphx" % sparkVersion % "compile,test,provided",
  "org.apache.spark" %% "spark-streaming-kafka" % sparkVersion % "compile,test",
  "com.orientechnologies" % "orientdb-core" % "2.1.0" % "compile,test",
  "com.orientechnologies" % "orientdb-client" % "2.1.0" % "compile,test",
  "com.orientechnologies" % "orientdb-object" % "2.1.0" % "compile,test",
  "com.orientechnologies" % "orientdb-graphdb" % "2.1.0" % "compile,test",
  "com.orientechnologies" % "orientdb-distributed" % "2.1.0",
  "org.scalatest" %% "scalatest" % "2.0" % "test"
)

logBuffered in Test := false

