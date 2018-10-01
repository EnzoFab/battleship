package grid

case class Square(coordX: Int, coordY: Int, isTouched: Boolean = false, icon: String = "X") {

	override def toString: String = {
		if (!isTouched) "| " + icon + " |"
		else "| X |"
	}

	/**
		* Two squares are equal if their coordinates are the same
		* @param square
		* @return
		*/
	def equals(square : Square): Boolean = square.coordX == coordX && square.coordY == coordY

	/**
		* Two squares are equal if their share one same coordinate
		* @param square
		* @return
		*/
	def isClosed(square: Square): Boolean = square.coordX == coordX || square.coordY == coordY

}