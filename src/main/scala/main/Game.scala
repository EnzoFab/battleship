package main

import grid.{Grid, Square}
import main.Game.random
import player.{AI, HPlayer, Player}
import ship._

import scala.util.Random

object Game extends App {


  def play(gs: GameState, random: Random): GameState = {
    print("\033[H\033[2J")
    println(s"${Console.BLINK}Game Start:${Console.RESET}\n\n")
    println(gs.player1.identifier + ": ")

		var copyPlayer1 = gs.player1.resetGame
		var copyPlayer2 = gs.player2.resetGame
			// reset the list of both players


		copyPlayer1 = copyPlayer1.myOwnCopy(navy =  SetUp.placeShip(copyPlayer1, Random).navy)
    // create a copy of the player with his navy
		copyPlayer2 = copyPlayer2.myOwnCopy(navy =  SetUp.placeShip(copyPlayer2, Random).navy)


		println(gs.player2.identifier + ": ")

    var gameState = GameState(copyPlayer1, copyPlayer2)

    // display the grid
    /*Grid.displayNavy(gameState.player1.navy, gameState.player1.playerShotRecord)

    Grid.displayNavy(player2.navy, player2.playerShotRecord)*/

    gameState = GameLoop.battleLoop(gameState.player1, gameState.player2, random)
    println("Play again ?\n1- Yes\n2- No")
    val playAgain = scala.io.StdIn.readInt
    if(playAgain == 1)
      play(gameState, random)
    else
      gameState

  }


  Console.println(s"\n${Console.BOLD}Welcome to the BattleShip Game\n" +
		s"${Console.RESET}\n")
    val random = Random
	// println(Grid.displayGrid())
  var player1: Player = _ // equivalent to null
  var player2: Player = _

  println("First, what do you want to do ?\n1- Play game\n\n" +
    "2- Test AI level\n  * AI level 1 vs AI level 2, 100 times\n" +
    "  * AI level 1 vs AI level 3, 100 times\n" +
    "  * AI level 2 vs AI level 3, 100 times")
  val gameMod = scala.io.StdIn.readInt

  if (gameMod == 1) {

    println("Please choose the game mode:\n1- Player vs Player\n2- Player vs AI\n3- AI vs AI")
    val x = scala.io.StdIn.readInt

    x match {
      case 1 =>
        val name1 = scala.io.StdIn.readLine("Player vs Player\n\nPlayer 1 name: \n")

        player1 = HPlayer(name1)

        val name2 = scala.io.StdIn.readLine("Player 2 name: \n")

        player2 = HPlayer(name2)

        println(s"$name1 and $name2 you are about to face each other in the battleship game be ready !\n")
      case 2 =>
        println("Player vs AI\n\nPlayer name: ")
        val name = scala.io.StdIn.readLine
        player1 = HPlayer(name)

        println("\nPlease choose choose the difficulty level of the AI" +
          "\n(1, 2 or 3):\n")
        val difficultyLevel = scala.io.StdIn.readInt
        player2 = AI(difficultyLevel)

        println(s"\n$name you are about to face the AI level $difficultyLevel in the battleship game be ready !")
      case _ =>
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

    var gameState = GameState(player1, player2)
    // to keep a track of the result


    // the players didn't want to play again
    gameState = play(gameState, random)

    println(s"\n$gameState\nEnd game !")


  }
  else {
    var csvText = ""
    csvText += AI.testAI(AI(1), AI(2), 100, random).toString + "\n"
    csvText += AI.testAI(AI(1), AI(3), 100, random).toString + "\n"
    csvText += AI.testAI(AI(2), AI(3), 100, random).toString

    if (FileSaver.saveCSV("ai_proof", csvText).isDefined) // try to save the csv file
      println(s"\n${Console.BOLD}File saved${Console.RESET}")
    else
      println("An error has occured coudn't save the file")

  }


	/* var b = BattleShip('A', 0, "R")
	b = b.copy(positions = b.positions.tail)

		println("Conform : " + Grid.isConform(b))
	var b2 = BattleShip('C', 0, "R")

	println("Conform : " + Grid.isConform(b2))

	println("overLaps :" + b.overLaps(b2)) */





}
