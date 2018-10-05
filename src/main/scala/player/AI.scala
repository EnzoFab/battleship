package player
import grid.{Grid, Shot, Square}
import ship.Ship

import scala.annotation.tailrec
import scala.util.Random

case class AI(level : Int = 1,
              override val navy: List[Ship], override val playerShotRecord: List[Shot],
              override val opponentShotRecord: List[Shot])
  extends Player(navy, playerShotRecord, opponentShotRecord) {

  override def identifier: String = s"IA level $level"

  override def myOwnCopy(ships: List[Ship], playerShotRecord: List[Shot],
                         opponentShotRecord: List[Shot]): AI
  = AI(level, ships, playerShotRecord, opponentShotRecord)

  /**
    * To choose a square to target according to the level of the AI
    * @param random
    * @param aiLevel
    * @return
    */
  def computeTarget(random: Random, aiLevel: Int, playerShotRecord: List[Shot]): Square = {
    aiLevel match {
      case 1 => level1(random)
      case 2 => level2(random)
      case 3 => level3(random, playerShotRecord)
    }
  }


  /**
    * Shot in random place and can hit several time on the same place
    * @param random
    * @return
    */
  private def level1(random: Random): Square = {
    val arrayInt = (0 to 9).toArray
    val arrayChar = ('A' to 'J').toArray

    val x = arrayChar(random.nextInt(10))
    val y = arrayInt(random.nextInt(10))
    Square(x, y)
  }


  /**
    * Hit on random place but doesn't hit twice the same position
    * @param random
    * @return
    */
  private def level2(random: Random): Square = {
    /* val arrayInt = (0 to 9).toArray
    val arrayChar = ('A' to 'J').toArray

    val x = arrayChar(random.nextInt(10))
    val y = arrayInt(random.nextInt(10))
    val s = Square(x, y)

    if (placeTouched(s, playerShotRecord))
      level2(random)
    else s */

    val l = possibleShot(Grid.createGridInList('A', 0, 'J', 9), playerShotRecord).toArray
    l(random.nextInt(l.length)) // choose randomly in the possible shot
  }

  /**
    * Hit randomly if there isn't a success or close shot in the four previous one
    * overwise hit around
    * if two of the previous shot have successded hit on the same row or column
    * @param random
    * @return
    */
  private def level3(random: Random, shotRecord: List[Shot]): Square = {
    if (fourPreviousShotHasSucceeded(shotRecord).isDefined){
      // create 4 square arround and check if they are possible shot and are in the grid
      Square('A', 2) // TODO
    }
    Square('A', 2) // TODO
  }

  /**
    * allows to know if the IA has succeeded its four previous shot or at List on target
    * @return
    */
  private def fourPreviousShotHasSucceeded(playerShotRecord: List[Shot]): Option[Square] = {
    // TODO use option instead
    @tailrec
    def fourPreviousShotInt(list: List[Shot], number: Int): Option[Square] = {
      if(list.isEmpty || number > 4) None
      else  if ( list.head.hasTouch || list.head.isNear) Some(list.head.toSquare)
      else fourPreviousShotInt(list.tail, number + 1)
    }

    fourPreviousShotInt(playerShotRecord, 0)
  }

  private def twoPreviousShotHasSucceded(playerShotRecord: List[Shot]): Boolean = {

  }

  private def placeTouched(square: Square, shotList: List[Shot]): Boolean = {
    if (shotList.isEmpty) false
    else {
      shotList.head.toSquare.equals(square) || placeTouched(square, shotList.tail)
    }
  }


  /**
    * according to the shotRecord return all the square that weren't targeted yet
    * @param grid
    * @param shotRecord
    * @return
    */
  private def possibleShot (grid: List[Square], shotRecord: List[Shot]): List[Square] = {
    if(grid.isEmpty) Nil
    else if (!placeTouched(grid.head, shotRecord)) {
      grid.head :: possibleShot(grid.tail, shotRecord)
    }else {
      possibleShot(grid.tail, shotRecord)
    }
  }
}


object AI {
  def apply(level: Int, ships:
  List[Ship] = Nil, playerShotRecord: List[Shot] = Nil, opponentShotRecord: List[Shot] = Nil): AI
  = new AI(level, ships, playerShotRecord, opponentShotRecord)


  /**
    * Random orientation for a ship according the the given parameter
    * @param x
    * @param y
    * @param random
    * @return
    */
  def randomAIOrientation(x: Char, y: Int, random: Random): String = {
    var arrayOrientation: Array[String] = null
    if (x.toInt >= 'A'.toInt && x.toInt <= 'C'.toInt && y >= 0 && y <= 3) // the ship is on the top left of the grid
      arrayOrientation = Array("RIGHT", "BOTTOM")

    else if (x.toInt >= 'A'.toInt && x.toInt <= 'C'.toInt && y >= 7 && y <= 9) // ship on bottom left
      arrayOrientation = Array("RIGHT", "TOP")

    else if (x.toInt >= 'H'.toInt && x.toInt <= 'J'.toInt && y >= 0 && y <= 3) // the ship is on the right top of the grid
      arrayOrientation = Array("LEFT", "BOTTOM")

    else if(x.toInt >= 'H'.toInt && x.toInt <= 'J'.toInt && y >= 7 && y <= 9) // the ship is on the bottom rigth
      arrayOrientation = Array("LEFT", "TOP")

    else if(y >= 7 && y <= 9) // ship is on bottom
      arrayOrientation = Array("TOP")

    else if (y >= 0 && y <= 3) // ship is on top
      arrayOrientation = Array("BOTTOM")

    else if (x.toInt >= 'A'.toInt && x.toInt <= 'C'.toInt) // Ship is on the left
      arrayOrientation = Array("RIGHT")

    else if (x.toInt >= 'H'.toInt && x.toInt <= 'J'.toInt) // ship is on the right
      arrayOrientation = Array("LEFT")

    else // shie is on the middle
      arrayOrientation = Array("T", "B", "R", "L")

    arrayOrientation(random.nextInt(arrayOrientation.size))
  }
}
