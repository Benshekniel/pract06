object InventorySystem {
  def main(args: Array[String]): Unit = {
    // Sample inventories
    val inventory1 = Map(
      101 -> ("ProductA", 10, 15.5),
      102 -> ("ProductB", 5, 25.0),
      103 -> ("ProductC", 20, 10.0)
    )

    val inventory2 = Map(
      101 -> ("ProductA", 5, 17.0),
      104 -> ("ProductD", 8, 20.0)
    )

    // I. Retrieve all product names from inventory1
    val productNames = inventory1.values.map(_._1).toList
    println("Product names in inventory1: " + productNames)

    // II. Calculate the total value of all products in inventory1
    val totalValue = inventory1.values.map { case (_, quantity, price) => quantity * price }.sum
    println("Total value of all products in inventory1: " + totalValue)

    // III. Check if inventory1 is empty
    val isEmpty = inventory1.isEmpty
    println("Is inventory1 empty? " + isEmpty)

    // IV. Merge inventory1 and inventory2, updating quantities and retaining the highest price
    val mergedInventory = (inventory1.keySet ++ inventory2.keySet).map { key =>
      val item1 = inventory1.getOrElse(key, ("", 0, 0.0))
      val item2 = inventory2.getOrElse(key, ("", 0, 0.0))
      val newName = if (item1._1.nonEmpty) item1._1 else item2._1
      val newQuantity = item1._2 + item2._2
      val newPrice = Math.max(item1._3, item2._3)
      key -> (newName, newQuantity, newPrice)
    }.toMap
    println("Merged inventory: " + mergedInventory)

    // V. Check if a product with a specific ID (e.g., 102) exists in inventory1 and print its details
    val productIdToCheck = 102
    if (inventory1.contains(productIdToCheck)) {
      println(s"Product with ID $productIdToCheck: " + inventory1(productIdToCheck))
    } else {
      println(s"Product with ID $productIdToCheck does not exist in inventory1")
    }
  }
}
