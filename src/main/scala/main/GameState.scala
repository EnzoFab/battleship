package main

import player.Player

/**
  * Save the state of the game. The player 1 is the player that has began the game
  * @param player1
  * @param player2
  */
case class GameState(player1: Player, player2: Player){
  override def toString: String = player1.toString + "; " + player2.toString
}
