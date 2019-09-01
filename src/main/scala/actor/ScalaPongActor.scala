package actor

import akka.actor.{Actor, ActorSystem, Props, Status}
import akka.util.Timeout
import akka.pattern.ask
import scala.concurrent.Await
import scala.concurrent.duration._

class ScalaPongActor extends Actor {
  override def receive: Receive = {
    case "Ping" => sender() ! "Pong"
    case _ =>
      sender() ! Status.Failure(new Exception("unknown message"))
  }
}

object ScalaPongActor extends App {
  val system = ActorSystem()
  implicit val timeout = Timeout(5 seconds) // 需要导入 import scala.concurrent.duration._

  val pongActor = system.actorOf(Props[ScalaPongActor], "scalaPongAC")

  //需要返回结果使用ask  |发送消息时  "?"
  val future = pongActor ? "Ping" //uses the implicit timeout|需要引入 import akka.pattern.ask
  val result = Await.result(future.mapTo[String], 1 second)
  println(s"接收到的消息${result}")
}