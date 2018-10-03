package player
import grid.Square
import ship.Ship

class IA(level : Int = 1,
         override val navy: List[Ship], override val shotRecord: List[Square])
  extends Player(navy, shotRecord) {

  override def shoot(square: Square): Unit = ???

  override def myOwnCopy(ships: List[Ship], shotRecord: List[Square]): IA =
    IA(level, ships, shotRecord)
}


object IA {
  def apply(level: Int, ships:
  List[Ship] = Nil, shotRecord: List[Square] = Nil): IA
  = new IA(level, ships, shotRecord)
}
