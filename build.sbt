val versions = new {
  val scala     = "2.12.11"
  val scalatest = "3.1.2"

  // Logging
  val logback      = "1.2.3"
  val scalaLogging = "3.9.2"
  val logstash     = "6.2"

  // Json
  val circe = "0.12.3"

  // AWS
  val lambdaCore   = "1.2.0"
  val lambdaEvents = "2.2.7"
}

lazy val root = project
  .in(file("."))
  .enablePlugins(JavaAppPackaging)
  .settings(
    name := "weather-lambda",
    version := sys.env.getOrElse("VERSION", "1.0-SNAPSHOT"),
    scalaVersion := versions.scala,
    libraryDependencies ++= Seq(
      "org.scalatest" %% "scalatest" % versions.scalatest % "test",
      // Logging
      "ch.qos.logback"              % "logback-classic"          % versions.logback,
      "com.typesafe.scala-logging" %% "scala-logging"            % versions.scalaLogging,
      "net.logstash.logback"        % "logstash-logback-encoder" % versions.logstash,
      // Json
      "io.circe" %% "circe-generic" % versions.circe,
      // AWS
      "com.amazonaws"           % "aws-lambda-java-core"     % versions.lambdaCore,
      "com.amazonaws"           % "aws-lambda-java-events"   % versions.lambdaEvents,
      "org.scala-lang.modules" %% "scala-xml"                % "1.2.0",
      "org.scala-lang.modules" %% "scala-parser-combinators" % "1.1.2",
    ),
    topLevelDirectory := None,
  )
