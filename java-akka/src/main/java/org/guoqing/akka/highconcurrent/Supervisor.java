package org.guoqing.akka.highconcurrent;

import akka.actor.OneForOneStrategy;
import akka.actor.Props;
import akka.actor.SupervisorStrategy;
import akka.actor.UntypedAbstractActor;
import scala.concurrent.duration.Duration;

import java.util.concurrent.TimeUnit;

/**
 * 父Actor,作为所有子Actor的监督者
 *
 * @author dengguoqing
 * @date 2019/7/7
 */
public class Supervisor extends UntypedAbstractActor {

    private static SupervisorStrategy strategy = new OneForOneStrategy(3,
                                                                       Duration.create(1,
                                                                                       TimeUnit.MINUTES),
                                                                       param -> {
                                                                           if (param instanceof ArithmeticException) {
                                                                               System.out.println(
                                                                                       "meet ArithmeticException,just resume");
                                                                               return SupervisorStrategy.resume();
                                                                           } else if (param instanceof NullPointerException) {
                                                                               System.out.println(
                                                                                       "meet NullPointException,restart");
                                                                               return SupervisorStrategy.restart();
                                                                           } else if (param instanceof IllegalArgumentException) {
                                                                               return SupervisorStrategy.stop();
                                                                           } else {
                                                                               return SupervisorStrategy.escalate();
                                                                           }
                                                                       });

    @Override
    public SupervisorStrategy supervisorStrategy() {
        return strategy;
    }

    @Override
    public void onReceive(Object message) throws Throwable {
        if (message instanceof Props) {
            getContext().actorOf((Props) message, "restartActor");
        }else {
            unhandled(message);
        }
    }


}
