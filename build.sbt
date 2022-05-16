name := """youshd"""
organization := "youshd.com"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.13.8"

libraryDependencies += guice
libraryDependencies += jdbc
libraryDependencies ++= Seq(
  "org.scalatestplus.play" %% "scalatestplus-play" % "5.0.0" % Test,
  "com.typesafe.slick" %% "slick-codegen" % "3.3.2",
  "com.typesafe.play" %% "play-json" % "2.8.1",
  "org.postgresql" % "postgresql" % "42.2.11",
  "com.typesafe.slick" %% "slick-hikaricp" % "3.3.2",
  "org.mindrot" % "jbcrypt" % "0.4"
)

// Adds additional packages into Twirl
//TwirlKeys.templateImports += "youshd.com.controllers._"

// Adds additional packages into conf/routes
// play.sbt.routes.RoutesKeys.routesImport += "youshd.com.binders._"
