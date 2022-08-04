package dev.zio.quickstart

import zio._
import zio.macros.accessible

@accessible
trait OverflowService {
  def myMethod: IO[OverflowService.MyError, Unit]
}

object OverflowService {
  case class MyError(msg: String)
}
