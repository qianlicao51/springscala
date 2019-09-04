package scalafuture

object ThreadMain extends App {

  val t: Thread = Thread.currentThread()

  val name = t.getName
  println(s" thread name is ${name}")

}
