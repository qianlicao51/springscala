package actor.char01

import actor.ScalaPongActor
import akka.actor.{ActorSystem, Props}
import akka.pattern.ask
import akka.util.Timeout
import org.scalatest.{FunSpecLike, Matchers}

import scala.concurrent.Await
import scala.concurrent.duration._


class ScalaAskExampleTest extends FunSpecLike with Matchers {

  val system = ActorSystem()
  implicit val timeout = Timeout(5 seconds) // 需要导入 import scala.concurrent.duration._

  val pongActor = system.actorOf(Props(classOf[ScalaPongActor]), "scalaPongAC")

  describe("Pong Actor") {
    it("should respond with Pong") {
      val future = pongActor ? "Ping" //uses the implicit timeout|需要引入 import akka.pattern.ask
      val result = Await.result(future.mapTo[String], 1 second)
    }

    it("should fail on unknow message") {
      val future = pongActor ? "unknow"
      intercept[Exception] {
        Await.result(future.mapTo[String], 1 seconds)
      }
    }
  }
}