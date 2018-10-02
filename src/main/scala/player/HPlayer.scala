package player

import grid.Square
import ship.Ship

class HPlayer(name: String = "No Name",
                   override val ships: List[Ship], override val shotRecord: List[Square])
extends Player(ships, shotRecord) {

  override def shoot(square: Square): Unit = ???

  override def myOwnCopy(ships: List[Ship], shotRecord: List[Square]): HPlayer =
    HPlayer(name, ships, shotRecord)


}



object HPlayer{
  def apply(name: String, ships:
  List[Ship] = Nil, shotRecord: List[Square] = Nil): HPlayer
  = HPlayer(name, ships, shotRecord)
}