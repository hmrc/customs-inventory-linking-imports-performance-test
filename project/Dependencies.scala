import sbt._

object Dependencies {

  private val gatlingVersion = "3.4.2"

  val test = Seq(
    "com.github.nscala-time" %% "nscala-time" % "2.14.0" % Test,
    "com.typesafe"          % "config"                    % "1.3.1"         % Test,
    "uk.gov.hmrc"          %% "performance-test-runner"   % "5.0.0"        % Test,
    "io.gatling"            % "gatling-test-framework"    % gatlingVersion  % Test,
    "io.gatling.highcharts" % "gatling-charts-highcharts" % gatlingVersion  % Test,
    "org.scala-lang.modules" % "scala-xml_2.12"            % "1.0.6"        % Test
  )
}
