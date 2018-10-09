import grid.{Grid, Square}
import org.scalatest.FunSuite
import ship._

class GridTest extends FunSuite{
  test("IsConform is correctly set") {
    val ship: Ship = Destroyer('A',0, "R")
      // conform
    val ship2: Ship = Destroyer('A', 0, "L")
      //exceed on left
    val ship3: Ship = Destroyer('A', 0, "T")
      // exceed on top coordY will be equal to -1
    val ship4: Ship = Destroyer('J', 0, "R")
      // exceed on right coordX will be equal to L
    val ship5: Ship = Destroyer('J', 9, "B")
      // exceed on bottom coordY will be equal to 10

    assert(Grid.isConform(ship) && !Grid.isConform(ship2)
      && !Grid.isConform(ship3) && !Grid.isConform(ship4) && !Grid.isConform(ship5))
  }

  test("IsInGrid is correctly set") {
    val square = Square('A', 7)
    val square2 = Square('J', 7)
    val square3 = Square('a', 7)
    val square4 = Square('A', 10)
    val square5 = Square('A', -5)

    assert(Grid.isInGrid(square) && Grid.isInGrid(square2)
    && !Grid.isInGrid(square3) && !Grid.isInGrid(square4)
    && !Grid.isInGrid(square5))
  }

  test("createGridList is correctly set") {
    val grid: List[Square] = Grid.createGridInList('A', 0, 'J', 9)
    assert(grid.lengthCompare(100) == 0)
  }
}
