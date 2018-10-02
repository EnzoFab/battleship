package player

import grid.Square
import ship.Ship

/**
  * abstract
  */
abstract class Player(val ships: List[Ship], val shotRecord: List[Square]) {
  def shoot(square: Square)

  /**
    * custom copy method
     * @param ships
    * @param shotRecord
    * @return
    */
  def myOwnCopy(ships: List[Ship] = ships, shotRecord: List[Square] = shotRecord): Player
}
