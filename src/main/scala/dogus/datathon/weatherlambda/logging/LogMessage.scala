package dogus.datathon.weatherlambda.logging

import io.circe.Encoder
import io.circe.generic.encoding.DerivedAsObjectEncoder
import io.circe.generic.semiauto._
import shapeless.Lazy

trait LogMessage extends Product {
  def message: String
}

object LogMessage {
  final case class LifecycleEvent(event: String) extends LogMessage {
    def message = s"Application lifecycle event: $event"
  }

  implicit def logMessageObjectDecoder[A <: LogMessage](implicit
      encode: Lazy[DerivedAsObjectEncoder[A]]
  ): Encoder.AsObject[A] = deriveEncoder[A]
}
