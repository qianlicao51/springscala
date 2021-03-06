package scalafuture

import java.util.concurrent.TimeUnit

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent._
import scala.io.Source

object FuturesDataType extends App {


  private val buildFile: Future[String] = Future {
    val f = Source.fromFile("pom.xml")
    try {
      f.getLines().mkString("\n")
    } finally {
      f.close()
    }
  }


  log(s" status :${buildFile.isCompleted}.....")

  TimeUnit.SECONDS.sleep(1)

  log(s" status :${buildFile.isCompleted}.....")

  log("\n")
  log(s" status :${buildFile.value}.....")

  def log(msg: String): Unit = {
    println(msg)
  }
}
