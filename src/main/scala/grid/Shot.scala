package grid

/**
  * This object is a record of all data provided after a shot
  * the coordinate of the shot and the success of the failure.
  * The last field isNear permit to know if the case of failure if the shot was closed to a ship
  * @param coordX
  * @param coordY
  * @param hasTouch
  * @param isNear
  */
case class Shot(coordX: Char, coordY: Int, hasTouch: Boolean, isNear: Boolean = false) {

  def toSquare = Square(coordX, coordY, isTouched = true)

}
