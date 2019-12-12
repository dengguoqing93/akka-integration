package org.spiral.scala.sql.actor

import akka.actor.Actor
import akka.event.Logging

/**
 * ${DESCRIPTION}
 *
 * @author dengguoqing
 * @date 2019/12/12
 * @since 1.0 Version
 * @copyright spiral
 */
class StringSaveActor extends Actor {
  var message = ""
  var log = Logging(context.system, this)

  override def receive: Receive = {
    case s: String => {
      log.info("发送的消息为:{}", s)
      message = s
    }
    case other => log.info("发送的消息非string:{}", other)
  }
}
