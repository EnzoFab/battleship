package player
import grid.Square
import ship.Ship

case class IA(level : Int = 1) extends Player{
  override var ships: List[Ship] = List()

  override def shoot(square: Square): Unit = ???

  override var shotRecord: List[Square] = List()
}
