package ship
import grid.Square


class Destroyer(positions: List[Square], shipSize: Int, shipName: String)
  extends Ship(positions, shipSize, shipName){

  override def copy(positions: List[Square],
                    shipSize: Int, shipName: String): Destroyer =
    new Destroyer(positions, shipSize, shipName)
}

object Destroyer {
  def apply(x: Char, y: Int, orientation: String): BattleShip = {
    val l = Ship.createList(x, y, orientation, 2, "Destroyer")
    new BattleShip(l, 2, "Destroyer")
  }
}
