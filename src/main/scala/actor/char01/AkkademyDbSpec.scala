package actor.char01

import akka.actor.ActorSystem
import akka.testkit.TestActorRef
import akka.util.Timeout
import org.scalatest.{FunSpecLike, Matchers}

import scala.concurrent.duration._

class AkkademyDbSpec extends FunSpecLike with Matchers {
  implicit val system = ActorSystem()
  implicit val timeout = Timeout(5 seconds) // 需要导入 import scala.concurrent.duration._

  describe("akkademyDb") {
    describe("give SetRequest") {
      it("should place key/value into mao") {
        val actorRef = TestActorRef(new AkkaDemyDb)
        actorRef ! SetRequest("key", "value|Actor学习")

        val akkademyDb = actorRef.underlyingActor
        akkademyDb.map.get("key") should equal(Some("value|Actor学习"))

      }
    }
  }
}
