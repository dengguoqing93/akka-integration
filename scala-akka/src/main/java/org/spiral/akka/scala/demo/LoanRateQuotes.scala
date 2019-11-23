package org.spiral.akka.scala.demo

import java.util.concurrent.TimeUnit

import akka.actor.SupervisorStrategy.{Escalate, Restart, Resume, Stop}
import akka.actor.{Actor, OneForOneStrategy, SupervisorStrategy}

import scala.concurrent.duration.Duration

/**
  * ${DESCRIPTION}
  *
  * @author dengguoqing
  * @since 2019/5/21
  */
class LoanRateQuotes extends Actor {


  override def supervisorStrategy: SupervisorStrategy = {
    OneForOneStrategy(maxNrOfRetries = 5,
      withinTimeRange = Duration.apply(1, TimeUnit.MINUTES)) {
      case NullPointerException => Restart
      case ArithmeticException => Resume
      case IllegalArgumentException => Stop
      case UnsupportedOperationException => Stop
      case Exception => Escalate
    }
  }

  override def receive: Receive = {
    return null
  }
}
