package ship
import grid.Square

case class Carrier(x: Int, y: Int, orientation: String) extends Ship {
  override var positions: List[Square] = create(x, y, orientation, 0)
  override val shipSize: Int = 5
  override val shipName: String = "Carrier"
}
