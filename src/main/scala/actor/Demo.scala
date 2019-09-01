package actor

import akka.actor.Actor

import scala.concurrent.Future

object Demo {
  def sout(msg: String): Unit = {
    println(s"${msg}")
  }

  def main(args: Array[String]): Unit = {
    Demo sout("厉害了 scala")
  }

}

