package dev.zio.quickstart

import zio._
import zio.test._
import java.lang.RuntimeException

object MainSpec extends ZIOSpecDefault {

  val intLayer: ULayer[Int] = ZLayer.die(new RuntimeException("boom"))

  def spec = suite("Main")(
    test("sampleTest") {
      ZIO.log("test") *>
        ZIO.service[Int] *>
        assertCompletesZIO
    }
  ).provide(
    intLayer
  )
}
