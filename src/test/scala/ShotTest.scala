import grid.{Shot, Square}
import org.scalatest.FunSuite

class ShotTest extends FunSuite{
  test("To square is set correctly") {
    val shot = Shot('A', 9)
    val square = Square('A', 9)

    val sq = Shot('B', 5, hasTouch = true).toSquare

    assert(shot.toSquare.equals(square) &&
    sq.isTouched && sq.icon == "X")
  }
}
