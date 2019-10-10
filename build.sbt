name := "gattling-neo4j"

version := "0.1"

scalaVersion := "2.12.9"
val gatlingVersion = "3.2.1"
val neoTypesVersion = "0.13.0"

enablePlugins(GatlingPlugin)

scalacOptions := Seq(
  "-encoding", "UTF-8",
  "-target:jvm-1.8",
  "-deprecation",
  "-feature",
  "-unchecked",
  "-language:implicitConversions",
  "-language:postfixOps"
)

// Add libraries for using Gatling
libraryDependencies += "io.gatling.highcharts" % "gatling-charts-highcharts" % gatlingVersion % "test"
libraryDependencies += "io.gatling"            % "gatling-test-framework"    % gatlingVersion % "test"

// Add libraries for using Neo4J
libraryDependencies += "com.dimafeng" %% "neotypes" % neoTypesVersion

// https://mvnrepository.com/artifact/org.neo4j.driver/neo4j-java-driver
libraryDependencies += "org.neo4j.driver" % "neo4j-java-driver" % "1.7.5"
