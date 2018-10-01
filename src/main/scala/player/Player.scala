package player

import grid.Square
import ship.Ship

/**
  * abstract
  */
abstract class Player {
  var ships: List[Ship]
  var shotRecord: List[Square]
  def shoot(square: Square)
}
