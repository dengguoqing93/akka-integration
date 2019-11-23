package org.spiral.akka.highconcurrent;

import akka.actor.UntypedAbstractActor;

/**
 * @author guoqing
 * @since ： 2019/7/7 16:14
 */
public class Greeter extends UntypedAbstractActor {

    public static enum Msg {
        GREET, DONE;
    }


    @Override
    public void onReceive(Object message) throws Throwable {
        if (Msg.GREET == message) {
            System.out.println("Hello World");
            getSender().tell(Msg.DONE, getSelf());
        } else {
            unhandled(message);
        }
    }
}
