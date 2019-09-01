package actor.char01

import akka.actor.ActorSystem
import akka.pattern.ask
import akka.util.Timeout
import org.scalatest.{FunSpecLike, Matchers}

import scala.concurrent.Await
import scala.concurrent.duration._

class SClinet(remoteAdress: String) {
  private implicit val timeout = Timeout(2 seconds)
  private implicit val system = ActorSystem("localSystem")
  private val remoteDb = system.actorSelection(s"akka.tcp://akkademy@127.0.0.1:2552")


  def set(key: String, value: Object) = {
    //    remoteDb ? SetRequest(key,value)
    remoteDb ? SetRequest(key, value) //?需引入 import akka.pattern.ask
  }

  def get(key: String) = {
    remoteDb ? GetRequest(key)
  }
}

/**
 * 测试用例
 *
 */
 class SClientIntegrationSpec extends FunSpecLike with Matchers{
  val client = new SClinet("127.0.0.1:2552")

  describe("akkademyDblient"){
    it("should set a value"){
      client.set("123",new Integer(123))
      val futureResult= client.get("123")
      val result=Await.result(futureResult,10 seconds)

      result should equal(123)

    }

  }

}