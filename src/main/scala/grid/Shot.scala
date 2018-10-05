package grid

/**
  * This object is a record of all data provided after a shot
  * the coordinate of the shot and the success of the failure.
  * The field isNear permit to know if the case of failure if the shot was closed to a ship
  * The last field shipName represent the shiep name of the ship hitten
  * @param coordX
  * @param coordY
  * @param hasTouch
  * @param isNear
  * @param shipName
  */
case class Shot(coordX: Char, coordY: Int, hasTouch: Boolean, isNear: Boolean = false, shipName: String = "None") {

  def toSquare = {
    var icon = ""
    if(hasTouch) icon = "X"
    else icon = "O"
    Square(coordX, coordY, hasTouch, icon = icon)
  }

}
