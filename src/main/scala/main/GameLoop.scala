package main

import player.Player

object GameLoop {

  /**
    * main loop of the game
    * @param player1
    * @param player2
    */
  def mainLoop(player1: Player, player2: Player): Unit = {
    if (player1.gameOver) println(player2.indentifiant + " has won\n Game is over !")
    else if (player2.gameOver) println(player1.indentifiant + " has won\n Game is over !")
    else {

    }

  }
}
