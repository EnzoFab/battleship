package grid

import ship.Ship

/**
  * kinda helper class that helps to display a grid or to know whether a ship belongs to the grid or not
  */
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
    * create a grid in list form
    * @param coordX
    * @param coordY
    * @param maxX
    * @param maxY
    * @return
    */
  def createGridInList(coordX: Char, coordY: Int, maxX: Char, maxY: Int):  List[Square] = {
    if(coordX == maxX && coordY == maxY) Nil
    else if (coordY == maxY) {
      val newX = (coordX.toInt + 1).toChar // increment the letter coord
      Square(coordX, coordY) ::  createGridInList(newX, 0, maxX, maxY)
    }else {
      Square(coordX, coordY) ::  createGridInList(coordX, coordY + 1, maxX, maxY)
    }
  }


  /**
    * create a grid full of square
    * @return
    */
  private def createGrid (): Array[Array[Square]] = {

    /**
      * fill the matrix with squares
      * @param matrix
      * @param i
      * @param j
      * @return
      */
    def fillMatrix(matrix: Array[Array[Square]], i: Int, j: Int): Array[Array[Square]] = {
      if (i == 9 && j == 9){
        val x = (i + 65).toChar
        matrix(i)(j) = Square(x, j)
        matrix
      }
      else if(j == 9){
        val x = (i + 65).toChar
        matrix(i)(j) = Square(x, j)

        fillMatrix(matrix, i+1, 0)
      }else{
        val x = (i + 65).toChar
        matrix(i)(j) = Square(x, j)

        fillMatrix(matrix, i, j+1)
      }
    }
    fillMatrix(Array.ofDim[Square](10, 10) , 0, 0)
  }


  /**
    *
    * @param grid
    * @return
    */
  private def fillGridWithCustomSquare(grid: Array[Array[Square]], list: List[Square]): Array[Array[Square]] = {
    if(list.isEmpty) grid
    else {
      val j =  list.head.coordY
      val i = list.head.coordX.toInt - 65
      grid(j)(i) = list.head

      fillGridWithCustomSquare(grid, list.tail)
    }
  }


  private def navyToSquareList(list: List[Ship]): List[Square] = {
    if(list.isEmpty) Nil
    else {
      list.head.positions ::: navyToSquareList(list.tail)
    }
  }

  private def shotsToListSquare(list: List[Shot]): List[Square] = {
    if (list.isEmpty) Nil
    else list.head.toSquare :: shotsToListSquare(list.tail)
  }



  /*
    =========================== DISPLAY METHOD =========================================================
   */
  /**
    * permit to have the labels for the number coordinate in the grid
    * @return
    */
  private def charLineSquares (max: Int, cpt: Int): String = {
    if(cpt == max) ""
    else {
      val char = (cpt + 65).toChar
      // + 65 because we start from the character ascii A (A = 65 in the table)
      " " + char + " " + charLineSquares(max, cpt + 1)
    }
  }

  /**
    * display the grid given in parameter
    * @param grid
    * @return
    */
  private def displayGrid (grid: Array[Array[Square]]): String = {

    def througGrid(grid: Array[Array[Square]], i: Int, j: Int): String = {
      if(i == 9 && j == 9) grid(i)(j).toString + " "+ Console.RESET + "\n"
      else if(j == 9){
        grid(i)(j).toString + " \n" + througGrid(grid, i+1, 0)
      }else if (j == 0) {
        " " +  Console.BOLD + Console.GREEN + i + Console.RESET +" " +
          grid(i)(j).toString  + througGrid(grid, i, j+1)
        // add the number at the beginning of the row
      }
      else {
        Console.CYAN_B + grid(i)(j).toString  + througGrid(grid, i, j+1)
      }
    }
    Console.BOLD + Console.RED + " - " + charLineSquares(10, 0) + Console.RESET +
      "\n" + througGrid(grid, 0, 0)
  }



  /**
    * displays the list given in parameter in grid form
    * @param list
    * @param shotRecord represent the opponent shot
    */
  private def display(list: List[Square], shotRecord: List[Square] = Nil): String = {
    var grid = createGrid()
    grid = fillGridWithCustomSquare(grid, shotRecord)
    grid = fillGridWithCustomSquare(grid, list)




    displayGrid(grid)
  }



  /**
    * display a list of ships in a grid
    * @param navy
    */
  def displayNavy(navy: List[Ship], shotRecord: List[Shot]): Unit = {
    val l = navyToSquareList(navy)
    val l2 = shotsToListSquare(shotRecord)
    println("\nNavy:\n" + display(l, l2) + "\n")
  }


  /**
    * display a list of shot in a grid
    * @param list
    */
  def displayShotRecord(list: List[Shot]): Unit= {
    val l = shotsToListSquare(list)
    println("\nShot record:\n" +
      "    - X: Shot successful \n" +
      "    - O: Shot failed" +
      "\n" + display(l) + "\n")
  }
}
