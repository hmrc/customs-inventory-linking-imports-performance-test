/*
 * Copyright 2022 HM Revenue & Customs
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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
    "X-Submitter-Identifier" -> s"${Random.alphanumeric.take(17).mkString("").toString}"
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


