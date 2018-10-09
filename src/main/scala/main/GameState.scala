package main

import player.Player

/**
  * Save the state of the game. The player 1 is the player that has began the game
  * @param player1: A Player can be AI or HPLayer
  * @param player2: A Player can be AI or HPLayer
  */
case class GameState(player1: Player, player2: Player){
  override def toString: String = player1.toString + "; " + player2.toString
}
