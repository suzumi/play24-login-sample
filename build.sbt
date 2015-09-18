name := """play-login"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.6"

libraryDependencies ++= Seq(
//  jdbc,
//  cache,
//  ws,
//  specs2 % Test,
  "com.h2database" % "h2" % "1.4.177",          // <- この行を追加してください
  "com.typesafe.play" %% "play-slick" % "1.0.0", // <- この行を追加してください
  "jp.t2v" %% "play2-auth"        % "0.14.1",
  "jp.t2v" %% "play2-auth-social" % "0.14.1", // ソーシャルログイン
  "jp.t2v" %% "play2-auth-test"   % "0.14.1" % "test",
  play.sbt.Play.autoImport.cache // デフォルトのIdContainerを使う場合のみ必要です
)

resolvers += "scalaz-bintray" at "http://dl.bintray.com/scalaz/releases"

// Play provides two styles of routers, one expects its actions to be injected, the
// other, legacy style, accesses its actions statically.
routesGenerator := InjectedRoutesGenerator
