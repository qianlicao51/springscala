package scalafuture

import java.util.concurrent.TimeUnit

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.io.Source
import scala.util.{Failure, Success, Try}

object FuturesFailure extends App {
  private val urlSpec = Future {
    val invalidUrl = "http://commons.apache.org/proper/commons-io/index.html"
    Source.fromURL(invalidUrl).mkString
  }
  urlSpec.failed foreach {
    case t => println(s"exception occurred $t")
  }
   TimeUnit.SECONDS.sleep(1)

def handleMessage(t:Try[String]) = t match {
  case Failure(exception) => println(exception)
  case Success(value) => println(s" fail happend -$value")
}
}
