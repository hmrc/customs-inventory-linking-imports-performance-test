credentials += Credentials(Path.userHome / ".sbt" / ".credentials")

addSbtPlugin("uk.gov.hmrc" % "hmrc-resolvers" % "1.2.0")

addSbtPlugin("uk.gov.hmrc" % "sbt-auto-build" % "1.4.0")

addSbtPlugin("io.gatling" % "gatling-sbt" % "2.1.7")
