name := "battleship"

libraryDependencies += "com.twitter" %% "util-eval" % "[6.2.4,)"
libraryDependencies += "org.scala-lang.modules" %% "scala-parser-combinators" % "1.1.0"
libraryDependencies += "org.fusesource.jansi" % "jansi" % "1.17.1"
version := "0.1"

initialize ~= { _ =>
  val ansi = System.getProperty("sbt.log.noformat", "false") != "true"
  if (ansi) {
    System.setProperty("scala.color", "true")
  }
}

scalaVersion := "2.12.7"