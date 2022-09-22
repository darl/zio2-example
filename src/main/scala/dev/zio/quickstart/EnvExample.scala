package dev.zio.quickstart

import zio.{EnvironmentTag, Scope, ZIO, ZIOAppArgs, ZLayer}

import java.time.Instant

object EnvExample extends zio.ZIOApp {

  implicit override def environmentTag: zio.EnvironmentTag[Environment] = EnvironmentTag[Environment]

  override type Environment = MyService with Dep1 with Dep2 with Dep3 with Dep4 with Dep4 with Dep5 with Dep6 with Dep7

  override def bootstrap: ZLayer[ZIOAppArgs with Scope, Any, Environment] = ZLayer.make[Environment](
    ZLayer.fromFunction(MyService.apply _),
    ZLayer.succeed(new Dep1),
    ZLayer.succeed(new Dep2),
    ZLayer.succeed(new Dep3),
    ZLayer.succeed(new Dep4),
    ZLayer.succeed(new Dep5),
    ZLayer.succeed(new Dep6),
    ZLayer.succeed(new Dep7),
    ZLayer.succeed("123"),
    ZLayer.succeed(123),
    ZLayer.succeed(123.0f)
  )

  override def run: ZIO[Environment with ZIOAppArgs with Scope, Any, Any] = {
    val repeats = 1000000
    for {
      // warm up
      _ <- ZIO.serviceWithZIO[MyService](_.doWork.repeatN(50000))

      _ <- ZIO.log("Starting")

      start <- ZIO.clockWith(_.nanoTime)
      _ <- ZIO.serviceWithZIO[MyService](_.doWork.repeatN(repeats))
      end <- ZIO.clockWith(_.nanoTime)
      _ <- ZIO.log(((end - start) / repeats) + "ns per cycle")
    } yield ()
  }
}

case class Dep1()
case class Dep2()
case class Dep3()
case class Dep4()
case class Dep5()
case class Dep6()
case class Dep7()

case class MyService(
    dep1: Dep1,
    dep2: Dep2,
    dep3: Dep3,
    dep4: Dep4,
    dep5: Dep5,
    dep6: Dep6,
    dep7: Dep7,
    str: String,
    f: Float,
    i: Int) {

  def doWork: ZIO[Any, Nothing, Any] = {
    ZIO.scoped(ZIO.scope)
  }
}
