package org.spiral.akka.java.demo;

import akka.actor.AbstractActor;
import akka.actor.OneForOneStrategy;
import akka.actor.SupervisorStrategy;
import akka.japi.Function;
import scala.Tuple2;
import scala.collection.Iterable;
import scala.collection.mutable.ArraySeq;
import scala.concurrent.duration.Duration;

import java.util.concurrent.TimeUnit;

/**
 * 单项单款利率报价实例
 *
 * @author dengguoqing
 * @date 2019/5/21
 */
public class LoanRateQuotes extends AbstractActor {
    @Override
    public Receive createReceive() {
        return null;
    }

    @Override
    public SupervisorStrategy supervisorStrategy() {
        Iterable<Tuple2<Exception, SupervisorStrategy.Directive>> list = new ArraySeq<>(
                10);
        Function<Throwable, SupervisorStrategy.Directive> function = (e) -> {
            if (e instanceof Exception) {
                return SupervisorStrategy.resume();
            }
            return null;
        };
        SupervisorStrategy.makeDecider(function);
        return new OneForOneStrategy(5, Duration.create(1,
                                                        TimeUnit.MINUTES),
                                     true,
                                     SupervisorStrategy.defaultDecider());
    }
}
