package org.guoqing.akka.highconcurrent;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedAbstractActor;

/**
 * @author guoqing
 * @since ： 2019/7/7 16:29
 */
public class HelloWorld extends UntypedAbstractActor {

    private ActorRef greeter;

    @Override
    public void onReceive(Object message) throws Throwable {
        if (Greeter.Msg.DONE == message) {
            greeter.tell(Greeter.Msg.GREET, getSelf());
            getContext().stop(getSelf());
        }else {
            unhandled(message);
        }
    }

    @Override
    public void preStart() throws Exception {
        greeter = getContext().actorOf(Props.create(Greeter.class), "greeter");
        System.out.println("Greeter Actor Path:" + greeter.path());
        greeter.tell(Greeter.Msg.GREET, getSelf());
    }
}
