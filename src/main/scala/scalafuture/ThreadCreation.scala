package scalafuture

object ThreadCreation extends App {

  class MyThread extends Thread {

    override def run(): Unit = {
      println(" new Thread running..")

    }
  }

  //  val t = new MyThread
  //  t.start()
  //  t.join()
  //  println("new Thread joined.")

  def thread(body: => Unit): Thread = {
    val t = new Thread {
      override def run(): Unit = body
    }
    t.start()
    t
  }


}

import java.util.concurrent
import java.util.concurrent.TimeUnit

import scala.concurrent._

object ExecutorCreate extends App {

  val executor = new forkjoin.ForkJoinPool(5)

  executor.execute(new Runnable {
    override def run(): Unit = {
      println("this task is run asynchronsously")
    }

executor.shutdown()
executor.awaitTermination(10, java.util.concurrent.TimeUnit.SECONDS)
    println("main")
    Thread.sleep(500)
  })

}
