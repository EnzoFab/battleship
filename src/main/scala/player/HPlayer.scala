package player

import grid.{Shot, Square}
import ship.Ship

case class HPlayer(name: String = "No Name",
              override val navy: List[Ship], override val shotRecord: List[Shot])
extends Player(navy, shotRecord) {

  override def indentifiant: String = name


  override def shoot(square: Square): Unit = ???

  override def myOwnCopy(ships: List[Ship], shotRecord: List[Shot]): HPlayer =
    HPlayer(name, ships, shotRecord)


}



object HPlayer{
  def apply(name: String, ships:
  List[Ship] = Nil, shotRecord: List[Shot] = Nil): HPlayer
  = new HPlayer(name, ships, shotRecord)
}