package ship
import grid.Square

class SubMarine(x: Int, y: Int, orientation: String) extends Ship {
  override val shipSize: Int = 3
  override val shipName: String = "SubMarine"
  override var positions: List[Square] = create(x, y, orientation, 0)
}
