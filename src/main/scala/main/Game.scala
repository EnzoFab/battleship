package main

import grid.{Grid, Square}
import player.{AI, HPlayer, Player}
import ship._

import scala.util.Random

object Game extends App {

	print(s"${Console.RED}Salut ${Console.RESET}")
	var player1: Player = _ // equivalent to null
	var player2: Player = _

	println(s"\n${Console.BOLD}Welcome to the BattleShip Game\nPlease choose the game mode:" +
		s"${Console.RESET}\n")

	// println(Grid.displayGrid())

	println("1- Player vs Player\n2- Player vs AI\n3- AI vs AI")
	val x = scala.io.StdIn.readInt
  val random = Random
	x match {
		case 1 => {
			val name1 = scala.io.StdIn.readLine("Player vs Player\n\nPlayer 1 name: \n")

			player1 = HPlayer(name1)

			val name2 = scala.io.StdIn.readLine("Player 2 name: \n")

			player2 = HPlayer(name2)

			println(s"$name1 and $name2 you are about to face each other in the battleship game be ready !\n")
		}
		case 2 => {
			println("Player vs AI\n\nPlayer name: ")
			val name = scala.io.StdIn.readLine
			player1 = HPlayer(name)

			println("\nPlease choose choose the difficulty level of the AI" +
				"\n(1, 2 or 3):\n")
			val difficultyLevel = scala.io.StdIn.readInt
			player2 = AI(difficultyLevel)

			println(s"\n$name you are about to face the AI level $difficultyLevel in the battleship game be ready !")
		}
		case _ => {
			println("AI vs AI")
			println("\nPlease choose choose the difficulty level of the first AI" +
				"\n(1, 2 or 3):\n")
			val difficultyAI1 = scala.io.StdIn.readInt
			player1 = AI(difficultyAI1)

			println("\nPlease choose choose the difficulty level of the second AI" +
				"\n(1, 2 or 3):\n")
			val difficultyAI2 = scala.io.StdIn.readInt
			player2 = AI(difficultyAI2)

			println(s"\nAI level $difficultyAI1 and AI level $difficultyAI2 are about to face each other " +
				s"the in the battleship game be ready !")

		}
	}

	var gameState = GameState(player1, player2)

	println("Game Start:\n\n")
	println(gameState.player1.identifier + ": ")

	val copyPlayer1 = player1.myOwnCopy(navy =  SetUp.placeShip(gameState.player1, Random).navy)

	println(gameState.player2.identifier + ": ")
	val copyPlayer2 = player2.myOwnCopy(navy =  SetUp.placeShip(gameState.player2, Random).navy)

	gameState = gameState.copy(player1 = copyPlayer1, player2 = copyPlayer2)

	// display the grid
  Grid.displayNavy(gameState.player1.navy, gameState.player1.playerShotRecord)

  Grid.displayNavy(player2.navy, player2.playerShotRecord)

  gameState = GameLoop.battleLoop(gameState.player1, gameState.player2, random)




	/* var b = BattleShip('A', 0, "R")
	b = b.copy(positions = b.positions.tail)

		println("Conform : " + Grid.isConform(b))
	var b2 = BattleShip('C', 0, "R")

	println("Conform : " + Grid.isConform(b2))

	println("overLaps :" + b.overLaps(b2)) */


	println("\n\nEnd game !")



}
