package scalafuture

import scalaz.concurrent._

import scala.concurrent.ExecutionContext.Implicits._
//import scalaz.concurrent._

object ScalazDemo extends App {


  private val value = Future {
    scala.util.Random.shuffle((1 until (100)).toVector)

  }

  (0 until(10000)).par.filter(x=> x.toString==x.toString.reverse)


  println("ss")


}
