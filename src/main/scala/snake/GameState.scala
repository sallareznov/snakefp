package snake

import snake.Main.cellSize
import scalafx.scene.shape.Rectangle
import Direction.*
import scalafx.scene.paint.Color.*

final case class GameState(snake: Snake, food: Food) {

  def draw(cellSize: Int): List[Rectangle] = {
    val foodRectangle: Rectangle =
      new Rectangle {
        x = food.x
        y = food.y
        width = cellSize
        height = cellSize
        fill = Red
      }

    val snakeRectangles: List[Rectangle] =
      snake.body.map { (cellX, cellY) =>
        new Rectangle {
          x = cellX
          y = cellY
          width = cellSize
          height = cellSize
          fill = Green
        }
      }

    foodRectangle :: snakeRectangles
  }

  def move(direction: Direction, windowSize: Int, numberOfCells: Int, cellSize: Int): GameState = {
    val (newHeadX, newHeadY) = newHead(direction, cellSize)

    if (canEat(newHeadX, newHeadY) || snake.body.tail.contains(newHeadX, newHeadY)) {
      copy(
        snake = snake.copy(body = (newHeadX, newHeadY) :: snake.body),
        food = Food.random(numberOfCells, cellSize)
      )
    } else {
      copy(
        snake = snake.copy(body = (newHeadX, newHeadY) :: snake.body.init)
      )
    }
  }

  private def newHead(direction: Direction, cellSize: Int): (Double, Double) = {
    val (oldHeadX, oldHeadY) = snake.body.head

    direction match {
      case UP    => (oldHeadX, oldHeadY - cellSize)
      case DOWN  => (oldHeadX, oldHeadY + cellSize)
      case LEFT  => (oldHeadX - cellSize, oldHeadY)
      case RIGHT => (oldHeadX + cellSize, oldHeadY)
    }
  }

  private def canEat(newHeadX: Double, newHeadY: Double) = (newHeadX, newHeadY) == (food.x, food.y)

  //private def outOfWindow(x: Int, y: Int, windowSize: Int) = (0 to 600).contains()

}
