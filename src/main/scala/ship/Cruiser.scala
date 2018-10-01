package ship
import grid.Square

case class Cruiser(x: Int, y: Int, orientation: String) extends Ship {
  override var positions: List[Square] = create(x, y, orientation, 0)
  override val shipSize: Int = 3
  override val shipName: String = "Cruiser"
}
