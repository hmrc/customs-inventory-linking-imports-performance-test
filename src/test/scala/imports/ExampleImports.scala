package customs.declaration

object ExampleImports {

  val validImportsValidateMovementPayload = {
    <InventoryLinkingImportsValidateMovementResponse xmlns="http://gov.uk/customs/inventoryLinkingImport/v1">
      <messageCode>str</messageCode>
      <dateOfEntry>2008-09-29T02:49:45</dateOfEntry>
      <entryNumber>string</entryNumber>
      <entryVersionNumber>3</entryVersionNumber>
      <inventoryConsignmentReference>string</inventoryConsignmentReference>
      <irc>str</irc>
    </InventoryLinkingImportsValidateMovementResponse>
  }

  val validImportsGoodsArrivalPayload = {
    <inventoryLinkingImportsGoodsArrival xmlns="http://gov.uk/customs/inventoryLinkingImport/v1">
      <messageCode>str</messageCode>
      <dateOfEntry>2008-09-29T02:49:45</dateOfEntry>
      <entryNumber>string</entryNumber>
      <entryVersionNumber>3</entryVersionNumber>
      <goodsArrivalDeclaration>Y</goodsArrivalDeclaration>
      <inventoryConsignmentReference>string</inventoryConsignmentReference>
      <irc>str</irc>
      <transportNationality>st</transportNationality>
    </inventoryLinkingImportsGoodsArrival>
  }


}
