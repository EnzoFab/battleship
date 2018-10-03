package main

import grid.Grid
import player.{HPlayer, IA, Player}
import ship._

object SetUp {
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
          //TODO finir
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
