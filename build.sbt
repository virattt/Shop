name := """shop"""

version := "1.0-SNAPSHOT"

lazy val root = project.in(file(".")).enablePlugins(PlayScala)

libraryDependencies += jdbc

libraryDependencies += "com.typesafe.slick" %% "slick" % "2.0.2"