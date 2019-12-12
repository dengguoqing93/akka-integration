package org.spiral.java.sql.actor;

import akka.actor.AbstractActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import akka.japi.pf.ReceiveBuilder;
import org.spiral.java.sql.model.SetRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * 消息接收响应处理
 *
 * @author dengguoqing
 * @date 2019/12/12
 * @copyright spiral
 * @since 1.0 Version
 */
public class AkkademyDb extends AbstractActor {

    protected final LoggingAdapter log = Logging.getLogger(context().system(), this);

    protected final Map<String, Object> map = new HashMap<>();

    @Override
    public Receive createReceive() {
        return ReceiveBuilder.create().match(SetRequest.class,
                                             message -> {
                                                 log.info("Receive set request", message);
                                                 map.put(message.getKey(),
                                                         message.getValue());
                                             }).matchAny(
                o -> log.info("received unknown message: {}", o)).build();
    }

}
