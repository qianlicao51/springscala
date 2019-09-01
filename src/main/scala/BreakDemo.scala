import scala.util.control.Breaks._

object BreakDemo {
  def main(args: Array[String]): Unit = {

    whileBreak
    println(s"!@#" * 8)
  }

  def whileBreak() = {
    var i = 0
    breakable(
      while (true) {
        i += 1
        println(i)
        if (i == 10) {
          break()
        }
      })
    println("ok")

  }
}
