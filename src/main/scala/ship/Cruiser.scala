package ship
import grid.Square

class Cruiser(positions: List[Square], shipSize: Int, shipName: String)
  extends Ship(positions, shipSize, shipName){

  override def copy(positions: List[Square],
                    shipSize: Int, shipName: String): Cruiser =
    new Cruiser(positions, shipSize, shipName)
}

object Cruiser {
  def apply(x: Int, y: Int, orientation: String): Cruiser = {
    val l = Ship.createList(x, y, orientation, 3, "Cruiser")
    new Cruiser(l, 3, "Cruiser")
  }
}