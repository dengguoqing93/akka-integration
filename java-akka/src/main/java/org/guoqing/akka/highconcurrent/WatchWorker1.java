package org.guoqing.akka.highconcurrent;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.Terminated;
import akka.actor.UntypedAbstractActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import akka.routing.ActorRefRoutee;
import akka.routing.RoundRobinRoutingLogic;
import akka.routing.Routee;
import akka.routing.Router;

import java.util.ArrayList;
import java.util.List;

/**
 * 消息路由的使用案例
 *
 * @author dengguoqing
 * @date 2019/7/9
 */
public class WatchWorker1 extends UntypedAbstractActor {

    private final LoggingAdapter log = Logging.getLogger(getContext().system(), this);

    public Router router;

    {
        List<Routee> routees = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            ActorRef worker = getContext().actorOf(Props.create(MyWorker.class),
                                                   "Worker_" + i);
            getContext().watch(worker);
            routees.add(new ActorRefRoutee(worker));
        }
        router = new Router(new RoundRobinRoutingLogic(), routees);
    }


    @Override
    public void onReceive(Object message) throws Throwable {
        if (message instanceof MyWorker.Msg) {
            router.route(message, getSender());
        } else if (message instanceof Terminated) {
            router = router.removeRoutee(((Terminated) message).actor());
            System.out.println(
                    ((Terminated) message).actor().path() + " is closed,routees =" + router.routees().size());
            if (router.routees().size() == 0) {
                System.out.println("CLose system");

                getContext().system().terminate();
            }
        } else {
            unhandled(message);
        }
    }
}
