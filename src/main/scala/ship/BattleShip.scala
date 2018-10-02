package ship
import grid.Square

class BattleShip(positions: List[Square], shipSize: Int, shipName: String)
  extends Ship(positions, shipSize, shipName) {

  override def copy(positions: List[Square],
                    shipSize: Int, shipName: String): BattleShip =
    new BattleShip(positions, shipSize, shipName)

}

object BattleShip {
  def apply(x: Int, y: Int, orientation: String): BattleShip = {
    val l = Ship.createList(x, y, orientation, 4, "BattleShip")
    new BattleShip(l, 4, "BattleShip")
  }
}


