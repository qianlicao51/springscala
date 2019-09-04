package scalafuture


import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Promise

object PromiseCreate extends App {

  val p = Promise[String]
  val q = Promise[String]

  p.future foreach { case x => println(s"p success with$x") }

  Thread.sleep(1000)

  p success ("assigned")
  q failure new Exception("not kept")

  q.future.failed foreach { case t => println(s"q fail with $t") }

  Thread.sleep(1000)

}
