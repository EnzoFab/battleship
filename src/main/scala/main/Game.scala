package main

import grid.Grid
import player.{HPlayer, IA, Player}
import ship._

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


	println(player1.indentifiant)
	player1 = player1.myOwnCopy(navy =  placeShip(player1, 0).navy)

	println(player2.indentifiant)
	player2 = player2.myOwnCopy(navy =  placeShip(player2, 0).navy)



	/* var b = BattleShip('A', 0, "R")
	b = b.copy(positions = b.positions.tail)

		println("Conform : " + Grid.isConform(b))
	var b2 = BattleShip('C', 0, "R")

	println("Conform : " + Grid.isConform(b2))

	println("overLaps :" + b.overLaps(b2)) */


	println("\n\nEnd game !")


	def placeShip(player: Player, navySize: Int): Player = {
		if (navySize >= 5) player.myOwnCopy()
		else {
			var ship: Ship = null
			var x: Char = '/'
			var y: Int = 0
			var orientation: String = ""
			var copyPlayer: Player = player

			player match {
				case IA(_, _, _) => { // create random value for the ship

				}
				case HPlayer(_, _, _) => {
					if(navySize == 0) println("\n1- BattleShip positionning (size 4)")
					else if(navySize == 1) println("\n2- Carrier positionning (size 5)")
					else if(navySize == 2) println("\n3- Cruiser positionning (size 3)")
					else if(navySize == 3) println("\n4- Destroyer positionning (size 2)")
					else if(navySize == 4) println("\n5- SubMarine positionning (size 3)")




					println("Please choose the column of your ship (a letter is expected): ")
					x = scala.io.StdIn.readChar
					x = x.toUpper
					println("\nPlease choose the row of your ship (a number is expected): ")
					y = scala.io.StdIn.readInt

					orientation = scala.io.StdIn.readLine("Please choose the orientation of your ship\n" +
						"(top, t, bottom, b, left, l, right or r)")

					if(orientation.toLowerCase != "top" && orientation.toLowerCase != "t"
						&& orientation.toLowerCase != "bottom" && orientation.toLowerCase != "b"
						&& orientation.toLowerCase != "left" && orientation.toLowerCase != "l"
						&& orientation.toLowerCase != "right" && orientation.toLowerCase != "r") {
						println("Sorry we can't handle this type of orientation retry please\n")
						placeShip(player, navySize) // reload with the same value of the parameter
					}
				}
					case _ => copyPlayer = player.myOwnCopy()

			}

			if(navySize == 0) ship = BattleShip(x, y, orientation)
			else if(navySize == 1) ship = Carrier(x, y, orientation)
			else if(navySize == 2) ship = Cruiser(x, y, orientation)
			else if(navySize == 3) ship = Destroyer(x, y, orientation)
			else if(navySize == 4) ship = SubMarine(x, y, orientation)

			// recursive call
			player match {
				case IA(_, _, _) => {

					if (!Grid.isConform(ship) || player.overLaps(ship)){
						placeShip(player.myOwnCopy(), navySize) // reload with the same value of the parameter
					}
					else copyPlayer = player.myOwnCopy(navy = ship :: player.navy )

				}
				case HPlayer(_, _, _) => {

					if (!Grid.isConform(ship)) {
						println("Sorry This ship doesn't fit the grid (column A - J and row 0 - 9)")
						placeShip(player.myOwnCopy(), navySize) // reload with the same value of the parameter
					}
					else if (player.overLaps(ship)) {
						println("This ship can't be created it overlaps an existing one")
						placeShip(player.myOwnCopy(), navySize) // reload with the same value of the parameter
					}
					else copyPlayer = player.myOwnCopy(navy = ship :: player.navy )

				}
			}

			placeShip(copyPlayer, navySize + 1)

		}
	}

}
