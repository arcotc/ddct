package ddct

import org.scalatest.FunSuite

import ddct.Menu._

class MenuTest extends FunSuite {
  test("Available menu should not be empty") {
    assert(!availableMenuItems.isEmpty)
  }

  test("Total menu items") {
    assert(total(List(cola, coffee, cheeseSandwich)) == (cola.itemCost + coffee.itemCost + cheeseSandwich.itemCost))
  }

  test("Hot food ordered") {
    assert(hotFoodOrdered(List(steakSandwich)))
  }

  test("Hot food ordered in multiple item order") {
    assert(hotFoodOrdered(List(cheeseSandwich, steakSandwich, cola)))
  }

  test("Cold food ordered") {
    assert(!hotFoodOrdered(List(cheeseSandwich)))
  }

  test("Only cold food ordered in multiple item order") {
    assert(!hotFoodOrdered(List(cheeseSandwich, cola)))
  }

  test("Only drinks ordered") {
    assert(onlyDrinksOrdered(List(cola)))
  }

  test("Only drinks ordered in multiple item order") {
    assert(onlyDrinksOrdered(List(cola, coffee)))
  }

  test("Food ordered") {
    assert(!onlyDrinksOrdered(List(cheeseSandwich)))
  }

  test("Food ordered in multiple item order") {
    assert(!onlyDrinksOrdered(List(cheeseSandwich, cola)))
  }

  test("Zero tip for drinks only order") {
    assert(tips(List(cola, coffee)) == TIP_ZERO_RATE)
  }

  test("10% tip for purchases including food") {
    assert(tips(List(cheeseSandwich)) == (cheeseSandwich.itemCost * TIP_10PC_MULTIPLIER))
  }

  test("10% tip for purchases including food on multiple item orders") {
    assert(tips(List(cheeseSandwich, cola)) == ((cheeseSandwich.itemCost * TIP_10PC_MULTIPLIER) + (cola.itemCost * TIP_10PC_MULTIPLIER)))
  }

  test("20% tip for purchases including hot food") {
    assert(tips(List(steakSandwich)) == (steakSandwich.itemCost * TIP_20PC_MULTIPLIER))
  }

  test("20% tip for purchases including hot food on multiple item orders") {
    assert(tips(List(steakSandwich, cola)) == ((steakSandwich.itemCost * TIP_20PC_MULTIPLIER) + (cola.itemCost * TIP_20PC_MULTIPLIER)))
  }

  test("Large tip on multiple order restricted to 20") {
    val lotsOfSteaks = List.fill(30)(steakSandwich)
    assert(tips(lotsOfSteaks) == MAXIMUM_TIP)
  }

  test("Total bill for drinks only order") {
    assert(totalBill(List(cola)) == cola.itemCost)
  }

  test("Total bill for drinks only order in multiple item order") {
    assert(totalBill(List(cola, coffee)) == cola.itemCost + coffee.itemCost)
  }

  test("Total bill for cold food including tip") {
    assert(totalBill(List(cheeseSandwich)) == (cheeseSandwich.itemCost + (cheeseSandwich.itemCost * TIP_10PC_MULTIPLIER)))
  }

  test("Total bill for cold food and drink including tip") {
    assert(totalBill(List(cheeseSandwich, cola)) ==
      (
        cheeseSandwich.itemCost + (cheeseSandwich.itemCost * TIP_10PC_MULTIPLIER) +
        cola.itemCost + (cola.itemCost * TIP_10PC_MULTIPLIER)
      )
    )
  }

  test("Total bill for hot food") {
    assert(totalBill(List(steakSandwich)) == (steakSandwich.itemCost + (steakSandwich.itemCost * TIP_20PC_MULTIPLIER)))
  }
}
