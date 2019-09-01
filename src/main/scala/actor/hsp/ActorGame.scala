package actor.hsp

import akka.actor.{ActorRef, ActorSystem, Props}

object ActorGame  extends App {

  //1 创建 ActorSystem ,专门用于创建 actor
  val actorFactory = ActorSystem("ActorFactory")
  //2创建一个 actor 的同时返回 actor 的 actorRef
  //使用反射创建，
  //sayHelloactorRef 就是Props[SayHelloActor] 的ActorRef
  val bactorRef: ActorRef = actorFactory.actorOf(Props[BActor], "bactor")
  val aactorRef: ActorRef = actorFactory.actorOf(Props(new AActor(bactorRef)), "aactor")

  aactorRef ! "start"

}

