package ship
import grid.Square

case class BattleShip(x: Int, y: Int, orientation: String) extends Ship {
  override val shipSize: Int = 4
  override val shipName: String = "BattleShip"
  override var positions: List[Square] = create(x, y, orientation, 0)
}
