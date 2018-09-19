package customs.inventory.linking.imports

import scala.xml.Elem

object ExampleImports {

  val validImportsValidateMovementPayload = {
    <InventoryLinkingImportsValidateMovementResponse xmlns="http://gov.uk/customs/inventoryLinkingImport/v1">
      <entryNumber>string</entryNumber>
      <entryVersionNumber>3</entryVersionNumber>
      <inventoryConsignmentReference>string</inventoryConsignmentReference>
      <irc>str</irc>
    </InventoryLinkingImportsValidateMovementResponse>
  }

  val validImportsGoodsArrivalPayload: Elem = {
    <inventoryLinkingImportsGoodsArrival xmlns="http://gov.uk/customs/inventoryLinkingImport/v1">
      <entryNumber>string</entryNumber>
      <entryVersionNumber>3</entryVersionNumber>
      <inventoryConsignmentReference>string</inventoryConsignmentReference>
      <transportNationality>st</transportNationality>
    </inventoryLinkingImportsGoodsArrival>
  }

  val createsubscriptionFieldsBody: String = {
    """{
      "fields": {
        "callbackUrl": "http://localhost:9826/customs-notifications-receiver-stub/pushnotifications",
        "securityToken": "Basic qwerty12345qwerty12345"
      }
    }"""
  }

  val updateSubscriptionFieldsBody: String = {
    """{
      "fields": {
        "callbackUrl": "http://localhost:9826/customs-notifications-receiver-stub/pushnotifications",
        "securityToken": "${fieldsIds}"
      }
    }"""
  }

}
