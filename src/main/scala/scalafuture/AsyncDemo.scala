package scalafuture

import org.joda.time.DateTime


/**
 * Scala Async 库
 * https://colobu.com/2016/02/15/Scala-Async/
 */
object AsyncDemo extends App {


  import scala.async.Async.{async, await}
//  import ExecutionContext.Implicits.global

  import scala.concurrent.ExecutionContext.Implicits.global

  val future = async {
    val f1 = async {
      println("first"); true
    }
    val f2 = async {
      println("second"); 42
    }
    if (await(f1)) await(f2) else 0
  }


  future.isCompleted
  println(s"${new DateTime()}")
}
