package dogus.datathon.weatherlambda.logging

import java.lang.Thread.UncaughtExceptionHandler
import com.typesafe.scalalogging.StrictLogging

object ExceptionLogger extends UncaughtExceptionHandler with StrictLogging {
  private val defaultHandler = Thread.getDefaultUncaughtExceptionHandler

  def init(): Unit = {
    Thread.setDefaultUncaughtExceptionHandler(this)

    logger.info("Installed uncaught exception handler")
  }

  override def uncaughtException(t: Thread, e: Throwable): Unit = {
    logger.error("Unchecked exception", e)
    defaultHandler.uncaughtException(t, e)
  }
}
