package player
import grid.Square
import ship.Ship

case class IA(level : Int = 1,
              override val ships: List[Ship], override val shotRecord: List[Square])
  extends Player(ships, shotRecord) {

  override def shoot(square: Square): Unit = ???

  override def myOwnCopy(ships: List[Ship], shotRecord: List[Square]): IA = ???
}
