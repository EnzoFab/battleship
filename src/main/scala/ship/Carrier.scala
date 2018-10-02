package ship
import grid.Square

class Carrier(positions: List[Square], shipSize: Int, shipName: String)
  extends Ship(positions, shipSize, shipName) {

  override def copy(positions: List[Square],
                    shipSize: Int, shipName: String): Carrier =
    new Carrier(positions, shipSize, shipName)
}

object Carrier {
  def apply(x: Int, y: Int, orientation: String): Carrier = {
    val l = Ship.createList(x, y, orientation, 5, "Carrier")
    new Carrier(l, 5, "Carrier")
  }
}