package actor.hspxhj

import akka.actor.{Actor, ActorRef, ActorSystem, Props}
import akka.util.Timeout
import com.typesafe.config.ConfigFactory

import scala.concurrent.duration._

class YellowChickenServerActor extends Actor {
  override def receive: Receive = {
    case "start" => {
      println("客服开始工作")
    }
    //接收到 ClientMsg消息
    case ClientMsg(msg) => {
      msg match {
        case "学费" => sender() ! ServerMsg("2500$")
        case "地址" => sender() ! ServerMsg("中国")
        case "技术" => sender() ! ServerMsg("scala java php")
        case _ => sender() ! ServerMsg("what")
      }
    }
  }

}

//主程序
object YellowChickenServerActor extends App {
  implicit val system = ActorSystem()
  implicit val timeout = Timeout(5 seconds) // 需要导入 import scala.concurrent.duration._

  val (host, port) = ("127.0.0.1", 9999)
  val config = ConfigFactory.parseString(
    s"""
       |akka.actor.provider=akka.remote.RemoteActorRefProvider
       |akka.remote.netty.tcp.hostnam=$host
       |akka.remote.netty.tcp.port=$port
       |""".stripMargin)

  //创建actor|名字很重要
  val actorSystem = ActorSystem("Server", config)

  // acotr 和ActorRef
  val yellowChickenServerActorRef: ActorRef = actorSystem.actorOf(Props[YellowChickenServerActor], "YellowChickenServerActor")

  yellowChickenServerActorRef ! "start"
  //[akka.tcp://Server@192.168.226.1:9999]
}
