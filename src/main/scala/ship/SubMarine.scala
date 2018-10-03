package ship
import grid.Square

class SubMarine(positions: List[Square], shipSize: Int, shipName: String)
  extends Ship(positions, shipSize, shipName) {

  override def copy(positions: List[Square],
                    shipSize: Int, shipName: String): SubMarine =
    new SubMarine(positions, shipSize, shipName)
}

object SubMarine {
  def apply(x: Char, y: Int, orientation: String): SubMarine = {
    val l = Ship.createList(x, y, orientation, 3, "SubMarine")
    new SubMarine(l, 3, "SubMarine")
  }
}