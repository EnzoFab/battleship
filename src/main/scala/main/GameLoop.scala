package main

import grid.{Grid, Shot, Square}
import player.{AI, HPlayer, Player}

import scala.util.Random

object GameLoop {

  /**
    * main loop of the game
    * the currentPlayer is playing and tries to shoot his opponent navy
    * @param currentPlayer
    * @param opponent
    */
  def battleLoop(currentPlayer: Player, opponent: Player, random: Random): GameState = {
    if (currentPlayer.gameOver) {
      println(opponent.identifier + " has won\n Game is over !")
      GameState(
        opponent,
        currentPlayer.myOwnCopy(playerScore = currentPlayer.playerScore + 1)
      )
      // the loser of this game will begin the next game
    }
    else {
      var x: Char = ' '
      var y: Int = 0
      var square: Square = null
      var shot: Shot = null

      currentPlayer match {
        case p:  HPlayer => {
          println(s" ${p.identifier} Please choose the letter of the column\n" +
            "(Q: quit, P: display your grid): ")
          x = scala.io.StdIn.readChar
          x = x.toUpper

          if (x == 'P') {
            Grid.displayNavy(currentPlayer.navy, currentPlayer.opponentShotRecord)
            Grid.displayShotRecord(currentPlayer.playerShotRecord)
            // display the currentplayer grid and reload the game in the same state

            battleLoop(currentPlayer, opponent, random)
          }else if (x == 'Q'){
            println("\nQuit !")
            Grid.displayNavy(currentPlayer.navy, currentPlayer.opponentShotRecord)
            println("\n\n")
            Grid.displayNavy(opponent.navy, opponent.opponentShotRecord)

            GameState(opponent.myOwnCopy(), currentPlayer.myOwnCopy())
          }
          // special command to display of leave the game

          println("\nPlease choose the row (a number is expected): ")
          y = scala.io.StdIn.readInt

          square = Square(x, y)

          if (!Grid.isInGrid(square)) {
            println("Sorry these coordinates aren't in the grid !")
            battleLoop(currentPlayer, opponent, random)
          }
        }
        case p: AI => {
         square = p.computeTarget(random, p.level, p.playerShotRecord)
          println(s"${currentPlayer.identifier} target position: (${square.coordX}, ${square.coordY})\n")
        }

      }

      var opponentNavy = opponent.navy
      val attackResult: Option[Square] = opponent.hasTouched(square) // option type

      if (attackResult.isDefined) { // replacement of isEmpty
        println(s"${currentPlayer.identifier} has hit a ${attackResult.get.associatedShipName} " +
          s"of ${opponent.identifier}'s navy\n\n")

        opponentNavy = opponent.updateTouchedShip(attackResult.get)
        // update the ship square of the navy

        shot = Shot(square.coordX, square.coordY, hasTouch = true, shipName = attackResult.get.associatedShipName)

      } else if (opponent.inSight(square)) {
        println("A ship is close\n")

        shot = Shot(square.coordX, square.coordY, hasTouch = false, isNear = true)

      }else {
        println("Right in the ocean...\n")
        shot = Shot(square.coordX, square.coordY, hasTouch = false)
      }

      val current = currentPlayer.myOwnCopy(playerShotRecord = shot::currentPlayer.playerShotRecord)

      // create a copy of the enemy by changing his navy and it's opponentShotRecord
      val enemy = opponent.myOwnCopy(
        navy = opponentNavy,
        opponentShotRecord = shot::opponent.opponentShotRecord)

      battleLoop(enemy, current, random) // it's opponent turn
    }

  }
}
