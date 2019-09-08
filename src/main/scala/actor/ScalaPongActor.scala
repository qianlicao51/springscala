package actor

import akka.actor.{Actor, ActorSystem, Props, Status, Terminated}
import akka.pattern.ask
import akka.util.Timeout

import scala.concurrent.duration._
import scala.concurrent.{Await, Future}

class ScalaPongActor extends Actor {
  var count = 10

  override def receive: Receive = {
    case "Ping" => {
      if (count > 0) {
        sender() ! "Pong" + (count -= 1)
      } else {
        context.stop(self) //停止当前 actorref |
        context.system.terminate() //退出 sctorSystem
      }
    }
    case _ =>
      sender() ! Status.Failure(new Exception("unknown message"))
  }
}

object ScalaPongActor extends App {
  val system = ActorSystem()
  implicit val timeout = Timeout(5 seconds) // 需要导入 import scala.concurrent.duration._

  val pongActor = system.actorOf(Props[ScalaPongActor], "scalaPongAC")

  //需要返回结果使用ask  |发送消息时  "?"
  while (true) {
    val future = pongActor ? "Ping" //uses the implicit timeout|需要引入 import akka.pattern.ask
    val result = Await.result(future.mapTo[String], 1 second)
    println(s"接收到的消息${result}")
  }
  //停止
  private val terminateFuture: Future[Terminated] = system.terminate()
  Await.ready(terminateFuture, Duration.Inf)


}