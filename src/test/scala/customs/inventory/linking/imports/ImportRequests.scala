package customs.inventory.linking.imports

import java.util.UUID

import io.gatling.core.Predef._
import io.gatling.core.structure.ChainBuilder
import io.gatling.http.Predef._
import io.gatling.http.request.builder.HttpRequestBuilder
import uk.gov.hmrc.performance.conf.ServicesConfiguration

import scala.util.parsing.json.JSON

object ImportRequests extends ServicesConfiguration {

  val authBaseUrl = baseUrlFor("auth")
  val apiBaseUrl = baseUrlFor("inventory-linking-imports")
  val apiSubscriptionFieldsBaseUrl = baseUrlFor("api-subscription-fields")

  val successFulCode = 202
  val validateMovementBody = StringBody(ExampleImports.validImportsValidateMovementPayload.toString())
  val goodsArrivalBody = StringBody(ExampleImports.validImportsGoodsArrivalPayload.toString())

  val createSubscriptionFieldsBody =ExampleImports.createsubscriptionFieldsBody
  val updateSubscriptionFieldsBody = ExampleImports.updateSubscriptionFieldsBody

  val fieldsId = """"fieldsId":"([^"]+)""""
  def savefieldsId = regex(_ => fieldsId).saveAs("fieldsIds")

  val clientId = """"clientId":"([^"]+)""""
  def saveClientId = regex(_ => clientId).saveAs("clientIds")

  val applicationClientUUID: Iterator[Map[String, String]] = Iterator.continually(Map("clientId" -> UUID.randomUUID().toString))
  def clientIdUUID: ChainBuilder = feed(applicationClientUUID)

  private def headers(): Map[String, String] = Map(
    "Accept" -> "application/vnd.hmrc.1.0+xml",
    "Content-Type" -> "application/xml; charset=UTF-8",
    "Authorization" -> "Bearer CSP",
    "X-Client-ID" -> "${csid}",
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

  def createFields = http("Create subscription fields")
    .put(apiSubscriptionFieldsBaseUrl + "/field/application/${clientId}/context/customs%2Finventory-linking-imports/version/1.0")
    .headers(Map("Content-Type" -> "application/json"))
    .body(StringBody(createSubscriptionFieldsBody))
    .check(savefieldsId)
    .check(saveClientId)
    .check(status.is(201))
    .transformResponse {
      case r =>
        println("client id is -> \n" +  JSON.parseFull(r.body.string).getOrElse(0).asInstanceOf[Map[String, String]].get("clientId").fold("")(_.toString))
        r
    }

  def updateFieldsId = http("Update subscription fields")
    .put(apiSubscriptionFieldsBaseUrl + "/field/application/${clientIds}/context/customs%2Finventory-linking-imports/version/1.0")
    .headers(Map("Content-Type" -> "application/json"))
    .body(StringBody(updateSubscriptionFieldsBody))
    .check(status.is(200))

}

