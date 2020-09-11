resolvers += Resolver.url(
  "as24-ivy-releases",
  new URL("https://nexus.tools.autoscout24.com/nexus/content/repositories/as24-ivy-releases/")
)(Resolver.ivyStylePatterns)

addSbtPlugin("com.autoscout24" % "sbt-scalascout" % "0.3.0")

addSbtPlugin("com.typesafe.sbt" % "sbt-native-packager" % "1.3.16")
