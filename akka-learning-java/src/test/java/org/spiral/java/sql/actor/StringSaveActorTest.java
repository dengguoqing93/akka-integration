package org.spiral.java.sql.actor;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.testkit.TestActorRef;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * ${DESCRIPTION}
 *
 * @author dengguoqing
 * @date 2019/12/12
 * @copyright spiral
 * @since 1.0 Version
 */
public class StringSaveActorTest {

    @Test
    public void createReceive() {
        TestActorRef<StringSaveActor> actorRef = TestActorRef.create(Common.system,
                                                                     Props.create(
                                                                             StringSaveActor.class));
        actorRef.tell(("key"), ActorRef.noSender());

        StringSaveActor akkademyDb = actorRef.underlyingActor();
        assertEquals("key", akkademyDb.string);
        actorRef.tell("value", ActorRef.noSender());
        akkademyDb = actorRef.underlyingActor();
        assertEquals("value", akkademyDb.string);
    }
}