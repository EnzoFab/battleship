package main

import player.{HPlayer, IA, Player}
import ship.BattleShip

object Game extends App {

	var player1: Player = _ // equivalent to null
	var player2: Player = _

	println("\n Welcome to the BattleShip Game\nPlease choose the game mode:\n")

	println("1- Player vs Player\n2- Player vs IA\n3- IA vs IA")
	val x = scala.io.StdIn.readInt

	x match {
		case 1 => {
			val name1 = scala.io.StdIn.readLine("Player vs Player\nPlayer 1 name: \n")

			player1 = HPlayer(name1)

			val name2 = scala.io.StdIn.readLine("\nPlayer 2 name: \n")

			player2 = HPlayer(name2)

			println(s"\n$name1 and $name2 you are about to face each other in the battleship game be ready !")
		}
		case 2 => {
			println("Player vs IA\n\nPlayer name: ")
			val name = scala.io.StdIn.readLine
			player1 = HPlayer(name)

			println("\nPlease choose choose the difficulty level of the IA" +
				"\n(1, 2 or 3):\n")
			val difficultyLevel = scala.io.StdIn.readInt
			player2 = IA(difficultyLevel)

			println(s"\n$name you are about to face the IA level $difficultyLevel in the battleship game be ready !")
		}
		case _ => {
			println("IA vs IA")
			println("\nPlease choose choose the difficulty level of the first IA" +
				"\n(1, 2 or 3):\n")
			val difficultyLevel1 = scala.io.StdIn.readInt
			player1 = IA(difficultyLevel1)

			println("\nPlease choose choose the difficulty level of the second IA" +
				"\n(1, 2 or 3):\n")
			val difficultyLevel2 = scala.io.StdIn.readInt
			player2 = IA(difficultyLevel2)

			println(s"\nIA level $difficultyLevel1 and IA level $difficultyLevel2 are about to face each other " +
				s"the in the battleship game be ready !")

		}
	}

	//TODO disposition of the ships
	var b = BattleShip('A', 0, "R")
	// b.copy(positions = b.positions.tail)

	println(b)

	println("\n\nEnd game !")

}
