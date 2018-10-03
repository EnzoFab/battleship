package player

import grid.Square
import ship.Ship

/**
  * abstract
  */
abstract class Player(val navy: List[Ship], val shotRecord: List[Square]) {
  def shoot(square: Square)

  /**
    * custom copy method
     * @param ships
    * @param shotRecord
    * @return
    */
  def myOwnCopy(ships: List[Ship] = navy, shotRecord: List[Square] = shotRecord): Player

  /**
    * Check if the ship given in parameter overlap a ship of the navy
    * @param ship
    * @return
    */
  def overLaps(ship: Ship): Boolean = {

    /**
      * recursive function that go through
      * a list to find out if a ship overlaps the one given in parameter
      * @param list
      * @return
      */
    def overLapsInt(list: List[Ship], ship: Ship): Boolean = {
      if (list.isEmpty) false
      else list.head.overLaps(ship) || overLapsInt(list.tail, ship)
    }
    overLapsInt(navy, ship)
  }

  /**
    * Check if there is a ship at the given suqare
    * @param square
    * @return
    */
  def hasTouched(square: Square): Option[Square] = {

    def hasTouchedInt(list: List[Ship], square: Square): Option[Square] = {
      if (list.isEmpty) None
      else if (list.head.isTouched(square).isEmpty) {
        // means that this ship hasn't been touch yet
        hasTouchedInt(list.tail, square)
      }
      else list.head.isTouched(square)
    }

    hasTouchedInt(navy, square)
  }

  /**
    * Check if the shot is closed to the boat
    * @param square
    * @return
    */
  def inSight(square: Square): Boolean = {

    def inSightInt(ships: List[Ship], square: Square): Boolean = {
      if (ships.isEmpty) false
      else if(!ships.head.isSinked & ships.head.inSight(square)){
        // if the ship is sinked yet and is in Sight
        true
      }
      else inSightInt(ships.tail, square)
    }

    inSightInt(navy, square)
  }

  /**
    * the game is over when the player has all his ship sinked
    * @return
    */
  def gameOver: Boolean = {

    def gameOverInt(list: List[Ship]): Boolean = {
      if(list.isEmpty) true
      else {
        list.head.isSinked && gameOverInt(list.tail)
      }
    }

    gameOverInt(navy)
  }
}
