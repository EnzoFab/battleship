package ship
import grid.Square

class Destroyer(x: Int, y: Int, orientation: String) extends Ship {
  override val shipSize: Int = 2
  override val shipName: String = "Destroyer"
  override var positions: List[Square] = create(x, y, orientation, 0)

}
