package scalafuture


import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent._
import scala.io.Source

object FuturesCallbacks extends App {


  val urlSpace: Future[List[String]] = getUrlSpec()

  def getUrlSpec(): Future[List[String]] = Future {
    val url = "https://github.com/qianlicao51/douyin/blob/master/pom.xml"
    val f = Source.fromURL(url)
    try f.getLines().toList finally f.clone()
  }

  def find(lines: List[String], keyword: String): String =
    lines.zipWithIndex collect {
      case (line, n) if line.contains(keyword) => (n, line)
    } mkString ("\n")


}
