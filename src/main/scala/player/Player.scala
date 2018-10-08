package player

import grid.{Shot, Square}
import ship.Ship

/**
  * abstract
  */
abstract class Player(val navy: List[Ship], val playerShotRecord: List[Shot],
                      val opponentShotRecord: List[Shot], val playerScore: Int = 0) {
  def identifier: String

  /**
    * custom copy method
     * @param navy
    * @param playerShotRecord
    * @return
    */
  def myOwnCopy(navy: List[Ship] = navy,
                playerShotRecord: List[Shot] = playerShotRecord,
                opponentShotRecord: List[Shot] = opponentShotRecord,
                playerScore: Int = playerScore): Player

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
      else if(!ships.head.isSunk & ships.head.inSight(square)){
        // if the ship is sinked yet and is in Sight
        true
      }
      else inSightInt(ships.tail, square)
    }

    inSightInt(navy, square)
  }

  /**
    * Update all the navy of the player.
    * If a ship of the navy contains a square equals to the square given in parameter
    * and if the square isn't touched yet then set this square to touched
    * @param square
    * @return a new navy with all the Ship updated
    */
  def updateTouchedShip (square: Square): List[Ship]= {

    def int (list: List[Ship], square: Square): List[Ship]=  {
      if(list.isEmpty) Nil
      else list.head.copy(positions = list.head.updateShip(square)) :: int(list.tail, square)
    }

    int(navy, square)
  }

  /**
    * the game is over when the player has all his ship sinked
    * @return
    */
  def gameOver: Boolean = {

    def gameOverInt(list: List[Ship]): Boolean = {
      if(list.isEmpty) true
      else {
        list.head.isSunk && gameOverInt(list.tail)
      }
    }

    gameOverInt(navy)
  }

  /**
    * return a new player with the same name and score
    * but with the navy, shot record and opponent record set to Nil
    * @return a new player
    */
  def resetGame: Player = myOwnCopy(navy = Nil, playerShotRecord = Nil, opponentShotRecord = Nil)



  override def toString: String = identifier + ": " + playerScore
}
