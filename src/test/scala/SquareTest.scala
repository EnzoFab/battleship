import grid.Square
import org.scalatest.FunSuite

class SquareTest extends FunSuite{
  test("Equal is set correctly") {
    val square = Square('A', 9)
    val square2 = Square('A', 9)
    assert(square.equals(square2) && !square.equals(Square('C', 8)))
  }

  test("IsClosed is set correctly") {
    val square = Square('B', 7)
    val square2 = Square('A', 7)
    val square3 = Square('C', 7)
    val square4 = Square('B', 8)
    val square5 = Square('A', 8)

    assert(
      square.isClosed(square2) && square.isClosed(square3) &&
        square.isClosed(square4 ) && !square.isClosed(square5)
    )
  }

  test("ToString is set correctly") {
    val square = Square('A', 9, isTouched = true, icon = "K")
    val square2 = Square('A', 9, icon = "K")
    val square3 = Square('A', 9)

    assert(square.toString == Console.BOLD + Console.WHITE + Console.RED_B +  " X " + Console.RESET &&
    square2.toString == Console.BOLD + Console.WHITE + Console.MAGENTA_B +  " K " + Console.RESET &&
    square3.toString == Console.BOLD + Console.WHITE + Console.CYAN_B +  " - " + Console.RESET)
  }
}
