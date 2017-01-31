package ddct

trait Menu {
  val cola = MenuItem("Cola", "Cold", 0.5)
  val coffee = MenuItem("Coffee", "Hot", 1)
  val cheeseSandwich = MenuItem("Cheese Sandwich", "Cold", 2)
  val steakSandwich = MenuItem("Steak Sandwich", "Hot", 4.5)

  def availableMenuItems: List[MenuItem]

  def total(itemsChosen: List[MenuItem]): Double = {
    itemsChosen.map(_.itemCost).sum
  }
}

object Menu extends Menu {
  def availableMenuItems = List(cola, coffee, cheeseSandwich, steakSandwich)
}