package dogus.datathon.weatherlambda

import com.typesafe.scalalogging.Logger

import scala.language.implicitConversions

package object logging {
  implicit def asStructuredLogger(logger: Logger): StructuredLogger = new StructuredLogger(logger)
}
