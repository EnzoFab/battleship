import grid.Square
import org.scalatest.FunSuite
import player.{HPlayer, Player}
import ship._

class PlayerTest extends FunSuite{
  test("myOwnCopy is correctly set") {
    val player: Player = HPlayer("name1")
    val player2 = player.myOwnCopy(navy = List(Destroyer('A', 0, "t")))

    assert(player.identifier == player2.identifier && player.navy.isEmpty)
  }

  test("Overlaps is correctly set") {
    val ship = Destroyer('A', 0, "r")
    val ship2 = Destroyer('B', 0, "r")
    val ship3 = Destroyer('J', 9, "t")
    var player: Player = HPlayer("name1")

    player = player.myOwnCopy(navy = List(ship))

    assert(player.overLaps(ship) && player.overLaps(ship2) && !player.overLaps(ship3))

  }

  test("HasTouched is correctly set") {
    var player: Player = HPlayer("name1")

    player = player.myOwnCopy(navy = List(Destroyer('A', 0, "r"), Cruiser('D', 5, "t") ))

    val square = Square('A', 0)
    val square2 = Square('D', 5)
    val square3 = Square('J', 9) // not supposed to hit

    assert(player.hasTouched(square).isDefined
      && player.hasTouched(square2).isDefined && player.hasTouched(square3).isEmpty)
  }

  test("inSight is correctly set") {
    var player: Player = HPlayer("name1")

    player = player.myOwnCopy(navy = List(Destroyer('A', 0, "r"), Cruiser('D', 5, "t") ))

    val square = Square('A', 1)
    val square2 = Square('D', 6)
    val square3 = Square('J', 9)

    assert(player.inSight(square) && player.inSight(square2) && !player.inSight(square3))
  }


}
