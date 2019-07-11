package org.guoqing.akka.highconcurrent;

import akka.actor.*;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import com.typesafe.config.ConfigFactory;

/**
 * 监听器Actor
 *
 * @author dengguoqing
 * @date 2019/7/7
 */
public class WatchActor extends UntypedAbstractActor {
    private final LoggingAdapter loggingAdapter = Logging.getLogger(getContext().system(),
                                                                    this);

    public WatchActor(ActorRef ref) {
        getContext().watch(ref);
    }

    @Override
    public void onReceive(Object message) throws Throwable {
        if (message instanceof Terminated) {
            System.out.println(String.format("%s has terminated,shuting down system",
                                             ((Terminated) message).getActor().path()));
        } else {
            unhandled(message);
        }
    }

    public static void main(String[] args) {
        ActorSystem system = ActorSystem.create("deadWatch",
                                                ConfigFactory.load("samplehello.conf"));
        ActorRef worker = system.actorOf(Props.create(MyWorker.class), "worker");
        system.actorOf(Props.create(WatchActor.class, worker), "watcher");
        worker.tell(MyWorker.Msg.WORKING, ActorRef.noSender());
        worker.tell(MyWorker.Msg.DONE, ActorRef.noSender());
        worker.tell(PoisonPill.getInstance(), ActorRef.noSender());
    }

}
