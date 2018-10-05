package grid

import ship.Ship

/**
	*
	* @param coordX: A letter
	* @param coordY
	* @param isTouched
	* @param icon
	*/
case class Square(coordX: Char, coordY: Int, isTouched: Boolean = false, icon: String = "-",
									var associatedShipName: String = "None") {

	override def toString: String = "| " + icon + " "



	/**
		* Two squares are equal if their coordinates are the same
		* @param square
		* @return
		*/
	def equals(square : Square): Boolean = square.coordX == coordX && square.coordY == coordY

	/**
		* Two squares are close if the are one square away
		* square.x == x and square.y = y +- 1 or square.y == y and square.x = x +- 1
		* @param square
		* @return
		*/
	def isClosed(square: Square): Boolean = (square.coordX == coordX && square.coordY  == coordY + 1) ||
		(square.coordX == coordX && square.coordY  == coordY - 1) ||
		(square.coordX.toInt == coordX.toInt - 1 && square.coordY  == coordY) ||
		(square.coordX.toInt == coordX.toInt + 1 && square.coordY  == coordY)

}