package org.guoqing.akka.scala.demo

import akka.actor.{ActorRef, ActorSystem, Props}

/**
  * 第一个scala akka代码
  *
  * @author dengguoqing
  * @since 2019/5/21
  */
class Demo {
  val system = ActorSystem("ReactiveEnterprise")

  //创建Actor对象并获取该对象的ActorRef引用
  val processManagersRef: ActorRef = system.actorOf(Props[ProcessManagers], "processManagers")

  /*
  使用actorRef引用向Actor对象发送消息
   */
  processManagersRef ! BrokerForLoan(banks)
}
