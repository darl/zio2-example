package dev.zio.quickstart

import zio.ZIO

import scala.concurrent.Await
import scala.concurrent.duration._

object FiberRoots extends App {

  zio.Unsafe.unsafe { implicit unsafe =>
    (1 to 10000).foreach { i =>
      println(i)
      Await.result(
        zio.Runtime.default.unsafe.runToFuture(ZIO.succeed(new Array[Byte](20000000))),
        1.seconds
      )
    }
  }
}
