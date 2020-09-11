package com.scout24.weatherlambda

import com.scout24.weatherlambda.logging.ExceptionLogger
import com.typesafe.scalalogging.StrictLogging

import com.amazonaws.services.lambda.runtime.Context;

object WeatherLambda extends StrictLogging {
  ExceptionLogger.init()

  def handle(name: String, context: Context): Unit = {
    logger.info(s"Lambda invocation ${context.getFunctionName} - ${context.getFunctionVersion}")
    logger.info(s"Hello $name")
  }
}
