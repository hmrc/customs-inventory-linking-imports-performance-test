package customs.imports

import customs.declaration.ExampleImports
import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.http.request.builder.HttpRequestBuilder
import uk.gov.hmrc.performance.conf.ServicesConfiguration

object ImportRequests extends ServicesConfiguration {

  val authBaseUrl = baseUrlFor("auth")
  val apiBaseUrl = baseUrlFor("customs-inventory-linking-imports")

  val successFulCode = 202
  val validateMovementBody = StringBody(ExampleImports.validImportsValidateMovementPayload.toString())
  val goodsArrivalBody = StringBody(ExampleImports.validImportsGoodsArrivalPayload.toString())

  private def headers(): Map[String, String] = Map(
    "Accept" -> "application/vnd.hmrc.1.0+xml",
    "Content-Type" -> "application/xml; charset=UTF-8",
    "Authorization" -> "Bearer CSP",
    "X-Client-ID" -> "1323e64f-892e-4f46-837e-0e8975d3151e",
    "X-Badge-Identifier" -> "BADGEID123"
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

