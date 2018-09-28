package customs.inventory.linking.imports

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.http.request.builder.HttpRequestBuilder
import uk.gov.hmrc.performance.conf.ServicesConfiguration
import java.util.UUID.randomUUID

object ImportRequests extends ServicesConfiguration {

  val authBaseUrl = baseUrlFor("auth")
  val apiBaseUrl = baseUrlFor("inventory-linking-imports")
  val apiSubscriptionFieldsBaseUrl = baseUrlFor("api-subscription-fields")

  val successFulCode = 202
  val validateMovementBody = StringBody(ExampleImports.validImportsValidateMovementPayload.toString())
  val goodsArrivalBody = StringBody(ExampleImports.validImportsGoodsArrivalPayload.toString())

  private def headers(): Map[String, String] = Map(
    "Accept" -> "application/vnd.hmrc.1.0+xml",
    "Content-Type" -> "application/xml; charset=UTF-8",
    "Authorization" -> "Bearer CSP",
    "X-Client-ID" -> "${ClientID}",
    "X-Badge-Identifier" -> "BADGEID123",
    "X-Correlation-ID" -> s"${randomUUID().toString}"
  )

  def validateMovementRequest(): HttpRequestBuilder = http("Validate Movement")
    .post(apiBaseUrl + "/movement-validation": String)
    .headers(headers())
    .body(validateMovementBody)
    .check(status.is(successFulCode))

  def goodsArrivalRequest(): HttpRequestBuilder = http("Goods Arrival")
    .post(apiBaseUrl + "/arrival-notifications": String)
    .headers(headers())
    .body(goodsArrivalBody)
    .check(status.is(successFulCode))

}

