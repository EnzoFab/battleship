package grid


import ship.Ship

/**
	*
	* @param coordX: A letter, coordinate to locate the square on a grid
	* @param coordY: A number, coordinate to locate the square on a grid
	* @param isTouched: A boolean, Allows to know whether this square was touched or not. Default value: False
	* @param icon: representation of the square. Default value -
	*/
case class Square(coordX: Char, coordY: Int, isTouched: Boolean = false, icon: String = "-",
									var associatedShipName: String = "None") {

	override def toString: String = {
		var value: String = "" + Console.BOLD + Console.WHITE
		if(isTouched) value += Console.RED_B +  " X "
		else if (icon == "-")value += Console.CYAN_B + " " + icon + " "
		else value += Console.MAGENTA_B + " " + icon + " "
		value + Console.RESET // reset the console to set the default color
	}



	/**
		* Two squares are equal if their coordinates are the same
		* @param square
		* @return
		*/
	def equals(square : Square): Boolean = square.coordX == coordX && square.coordY == coordY

	/**
		* Two squares are close if the are one square away
		* square.x == x and square.y = y +- 1 or square.y == y and square.x = x +- 1
		* @param square: A square
		* @return
		*/
	def isClosed(square: Square): Boolean = (square.coordX == coordX && square.coordY  == coordY + 1) ||
		(square.coordX == coordX && square.coordY  == coordY - 1) ||
		(square.coordX.toInt == coordX.toInt - 1 && square.coordY  == coordY) ||
		(square.coordX.toInt == coordX.toInt + 1 && square.coordY  == coordY)

}