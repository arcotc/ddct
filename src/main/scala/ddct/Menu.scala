package ddct

trait Menu {
  val MAXIMUM_TIP = 20
  val TIP_20PC_MULTIPLIER = 0.2
  val TIP_10PC_MULTIPLIER = 0.1
  val TIP_ZERO_RATE = 0
  val cola = MenuItem("Cola", true, false, 0.5)
  val coffee = MenuItem("Coffee", true, true, 1)
  val cheeseSandwich = MenuItem("Cheese Sandwich", false, false, 2)
  val steakSandwich = MenuItem("Steak Sandwich", false, true, 4.5)

  def availableMenuItems: List[MenuItem]

  def total(itemsChosen: List[MenuItem]): Double = {
    itemsChosen.map(_.itemCost).sum
  }

  def tips(itemsChosen: List[MenuItem]): Double = {
    val tipRate = (onlyDrinksOrdered(itemsChosen), hotFoodOrdered(itemsChosen)) match {
      case (true, _) => TIP_ZERO_RATE
      case (_, true) => TIP_20PC_MULTIPLIER
      case (_, _) => TIP_10PC_MULTIPLIER
    }

    val tip = (total(itemsChosen) * tipRate)
    (tip > MAXIMUM_TIP) match {
      case true => MAXIMUM_TIP
      case false => tip
    }
  }

  def totalBill(itemsChosen: List[MenuItem]): Double = {
    val totalCost = total(itemsChosen) + tips(itemsChosen)
    val roundedBill = BigDecimal(totalCost).setScale(2, BigDecimal.RoundingMode.HALF_UP).toDouble
    roundedBill
  }

  def hotFoodOrdered(itemsChosen: List[MenuItem]): Boolean = {
    itemsChosen.filter(item => item.isHot && !item.isDrink).size > 0
  }

  def onlyDrinksOrdered(itemsChosen: List[MenuItem]): Boolean = {
    itemsChosen.filter(_.isDrink).size == itemsChosen.size
  }
}

object Menu extends Menu {
  def availableMenuItems = List(cola, coffee, cheeseSandwich, steakSandwich)
}