import actor.char01.{GetRequest, SetRequest}
import akka.actor.{Actor, ActorRef, ActorSelection, ActorSystem, Props}
import akka.event.Logging
import akka.pattern.ask
import akka.util.Timeout
import com.typesafe.config.ConfigFactory

import scala.concurrent.Await
import scala.concurrent.duration._

class SClinet(remoteAddress: String, port: Int) extends Actor {
  private implicit val timeout = Timeout(2 seconds)
  private implicit val system = ActorSystem("LocalSystem")
  val log = Logging(context.system, this)
  var remoteDb: ActorSelection = _

  //actor 中有个方法 preStart方法，会在actor运行前执行|初始化 工作
  override def preStart(): Unit = {
    remoteDb = context.actorSelection(s"akka.tcp://akkademy@${remoteAddress}:${port}/user/akkademyDB")
    println("客户端初始化{} 远程地址{}", remoteDb, remoteAddress)
  }


  def set(key: String, value: Object) = {
    //    remoteDb ? SetRequest(key,value)
    log.info("serverdb{}", remoteDb)
    log.info("receive key{}  value{}", key, value)
    remoteDb ! SetRequest(key, value) //?需引入 import akka.pattern.ask
  }

  def get(key: String) = {
    remoteDb ? GetRequest(key)
  }

  override def receive: Receive = {
    case SetRequest(key, value) => {
      remoteDb ! SetRequest(key, value)
    }
    case GetRequest(key) => {
      remoteDb ? GetRequest(key)
      context.stop(self) //停止当前 actorref |
      context.system.terminate() //退出 sctorSystem
    }
  }
}


object SClinet extends App {

  val (clienthost, clientport, serverHost, serverPort) = ("127.0.0.1", 9990, "127.0.0.1", 9989)
  val config = ConfigFactory.parseString(
    s"""
       |akka.actor.provider=akka.remote.RemoteActorRefProvider
       |akka.remote.netty.tcp.hostnam=$clienthost
       |akka.remote.netty.tcp.port=$clientport
       |""".stripMargin)
  //创建actor|名字很重要
  val actorSystem = ActorSystem("client", config)

  implicit val timeout = Timeout(5 seconds)
  // acotr 和ActorRef
  val sclientActorRef: ActorRef = actorSystem.actorOf(Props(new SClinet("127.0.0.1", 9989)), "clientActorRef")
  sclientActorRef ! SetRequest("124", new Integer(123))


  val future = sclientActorRef ? GetRequest("123")
  val result = Await.result(future.mapTo[Object], 10 seconds)
  println(s"result:${result}")

  // 启动actor
}
