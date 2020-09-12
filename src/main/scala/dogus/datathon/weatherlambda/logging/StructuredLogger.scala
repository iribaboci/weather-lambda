package dogus.datathon.weatherlambda.logging

import com.fasterxml.jackson.core.JsonGenerator
import com.typesafe.scalalogging.Logger
import io.circe._
import io.circe.syntax._
import net.logstash.logback.marker.{LogstashMarker, MapEntriesAppendingMarker}

object StructuredLogger {
  private def asMarker[T: Encoder.AsObject](message: T): LogstashMarker =
    new LogstashMarker(MapEntriesAppendingMarker.MARKER_NAME) {
      override def writeTo(generator: JsonGenerator): Unit = {
        generator.writeObjectField("type", message.getClass.getSimpleName)
        message.asJsonObject.toIterable.foreach {
          case ("message" | "type", _) => ()
          case (k, v) =>
            generator.writeFieldName(k)
            generator.writeRawValue(Printer.noSpaces.print(v))
        }
      }
    }
}

class StructuredLogger(logger: Logger) {
  import StructuredLogger._
  def error[T <: LogMessage: Encoder.AsObject](msg: T): Unit =
    logger.whenErrorEnabled(logger.underlying.error(asMarker(msg), msg.message))
  def error[T <: LogMessage: Encoder.AsObject](msg: T, t: Throwable): Unit =
    logger.whenErrorEnabled(logger.underlying.error(asMarker(msg), msg.message, t))
  def warn[T <: LogMessage: Encoder.AsObject](msg: T): Unit =
    logger.whenWarnEnabled(logger.underlying.warn(asMarker(msg), msg.message))
  def warn[T <: LogMessage: Encoder.AsObject](msg: T, t: Throwable): Unit =
    logger.whenWarnEnabled(logger.underlying.warn(asMarker(msg), msg.message, t))
  def info[T <: LogMessage: Encoder.AsObject](msg: T): Unit =
    logger.whenInfoEnabled(logger.underlying.info(asMarker(msg), msg.message))
  def info[T <: LogMessage: Encoder.AsObject](msg: T, t: Throwable): Unit =
    logger.whenInfoEnabled(logger.underlying.info(asMarker(msg), msg.message, t))
  def debug[T <: LogMessage: Encoder.AsObject](msg: T): Unit =
    logger.whenDebugEnabled(logger.underlying.debug(asMarker(msg), msg.message))
  def debug[T <: LogMessage: Encoder.AsObject](msg: T, t: Throwable): Unit =
    logger.whenDebugEnabled(logger.underlying.debug(asMarker(msg), msg.message, t))
  def trace[T <: LogMessage: Encoder.AsObject](msg: T): Unit =
    logger.whenTraceEnabled(logger.underlying.trace(asMarker(msg), msg.message))
  def trace[T <: LogMessage: Encoder.AsObject](msg: T, t: Throwable): Unit =
    logger.whenTraceEnabled(logger.underlying.trace(asMarker(msg), msg.message, t))
}
