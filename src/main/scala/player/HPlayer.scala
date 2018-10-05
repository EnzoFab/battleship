package player

import grid.{Shot, Square}
import ship.Ship

case class HPlayer(name: String = "No Name",
              override val navy: List[Ship], override val playerShotRecord: List[Shot],
                   override val opponentShotRecord: List[Shot] )
extends Player(navy, playerShotRecord, opponentShotRecord) {

  override def identifier: String = name

  override def myOwnCopy(ships: List[Ship], playerShotRecord: List[Shot],
                         opponentShotRecord: List[Shot]):
  HPlayer = HPlayer(name, ships, playerShotRecord, opponentShotRecord)


}



object HPlayer{
  def apply(name: String, ships:
  List[Ship] = Nil, shotRecord: List[Shot] = Nil, opponentShotRecord: List[Shot] = Nil): HPlayer
  = new HPlayer(name, ships, shotRecord, opponentShotRecord)
}