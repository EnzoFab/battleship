package player

import grid.Square
import ship.Ship

/**
  * abstract
  */
abstract class Player(val ships: List[Ship], val shotRecord: List[Square]) {
  def shoot(square: Square)

  def myOwnCopy(ships: List[Ship], shotRecord: List[Square]): Player
}
