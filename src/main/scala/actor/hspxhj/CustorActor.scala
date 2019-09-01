package actor.hspxhj

import akka.actor.{Actor, ActorRef, ActorSelection, ActorSystem, Props}
import com.typesafe.config.ConfigFactory

import scala.io.StdIn

class CustorActor(serverHost: String, serverPort: Int) extends Actor {
  //定义一个 YellowChickenServerActorRef
  var sercerActorRef: ActorSelection = _


  //actor 中有个方法 preStart方法，会在actor运行前执行|初始化 工作
  override def preStart(): Unit = {
    sercerActorRef = context.actorSelection(s"akka.tcp://Server@${serverHost}:${serverPort}/user/YellowChickenServerActor")
    println("客户端初始化" + sercerActorRef)
  }

  override def receive: Receive = {
    case "start" => println("客户端start 可以咨询问题")
    case msg: String => {
      //发给服务器
      sercerActorRef ! ClientMsg(msg)
    }
    case ServerMsg(mes) => {
      println(s"收到回复：${mes}")
    }
  }
}

object CustorActor extends App {
  val (clienthost, clientport, serverHost, serverPort) = ("127.0.0.1", 9990, "127.0.0.1", 9999)
  val config = ConfigFactory.parseString(
    s"""
       |akka.actor.provider=akka.remote.RemoteActorRefProvider
       |akka.remote.netty.tcp.hostnam=$clienthost
       |akka.remote.netty.tcp.port=$clientport
       |""".stripMargin)
  //创建actor|名字很重要
  val actorSystem = ActorSystem("client", config)
  // acotr 和ActorRef
  val customerRef: ActorRef = actorSystem.actorOf(Props(new CustorActor("192.168.226.1", 9999)), "Customer")
  // 启动actor
  customerRef ! "start"

  while (true) {
    println("输入咨询的问题")
    val msg = StdIn.readLine()
    customerRef ! msg
  }

}