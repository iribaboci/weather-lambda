package dogus.datathon.weatherlambda

import scala.util.parsing.json._
import com.typesafe.scalalogging.StrictLogging
import com.amazonaws.services.lambda.runtime.Context
import dogus.datathon.weatherlambda.logging.ExceptionLogger;

object WeatherLambda extends StrictLogging {
  ExceptionLogger.init()

  def handle(name: String, context: Context): Unit = {
    logger.info(s"Lambda invocation ${context.getFunctionName} - ${context.getFunctionVersion}")
    logger.info(s"Hello $name")
    getWeatherData()
  }

  def getWeatherData() = {
    val lat = "41.0422"
    val lon = "29.0067"

    val attr = "T2M"

    val startDate = "20170101"
    val endDate   = "20180101"
    val url =
      s"https://power.larc.nasa.gov/cgi-bin/v1/DataAccess.py?&request=execute&identifier=SinglePoint&parameters=$attr&startDate=$startDate&endDate=$endDate&userCommunity=SSE&tempAverage=DAILY&outputList=ASCII,CSV&lat=$lat&lon=$lon"

    def get(url: String) = scala.io.Source.fromURL(url).mkString
    try {
      val content = get(url)
      val result = for {
        Some(M(map)) <- List(JSON.parseFull(content))
        M(outputs) = map("outputs")
        S(csv)     = outputs("csv")
      } yield csv
      println(result.toString())
    } catch {
      case ioe: java.io.IOException             => // handle this
      case ste: java.net.SocketTimeoutException => // handle this
    }
  }
}

class CC[T] { def unapply(a: Any): Option[T] = Some(a.asInstanceOf[T]) }

object M extends CC[Map[String, Any]]
object S extends CC[String]
