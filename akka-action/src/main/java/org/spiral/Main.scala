package org.spiral

import akka.actor.ActorSystem
import akka.stream.ActorMaterializer
import akka.util.Timeout
import com.typesafe.config.{Config, ConfigFactory}

import scala.concurrent.duration.{Duration, FiniteDuration}

/**
 * 启动类
 *
 * @author dengguoqing
 * @since 2019/11/23
 */
object Main extends App with RequestTimeOut {

  val config = ConfigFactory.load()
  val host = config.getString("http.host")
  val port = config.getInt("http.port")

  implicit val system = ActorSystem()
  implicit val ec = system.dispatcher


  implicit val materializer = ActorMaterializer()


}

trait RequestTimeOut {
  def requestTimeOut(config: Config): Timeout = {
    val t = config.getString("spray.can.server.request-timeout")
    val d = Duration(t)
    FiniteDuration(d.length, d.unit)
  }
}
