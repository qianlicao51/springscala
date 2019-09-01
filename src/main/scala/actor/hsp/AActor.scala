package actor.hsp

import akka.actor.{Actor, ActorRef}

class AActor(actorRef: ActorRef) extends Actor {
  val bActorRef = actorRef

  var nums =1

  override def receive: Receive = {
    case "start" => {
      println(" AActor 开始了")
      self ! "我打" //发送给自己
    }

    case "我打" => {
      //给BActor 发出消息|需要持有Bactor的引用(即 BactorRef)
      println("Aactor(黄飞鸿) 厉害，看我佛山无影脚")
      Thread.sleep(1000)
      nums+=1
      if(nums>10){
        context.stop(self) //停止当前 actorref |
        context.system.terminate() //退出 sctorSystem
      }
      bActorRef ! "我打"

    }
    case _ => println("ss")

  }
}
