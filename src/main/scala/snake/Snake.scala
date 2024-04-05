package snake

final case class Snake(body: List[(Double, Double)]) {

  def move(direction: Int): Snake = {
    val (oldHeadX, oldHeadY) = body.head

    direction match {
      case 1 => copy((oldHeadX, oldHeadY - 25) :: body.init)
      case 2 => copy((oldHeadX, oldHeadY + 25) :: body.init)
      case 3 => copy((oldHeadX - 25, oldHeadY) :: body.init)
      case 4 => copy((oldHeadX + 25, oldHeadY) :: body.init)
      case _ => copy((oldHeadX, oldHeadY) :: body.init)
    }
  }

}
