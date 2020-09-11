# Logging & Metrics

## Logging

### Backend

We use `logback-logstash-encoder` for structured logging.
Every log message should extend `com.scout24.weatherlambda.logging.LogMessage`.
The case class fields will be written as log message fields.
In addition to that, you need to override the `message` method to provide a human-friendly log string.
When you log an instance of `LogMessage` you need to have `com.scout24.weatherlambda.logging._`
imported in order to bring the implicit conversions to the scope. 

### Forwarding logs to Kibana

If you want to have logs in Kibana, please follow this: [Cloudwatch Logs to Kinesis)](https://github.com/Scout24/cloudwatchlogs-to-kinesis)
Depending on where the Lambda is deployed, you need to setup a cross-account subscription as outlined in the docs.

