package demo

object Demo02 extends App {


  val speech =
    """
      |For source and
      |seven year ago
      |""".stripMargin

  println(s"foo\nbar")
  println(raw"foo\nbar")


  speech.charAt(2)
  speech(2)
}
