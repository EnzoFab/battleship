package ship

import grid.Square

import scala.annotation.tailrec

abstract class Ship(val positions: List[Square], val shipSize: Int, val shipName: String){


	/*var positions: List[Square] // list of squares representing the positions of the ship in
	val shipSize: Int
	val shipName: String*/


	/**
	@param ship
	return True if the ship given in parameter share one or several position with the current ship
	**/
	def overLaps(ship: Ship): Boolean = {
		/**
			@param sq
			@param l2
			check if the current square is equals to at list one square of the list 
		**/
		@tailrec
		 def overLapsSquare(sq: Square, l2: List[Square]): Boolean = {
			if (l2.tail.isEmpty) {
				sq.equals(l2.head) 
			} else {
				sq.equals(l2.head) || overLapsSquare(sq, l2.tail)
			}
		}

		@tailrec
		def overLapsList(l1: List[Square], l2: List[Square]): Boolean = {
			if (l1.tail.isEmpty) {
				overLapsSquare(l1.head, l2)
			} else {
				overLapsSquare(l1.head, l2) || overLapsList(l1.tail, l2)
			}
		}

		overLapsList(positions, ship.positions)
	}

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


  def copy(positions: List[Square] = positions,
           shipSize: Int = shipSize,
           shipName: String = shipName): Ship
	
}

object Ship{
	/**
		* The function doesn't check whether the ship fit the grid or not
		* the verification needs to be done upstream
		* @param x
		* @param y
		* @param orientation
		*/
	def createList(x: Int, y: Int, orientation: String, size: Int, shipName: String): List[Square] = {
		var newX: Int = x
		var newY: Int = y

		orientation.toUpperCase() match {
			case "T" => { // top
				newY += 1
			}
			case "B" => { // bottom
				newY -= 1
			}
			case "L" => { // left
				newX -= 1
			}
			case "R" => { // right
				newX += 1
			}
		}

		if (size == 0) Nil
		else {
			Square(x, y, isTouched = false, shipName.charAt(0) + "") :: createList(newX,
				newY, orientation, size - 1, shipName)
		}
	}
}