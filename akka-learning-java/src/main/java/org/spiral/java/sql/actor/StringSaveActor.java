package org.spiral.java.sql.actor;

import akka.actor.AbstractActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import akka.japi.pf.ReceiveBuilder;

/**
 * 保存最近发送的字符串
 *
 * @author dengguoqing
 * @date 2019/12/12
 * @copyright spiral
 * @since 1.0 Version
 */
public class StringSaveActor extends AbstractActor {
    protected final LoggingAdapter log = Logging.getLogger(context().system(), this);

    protected String string;

    @Override
    public Receive createReceive() {
        return ReceiveBuilder.create().match(String.class, message -> {
            log.info("最近收的信息为:{}", message);
            string = message;
        }).matchAny(t -> log.info("收到的错误信息", t)).build();
    }
}
