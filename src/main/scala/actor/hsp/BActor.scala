package actor.hsp

import akka.actor.{Actor, ActorRef}

class BActor extends Actor {

  override def receive: Receive = {
    case "我打" => {
      println("BActor(乔峰)，挺猛，看我十八掌")
      //可以获取发送消息的引用
      Thread.sleep(1000)
      sender() ! "我打"
    }

  }
}
