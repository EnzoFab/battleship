package player

import grid.Square
import ship.Ship

case class HPlayer(name: String = "No Name",
                   override val ships: List[Ship], override val shotRecord: List[Square])
extends Player(ships, shotRecord) {

  override def shoot(square: Square): Unit = ???

  override def myOwnCopy(ships: List[Ship], shotRecord: List[Square]): HPlayer = ???
}