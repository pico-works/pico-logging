import sbt.Keys._
import sbt._

object Build extends sbt.Build {  
  val pico_event                = "org.pico"        %%  "pico-event"                % "3.0.0"

  val specs2_core               = "org.specs2"      %%  "specs2-core"               % "3.8.4"

  implicit class ProjectOps(self: Project) {
    def standard(theDescription: String) = {
      self
          .settings(scalacOptions in Test ++= Seq("-Yrangepos"))
          .settings(publishTo := Some("Releases" at "s3://dl.john-ky.io/maven/releases"))
          .settings(description := theDescription)
          .settings(isSnapshot := true)
          .settings(resolvers += Resolver.sonatypeRepo("releases"))
          .settings(addCompilerPlugin("org.spire-math" % "kind-projector" % "0.8.0" cross CrossVersion.binary))
    }

    def notPublished = self.settings(publish := {}).settings(publishArtifact := false)

    def libs(modules: ModuleID*) = self.settings(libraryDependencies ++= modules)

    def testLibs(modules: ModuleID*) = self.libs(modules.map(_ % "test"): _*)
  }

  lazy val `pico-fake` = Project(id = "pico-fake", base = file("pico-fake"))
      .standard("Fake project").notPublished
      .testLibs(specs2_core)

  lazy val `pico-logging` = Project(id = "pico-logging", base = file("pico-logging"))
      .standard("Tiny logging library")
      .libs(pico_event)
      .testLibs(specs2_core)

  lazy val all = Project(id = "pico-logging-project", base = file("."))
      .notPublished
      .aggregate(`pico-logging`, `pico-fake`)
}

