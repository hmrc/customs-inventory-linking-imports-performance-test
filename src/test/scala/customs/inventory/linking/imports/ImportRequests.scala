package customs.inventory.linking.imports

import java.util.UUID.randomUUID
import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.http.request.builder.HttpRequestBuilder
import uk.gov.hmrc.performance.conf.ServicesConfiguration
import scala.util.Random

object ImportRequests extends ServicesConfiguration {

  val authBaseUrl = baseUrlFor("auth")
  val apiBaseUrl = baseUrlFor("inventory-linking-imports")
  val successFulCode = 202
  val validateMovementBody = StringBody(ExampleImports.validImportsValidateMovementPayload.toString())
  val goodsArrivalBody = StringBody(ExampleImports.validImportsGoodsArrivalPayload.toString())

  private def inventoryLinkingImportsheaders(): Map[String, String] = Map(
    "Accept" -> "application/vnd.hmrc.1.0+xml",
    "Content-Type" -> "application/xml; charset=UTF-8",
    "Authorization" -> "Bearer CSP",
    "X-Client-ID" -> "${ClientID}",
    "X-Badge-Identifier" -> "BADGEID123",
    "X-Correlation-ID" -> s"${randomUUID().toString}",
    "X-Submitter-Identifier" -> s"${Random.alphanumeric.take(17).mkString("")}"
  )

  def validateMovementRequest(): HttpRequestBuilder = http("Validate Movement")
    .post(apiBaseUrl + "/movement-validation": String)
    .headers(inventoryLinkingImportsheaders())
    .body(validateMovementBody)
    .check(status.is(successFulCode))

  def goodsArrivalRequest(): HttpRequestBuilder = http("Goods Arrival")
    .post(apiBaseUrl + "/arrival-notifications": String)
    .headers(inventoryLinkingImportsheaders - "X-Correlation-ID")
    .body(goodsArrivalBody)
    .check(status.is(successFulCode))
}
