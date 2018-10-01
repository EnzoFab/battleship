package player

import grid.Square
import ship.Ship

case class HPlayer(name: String = "No Name") extends Player {

  override def shoot(square: Square): Unit = ???

  override var ships: List[Ship] = List()
  override var shotRecord: List[Square] = List()
}
