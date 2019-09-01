package bookB

import akka.actor.{Actor, ActorRef, ActorSystem, Props, Terminated}
import org.joda.time.DateTime

import scala.concurrent.duration.Duration
import scala.concurrent.{Await, Future}

/**
 * Acotr
 */
object Demo01 {
  def main(args: Array[String]): Unit = {

    val bools = Seq(true, false)
    for (bool <- bools) {
      bool match {
        case true => println("Got heads")
        case _ => println(" default value")
      }
    }


    for {
      x <- Seq(1, 5, 2.7, "one", "tow", "four") //1 由于序列 包含不同类型，因此序列的类型为Seq[Any]
    } {
      val str = x match { //2x的类型为 Any
        case 1 => "int 1" //3 如果 x等于 1 则匹配
        case i: Int => "other int :" + i //4 匹配除了1 外 的其他任意整数 ，想x的值安全的转为Int，并赋值给i
        case d: Double => "a double:" + x //5 匹配所有 double 类型，x的值被赋值给 double 类型变量d
        case "one" => "string one" //6 匹配字符串 one
        case s: String => "other string :" + s //7 匹配 one 外的其他任意字符串，x的值被赋 给了string类型的变量x
        case unexpected => " unexpected value:" + unexpected //8 匹配 其他 任意输入 ，        case unexpected => " unexpected value:" + unexpected //8 匹配 其他 任意输入 ，
      }
      println(str) //9 打印返回的字符串
    }
    println(s"-" * 10)

    val codeLg = List("hello", "java", "scala", "c++")
    for (code <- codeLg) {
      println(code)
    }

    //
    val selectData = for {
      code <- codeLg
      if code.contains("c")
    } yield code

    println(selectData)
  }
}

class HeadwordActor extends Actor {
  override def receive: Receive = {
    case message => println(s" playing thr role of $message ")
  }
}

class CreateActors extends App {
  val system = ActorSystem("sample")
  private val simpleActor: ActorRef = system.actorOf(Props[HeadwordActor], "simpleActor")
  simpleActor ! s" simple Acotr ${new DateTime().toString("yyyy-MM-dd HH:mm:ss")}"

  private val eventualTerminated: Future[Terminated] = system.terminate()
  Await.ready(eventualTerminated, Duration.Inf)

}