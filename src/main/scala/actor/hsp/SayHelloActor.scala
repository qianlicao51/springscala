package actor.hsp

import akka.actor.{Actor, ActorRef, ActorSystem, Props}

//1.继承actor，就是一个actor，核心方法是receive ，方法重写
class SayHelloActor extends Actor {

  //1
  // 2 当该 actor的mailBOX接收到消息，会调用 receive
  override def receive: Receive = {
    case "hello" => println("收到hello 回应 hello too:")
    case "ok" => println("收到 ok  回应 ok too:")

    case "exit" => {
      println("接收到指令，退出系统")
      context.stop(self) //停止当前 actorref |
      context.system.terminate() //退出 sctorSystem
    }
    case _ => println("没有匹配")
  }
}

object SayHelloActor {
  //1 创建 ActorSystem ,专门用于创建 actor
  private val actorFactory = ActorSystem("ActorFactory")
  //2创建一个 actor 的同时返回 actor 的 actorRef
  //使用反射创建，
  //sayHelloactorRef 就是Props[SayHelloActor] 的ActorRef
  private val sayHelloactorRef: ActorRef = actorFactory.actorOf(Props[SayHelloActor], "sayHelloactor")


  def main(args: Array[String]): Unit = {
    println(s"actor的路径"+sayHelloactorRef.path)
    //给sayHelloactor 发送消息
    sayHelloactorRef ! "hello"
    sayHelloactorRef ! "ok"
    sayHelloactorRef ! "okk"
    sayHelloactorRef ! "exit"
    // 如何退出 actor模型

  }


}