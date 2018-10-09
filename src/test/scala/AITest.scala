import grid.Grid
import org.scalatest.FunSuite
import player.AI

import scala.util.Random

class AITest extends FunSuite{
  test("computeTarget is correctly set") {
    // test that the square create by the method are in the grid
    val aiLevell = AI(1)
    val aiLevel2 = AI(2)
    val aiLevel3 = AI(3)

    val random = Random

    val square = aiLevell.computeTarget(random, aiLevell.level, aiLevell.playerShotRecord)
    val square2 = aiLevel2.computeTarget(random, aiLevel2.level, aiLevel2.playerShotRecord)
    val square3 = aiLevel3.computeTarget(random, aiLevel3.level, aiLevel3.playerShotRecord)
    assert( Grid.isInGrid(square) && Grid.isInGrid(square2) && Grid.isInGrid(square3))
  }
}
