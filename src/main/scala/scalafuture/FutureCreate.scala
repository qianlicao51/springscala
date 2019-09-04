package scalafuture

import java.util.concurrent.TimeUnit

import scala.concurrent._
import ExecutionContext.Implicits.global

/**
  * 导入scala.concurrent软件包的内容，
  * 然后从Implicits对象导入global执行上下文。这样可以确保全局上下文中执行Future计算，
  * 这也是大多数情况默认的执行环境。
  */
object FutureCreate extends App {

  Future {
    log("the Future is here")
  }

  log("the Future is coming")

  TimeUnit.SECONDS.sleep(1)

  def log(msg: String): Unit = {
    println(msg)
  }
}
