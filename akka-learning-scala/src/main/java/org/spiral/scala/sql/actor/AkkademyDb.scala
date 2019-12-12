package org.spiral.scala.sql.actor

import akka.actor.Actor
import akka.event.Logging
import org.spiral.scala.sql.model.SetRequest

import scala.collection.mutable.HashMap

/**
 * 消息处理
 *
 * @author dengguoqing
 * @date 2019/12/12
 * @since 1.0 Version
 * @copyright spiral
 */
class AkkademyDb extends Actor {
  val map = new HashMap[String, AnyRef]
  val log = Logging(context.system, this)

  override def receive: Receive = {
    case SetRequest(key, value) => {
      log.info("receive SetRequest - key:{} value:{}", key, value)
      map.put(key, value)
    }
    case o => log.info("received unknown message: {}", o)
  }
}
