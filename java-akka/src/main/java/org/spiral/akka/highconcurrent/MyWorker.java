package org.spiral.akka.highconcurrent;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.actor.UntypedAbstractActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import com.typesafe.config.ConfigFactory;

import java.util.concurrent.TimeoutException;

/**
 * 一个带有生命周期回调函数的Actor
 *
 * @author dengguoqing
 * @date 2019/7/7
 */
public class MyWorker extends UntypedAbstractActor {

    private final LoggingAdapter log = Logging.getLogger(getContext().system(), this);

    public static enum Msg {
        WORKING,
        DONE,
        CLOSE
    }

    @Override
    public void preStart() throws Exception {
        System.out.println("MyWorker is starting");
    }

    @Override
    public void postStop() throws Exception {
        System.out.println("MyWorker is stopping");
    }

    @Override
    public void onReceive(Object message) throws Throwable {
        if (Msg.WORKING == message) {
            log.info("I am working");
        } else if (Msg.DONE == message) {
            log.info("I will working");
        } else if (Msg.CLOSE == message) {
            log.info("I will shutdown");
            getSender().tell(Msg.CLOSE, getSelf());
            getContext().stop(getSelf());
        } else {
            unhandled(message);
        }
    }

    public static void main(String[] args) throws TimeoutException {
        ActorSystem system = ActorSystem.create("inboxDemo",
                                                ConfigFactory.load("samplehello.conf"));
        ActorRef worker = system.actorOf(Props.create(MyWorker.class), "worker");

        /*final Inbox inbox = Inbox.create(system);
        inbox.watch(worker);
        inbox.send(worker, Msg.WORKING);
        inbox.send(worker, Msg.DONE);
        inbox.send(worker, Msg.CLOSE);

        while (true) {
            Object receive = inbox.receive(Duration.create(1, TimeUnit.SECONDS));
            if (Msg.CLOSE == receive) {
                System.out.println("My worker is closing");
            } else if (receive instanceof Terminated) {
                System.out.println("My worker is dead");
                break;
            } else {
                System.out.println(receive);
            }
        }*/
    }

}
