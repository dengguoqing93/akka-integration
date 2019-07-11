package org.guoqing.akka.highconcurrent;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import com.typesafe.config.ConfigFactory;

/**
 * @author guoqing
 * @since ： 2019/7/7 16:44
 */
public class HelloMainSimple {
    public static void main(String[] args) {
        ActorSystem system = ActorSystem.create("Hello", ConfigFactory.load(
                "samplehello.conf"));
        ActorRef world = system.actorOf(Props.create(HelloWorld.class), "helloWorld");
        System.out.println("HelloWorld Actor Path:" + world.path());

    }
}
