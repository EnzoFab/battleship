package ship

import grid.Square

import scala.annotation.tailrec

abstract class Ship(val positions: List[Square], val shipSize: Int, val shipName: String){


	/*var positions: List[Square] // list of squares representing the positions of the ship in
	val shipSize: Int
	val shipName: String*/


	/**
	A ship is sinked if all its square are touched
		**/
	def isSinked: Boolean = {
		def isSinkInt(l: List[Square]): Boolean = {
			if(l.tail.isEmpty) l.head.isTouched
			else l.head.isTouched && isSinkInt(l.tail)
		}

		isSinkInt(positions)
	}

	/**
	@param ship
	return True if the ship given in parameter share one or several position with the current ship
	**/
	def overLaps(ship: Ship): Boolean = {
		/**
			@param sq
			@param list
			check if the current square is equals to at list one square of the list 
		**/
		@tailrec
		 def overLapsSquare(sq: Square, list: List[Square]): Boolean = {
			if (list.isEmpty) false
			else {
				sq.equals(list.head) || overLapsSquare(sq, list.tail)
			}
		}

		@tailrec
		def overLapsList(l1: List[Square], l2: List[Square]): Boolean = {
			if (l1.isEmpty) false
			else {
				overLapsSquare(l1.head, l2) || overLapsList(l1.tail, l2)
			}
		}

		overLapsList(positions, ship.positions)
	}


	/**
		* Check if the the
		* @param square
		* @return
		*/
	def isTouched(square: Square): Option[Square] = {

		@tailrec
		def isTouchInt(l: List[Square], square: Square): Option[Square] = {
			if (l.isEmpty) None
			else if (!l.head.isTouched && l.head.equals(square)) Some(l.head)
			else {
				isTouchInt(l.tail, square)
			}
		}

		isTouchInt(positions, square)
	}


	/**
		* Check if the shot is closed to the boat
		* @param square
		* @return
		*/
	def inSight(square: Square): Boolean = {

		@tailrec
		def inSightInt(l: List[Square], square: Square): Boolean = {
			if (l.isEmpty) false
			else {
				square.isClosed(l.head) || inSightInt(l.tail, square)
			}
		}

		inSightInt(positions, square)
	}

	/**
		* Return a list of square that are a copy of the current position of the boat with all the
		* position of the ship set to touched if they are equal to the square
		* @param square
		* @return
		*/
	def updateShip(square: Square): List[Square] = {

		def updateS(list: List[Square], square: Square): List[Square] = {
			if (list.isEmpty) Nil
			else if (list.head.equals(square))
				list.head.copy(isTouched = true) :: updateS(list.tail, square)
			else list.head :: updateS(list.tail, square)
		}

		updateS(positions, square)
	}

  def copy(positions: List[Square] = positions, shipSize: Int = shipSize, shipName: String = shipName): Ship

	override def toString: String = {

		def displayShip(list: List[Square]): String = {
			if (list.isEmpty) ""
			else list.head.coordX + " " + list.head.coordY + "\n" + displayShip(list.tail)
		}

		displayShip(positions)
	}
}

object Ship{
	/**
		* The function doesn't check whether the ship fit the grid or not
		* the verification needs to be done upstream
		* @param x
		* @param y
		* @param orientation
		*/
	def createList(x: Char, y: Int, orientation: String, size: Int, shipName: String): List[Square] = {
		var newX: Char = x
		var newY: Int = y

		orientation.toUpperCase() match {
			case "T" | "TOP" => { // top
				newY -= 1
			}
			case "B" | "BOTTOM" => { // bottom
				newY += 1
			}
			case "L" | "LEFT" => { // left
				newX = (newX.toInt - 1).toChar // go to the previous letter
			}
			case "R" | "RIGHT" => { // right
				newX = (newX.toInt + 1).toChar // go tho the next letter
			}
		}

		if (size == 0) Nil
		else
			Square(x, y, isTouched = false, shipName.charAt(0) + "", shipName) ::
				createList(newX, newY, orientation, size - 1, shipName)
	}

}