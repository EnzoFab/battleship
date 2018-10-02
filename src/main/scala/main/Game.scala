package main

import player.{HPlayer, IA, Player}
import ship.BattleShip

object Game extends App {

	var player1: Player = _ // equivalent to null
	var player2: Player = _

	println("\n Welcome to the BattleShip Game\nPlease choose the game mode:\n")

	println("1- Player vs Player\n2- Player vs IA\n3- IA vs IA")
	val x = readInt

	x match {
		case 1 => {
			println("Player vs Player\nPlayer 1 name:")
			val name1 = readLine

			player1 = HPlayer(name1)

			println("\nPlayer 2 name")
			val name2 = readLine

			player2 = HPlayer(name2)
		}
		case 2 => {
			println("Player vs IA\n\nPlayer name: ")
			val name = readLine
			player1 = HPlayer(name)

			println("\nPlease choose choose the difficulty level of the IA" +
				"\n(1, 2 or 3):\n")
			val difficultyLevel = readInt
			player2 = IA(difficultyLevel)

		}
		case _ => {
			println("IA vs IA")
			println("\nPlease choose choose the difficulty level of the first IA" +
				"\n(1, 2 or 3):\n")
			val difficultyLevel1 = readInt
			player1 = IA(difficultyLevel1)

			println("\nPlease choose choose the difficulty level of the second IA" +
				"\n(1, 2 or 3):\n")
			val difficultyLevel2 = readInt
			player2 = IA(difficultyLevel2)
		}
	}

	//TODO disposition of the ships
	var b = BattleShip(0, 0, "T")
	b.copy(positions = b.positions.tail)


	println("\n\nEnd game !")

}
