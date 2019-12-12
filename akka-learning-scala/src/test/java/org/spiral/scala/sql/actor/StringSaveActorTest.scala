package org.spiral.scala.sql.actor

import akka.actor.ActorSystem
import akka.testkit.TestActorRef
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

/**
 * ${DESCRIPTION}
 *
 * @author dengguoqing
 * @date 2019/12/12
 * @since 1.0 Version
 * @copyright spiral
 */
class StringSaveActorTest extends AnyFlatSpec with Matchers {
  implicit val system = ActorSystem()


  it should "save last send string" in {
    val actorRef = TestActorRef(new StringSaveActor)
    actorRef ! "key"

    val stringSaveActor = actorRef.underlyingActor
    stringSaveActor.message should equal("key")

    actorRef ! "value"
    val stringSaveActor1 = actorRef.underlyingActor
    stringSaveActor1.message should equal("value")

  }
}
