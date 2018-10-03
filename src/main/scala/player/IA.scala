package player
import grid.{Shot, Square}
import ship.Ship

import scala.annotation.tailrec

case class IA(level : Int = 1,
         override val navy: List[Ship], override val shotRecord: List[Shot])
  extends Player(navy, shotRecord) {

  override def indentifiant: String = s"IA level $level"


  override def shoot(square: Square): Unit = ???

  override def myOwnCopy(ships: List[Ship], shotRecord: List[Shot]): IA =
    IA(level, ships, shotRecord)


  private def level1 = "TODO" //TODO
  private def level2 = "TODO" //TODO

  private def level3 = "TODO" //TODO


  /**
    * allows to know if the IA has successed its four previous shot or at List on target
    * @return
    */
  private def fourPreviousShot: Boolean = {

    @tailrec
    def fourPreviousShotInt(list: List[Shot], number: Int): Boolean = {
      if(list.isEmpty) true
      else if(number >= 4) {
        list.head.hasTouch || list.head.isNear // has touch or is near
      }else {
        (list.head.hasTouch || list.head.isNear) && fourPreviousShotInt(list.tail, number + 1)
      }
    }

    fourPreviousShotInt(shotRecord, 0)
  }
}


object IA {
  def apply(level: Int, ships:
  List[Ship] = Nil, shotRecord: List[Shot] = Nil): IA
  = new IA(level, ships, shotRecord)
}
