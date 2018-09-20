package customs.inventory.linking.imports

import customs.inventory.linking.imports.ImportRequests._
import uk.gov.hmrc.performance.simulation.PerformanceTestRunner

class ImportsSimulation extends PerformanceTestRunner {

  setup("submit-validate-movement", "Send validate movement request") withRequests validateMovementRequest

  setup("submit-goods-arrival", "Send goods arrival request") withRequests goodsArrivalRequest

  runSimulation()
}
