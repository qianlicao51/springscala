package actor.char01.pong

import actor.ScalaPongActor
import akka.actor.{ActorSystem, Props}
import akka.pattern.ask
import akka.util.Timeout
import org.scalatest.{FunSpecLike, Matchers}

import scala.concurrent.duration._
import scala.concurrent.{Await, Future}

class ScalaAskExamplesTest extends FunSpecLike with Matchers {
  val system = ActorSystem()
  implicit val timeout = Timeout(5 seconds)
  val pongActor = system.actorOf(Props(classOf[ScalaPongActor]))

  describe("Pong actor") {
    it("should respond with Pong") {
      val future = pongActor ? "Ping" //uses the implicit timeout
      val result = Await.result(future.mapTo[String], 1 second)
      assert(result == "Pong")
    }
    it("should fail on unknow message") {
      val future = pongActor ? "unknow"
      intercept[Exception] {
        Await.result(future.mapTo[String], 1 seconds)
      }
    }

  }

  def askPong(mes: String): Future[String] = (pongActor ? mes).mapTo[String]


  describe("FutureExamples") {
    import scala.concurrent.ExecutionContext.Implicits.global
    it("should print to console") {
      (pongActor ? "Ping").onSuccess({
        case x: String => println("replied with: " + x)
      })
      Thread.sleep(100)
    }
  }
}
