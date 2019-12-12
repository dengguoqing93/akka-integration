package org.spiral.java.sql.actor;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.testkit.TestActorRef;
import org.junit.Test;
import org.spiral.java.sql.model.SetRequest;

import static org.junit.Assert.assertEquals;


/**
 * 消息处理单元测试
 *
 * @author dengguoqing
 * @date 2019/12/12
 * @copyright spiral
 * @since 1.0 Version
 */
public class AkkademyDbTest {

    @Test
    public void createReceive() {
        TestActorRef<AkkademyDb> actorRef = TestActorRef.create(Common.system,
                                                                Props.create(
                                                                        AkkademyDb.class));
        actorRef.tell(new SetRequest("key", "value"), ActorRef.noSender());

        AkkademyDb akkademyDb = actorRef.underlyingActor();
        assertEquals("value", akkademyDb.map.get("key"));
    }
}