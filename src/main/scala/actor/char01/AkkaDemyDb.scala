package actor.char01

import akka.actor.{Actor, ActorSystem, Props, Status}
import akka.event.Logging

import scala.collection.mutable

class AkkaDemyDb extends Actor {

  val map = new mutable.HashMap[String, Object]()
  val log = Logging(context.system, this)

override def receive: Receive = {
  case SetRequest(key, value) => {
    log.info("received SetRequest - key: {}  value{}", key, value)
    map.put(key, value)
  }
  case GetRequest(key)=>
    log.info("received GetRequest -key:{}",key)
    val response:Option[Object]=map.get(key)
    response match {
      case Some(x) => sender() !x
      case None => sender() ! Status.Failure(new KeyNotFoundException(s"找不到此${key}"))
    }
  case ex => log.info("received unknown message :{}", ex)
}
}


object Main extends App{
  val system= ActorSystem("akkademy")
  system.actorOf(Props[AkkaDemyDb],name = "akkademy-db")

}
