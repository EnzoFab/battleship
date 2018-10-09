import grid.Square
import org.scalatest.FunSuite
import ship.{Destroyer, Ship}

class ShipTest extends FunSuite{
  test("isSunk is set correctly"){
   val ship = Destroyer('A', 0, "R")
    val ship1 = ship.copy(
      positions = List(Square('A',0, isTouched = true), Square('A',1, isTouched = true))
    ) // create a copy of the ship every postition set to true

    assert(!ship.isSunk && ship1.isSunk)
  }
  test("overLaps is set correctly") {
    val ship = Destroyer('A', 0, "R")
    val ship2 = Destroyer('B', 0, "R")
    val ship3 = Destroyer('D', 0, "R")
    assert(ship.overLaps(ship2) && ! ship.overLaps(ship3))
  }
  test("isTouched is set correctly"){
    val ship = Destroyer('A', 0, "R")
      // Destroyer => Ship size 2 : [A, 0], [B, 0]
    val square = Square('A', 0)
    val square2 = Square('A', 8)
    assert(!ship.isTouched(square).isEmpty && ship.isTouched(square2).isEmpty)
  }

  test("inSight is set correctly") {
    val ship = Destroyer('A', 0, "R")
    // Destroyer => Ship size 2 : [A, 0], [B, 0]
    val square = Square('A', 8)
    val square2 = Square('A', 1)
    assert(!ship.inSight(square) && ship.inSight(square2))
  }

  test("updateShip is set correctly") {
    var ship = Destroyer('A', 0, "R")
    // Destroyer => Ship size 2 : [A, 0], [B, 0]
    val square = Square('A', 0)

    val list: List[Square] = ship.positions
    val list2: List[Square] = ship.updateShip(square)

    assert(!list.head.isTouched && !list(1).isTouched &&
      (list2.head.isTouched || list2(1).isTouched))
  }

  test("Is set correctly"){
    val ship: Ship = Destroyer('A', 0, "B")
    //starting from [A, 0] oriented on bottom
    // postion should be [A, 0], [A, 1]
    val sq = Square('A', 0)
    val sq2 = Square('A', 1)
    val sq3 = Square('C', 3) // shoudn't be defined

    assert(ship.isTouched(sq).isDefined && ship.isTouched(sq2).isDefined &&
      ship.isTouched(sq3).isEmpty)

  }

  test("Ship creation works as it should"){
    val ship: Ship = Destroyer('A', 0, "B")

    assert(ship.positions.nonEmpty)
  }
}
