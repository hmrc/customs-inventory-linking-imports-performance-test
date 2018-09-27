package customs.inventory.linking.imports

import scala.xml.Elem

object ExampleImports {

  val validImportsValidateMovementPayload = {
    <InventoryLinkingImportsValidateMovementResponse xmlns="http://gov.uk/customs/inventoryLinkingImport/v1">
      <entryNumber>string</entryNumber>
      <entryVersionNumber>3</entryVersionNumber>
      <inventoryConsignmentReference>123/-:ABC-a/-:ValidateMovement</inventoryConsignmentReference>
      <irc>str</irc>
    </InventoryLinkingImportsValidateMovementResponse>
  }

  val validImportsGoodsArrivalPayload: Elem = {
    <inventoryLinkingImportsGoodsArrival xmlns="http://gov.uk/customs/inventoryLinkingImport/v1">
      <entryNumber>string</entryNumber>
      <entryVersionNumber>3</entryVersionNumber>
      <inventoryConsignmentReference>123/-:ABC-a/-:GoodsArrival</inventoryConsignmentReference>
      <transportNationality>st</transportNationality>
    </inventoryLinkingImportsGoodsArrival>
  }

}
