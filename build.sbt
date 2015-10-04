organization := "com.thoughtworks"

name := "idea-plugin-akka-demo"

version in ThisBuild := "0.1-SNAPSHOT"

scalaVersion in ThisBuild := "2.11.1"

sbtVersion in ThisBuild := "0.13.6"

ivyScala := ivyScala.value map {
  _.copy(overrideScalaVersion = true)
}

resolvers in ThisBuild ++= Seq(
  "akka" at "http://repo.akka.io",
  "Akka Snapshot Repository" at "http://repo.akka.io/snapshots/"
)


libraryDependencies in ThisBuild ++= Seq(
  "com.typesafe.akka" %% "akka-actor" % "2.3.12"
)

//retrieveManaged := true

lazy val convertToPluginProject = taskKey[Unit]("Convert current project to plugin project")

convertToPluginProject := {
  PluginConverter.convertToPlugin(baseDirectory.value / ".idea" / "modules" / (name.value + ".iml"))
  PluginConverter.createPluginTask(baseDirectory.value / ".idea" / "workspace.xml", name.value)
}

