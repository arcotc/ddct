package ddct

import org.scalatest.FunSuite

class MenuTest extends FunSuite {
  test("Available menu should not be empty") {
    assert(!Menu.availableMenuItems.isEmpty)
  }

  test("Total menu items") {
    assert(Menu.total(List(Menu.cola, Menu.coffee, Menu.cheeseSandwich)) == 3.5)
  }
}
