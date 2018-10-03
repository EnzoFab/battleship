package grid

import ship.Ship

object Grid {

  /**
    * A ship is conform if all its square belongs to the grid
    * @param ship
    * @return
    */
  def isConform(ship: Ship): Boolean = {

    def isConformInt(list: List[Square]): Boolean = {
      if(list.isEmpty) true
      else isInGrid(list.head) && isConformInt(list.tail)
    }

    isConformInt(ship.positions)
  }

  /**
    * Check if the square in parameter belongs to the grid
    * @param square
    * @return
    */
  def isInGrid(square: Square): Boolean = {
    val x = square.coordX.toInt
    val y = square.coordY
    x >= 65 && x <= 74 && y >= 0 && y <= 9
    // x e [A, J] and y e [0, 9]
  }

  /**
    * displays the list given in parameter in grid form
    * @param list
    */
  def display(list: List[Square]): Unit = {
    println()
  }
}
