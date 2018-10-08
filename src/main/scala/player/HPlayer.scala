package player

import grid.{Shot, Square}
import ship.Ship

case class HPlayer(name: String = "No Name",
              override val navy: List[Ship], override val playerShotRecord: List[Shot],
                   override val opponentShotRecord: List[Shot], override val playerScore: Int)
extends Player(navy, playerShotRecord, opponentShotRecord, playerScore) {

  override def identifier: String = name

  override def myOwnCopy(navy: List[Ship], playerShotRecord: List[Shot],
                         opponentShotRecord: List[Shot], playerScore: Int):
  HPlayer = HPlayer(name, navy, playerShotRecord, opponentShotRecord, playerScore)


}



object HPlayer{
  def apply(name: String, ships:
  List[Ship] = Nil, shotRecord: List[Shot] = Nil,
            opponentShotRecord: List[Shot] = Nil, playerScore: Int = 0): HPlayer
  = new HPlayer(name, ships, shotRecord, opponentShotRecord, playerScore)
}