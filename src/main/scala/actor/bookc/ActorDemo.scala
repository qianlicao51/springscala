package actor.bookc

import akka.actor.{Actor, ActorRef, ActorSystem, Props, UntypedActor}
import akka.event.Logging
import akka.pattern.ask
import akka.util.Timeout
import org.joda.time.DateTime

import scala.concurrent.Await
import scala.concurrent.duration._

class ActorDemo extends Actor {
  val log = Logging(context.system, this)

  override def receive: Receive = {
    case msg: String => {
      log.info(s"接收到的消息是:$msg")
      sender() ! s"${new DateTime().toString("yyyy-MM-dd-HH-mm-ss")}"
    }
    case _ => println("未知数据")
  }
}

object ActorDemo extends App {

  //1 创建 ActorSystem ,专门用于创建 actor
  val actorFactory = ActorSystem("ActorFactory")
  implicit val timeout = Timeout(5 seconds)
  //2创建一个 actor 的同时返回 actor 的 actorRef
  //使用反射创建，
  //sayHelloactorRef 就是Props[SayHelloActor] 的ActorRef
  val actorDemo: ActorRef = actorFactory.actorOf(Props[ActorDemo], "actorDemo")

  actorDemo tell("hello akka", ActorRef.noSender) //果然是异步
  println("send over")


  val future = actorDemo ? "Ping" //uses the implicit timeout
  val result = Await.result(future.mapTo[String], 15 second)
  println(s"ask 返回的结果 ${result}")

}
