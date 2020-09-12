resolvers += Resolver.url(
  "ivy-releases",
  new URL("https://repo.typesafe.com/typesafe/ivy-releases/")
)(Resolver.ivyStylePatterns)

addSbtPlugin("com.typesafe.sbt" % "sbt-native-packager" % "1.3.16")
