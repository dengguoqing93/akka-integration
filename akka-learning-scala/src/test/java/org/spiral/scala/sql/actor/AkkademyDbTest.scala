package org.spiral.scala.sql.actor

import akka.actor.ActorSystem
import akka.testkit.TestActorRef
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
import org.spiral.scala.sql.model.SetRequest

/**
 * ${DESCRIPTION}
 *
 * @author dengguoqing
 * @date 2019/12/12
 * @since 1.0 Version
 * @copyright spiral
 */
class AkkademyDbTest extends AnyFlatSpec with Matchers {
  implicit val system = ActorSystem()


  it should "place key/value into map" in {
    val actorRef = TestActorRef(new AkkademyDb)
    actorRef ! SetRequest("key", "value")

    val akkademyDb = actorRef.underlyingActor
    akkademyDb.map.get("key").get should equal("value")
  }
}
