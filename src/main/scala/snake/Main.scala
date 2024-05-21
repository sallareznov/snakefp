package snake

import scalafx.animation.KeyFrame
import scalafx.animation.Timeline
import scalafx.animation.Timeline.*
import scalafx.application.JFXApp3
import scalafx.application.JFXApp3.PrimaryStage
import scalafx.beans.property.ObjectProperty
import scalafx.scene.Scene
import scalafx.scene.paint.Color.*
import scalafx.scene.shape.Rectangle
import scalafx.util.Duration
import scalafx.beans.property.IntegerProperty
import javafx.scene.input.{KeyCode, KeyEvent}
import javafx.scene.input.KeyCode.*

object Main extends JFXApp3 {

  private val windowSize: Int = 600
  val cellSize: Int           = 25
  val numberOfCells: Int      = windowSize / cellSize

  override def start(): Unit = {
    val gameState: ObjectProperty[GameState] = ObjectProperty(
      GameState(
        Snake(List((250, 200), (225, 200), (200, 200))),
        Food.random(numberOfCells, cellSize)
      )
    )

    val directionState: ObjectProperty[Direction] = ObjectProperty(Direction.RIGHT)

    stage = new PrimaryStage {
      title = "Snake"
      width = windowSize
      height = windowSize
      scene = new Scene {
        fill = White
        content = gameState.value.draw(cellSize)
        gameState.onChange {
          content = gameState.value.draw(cellSize)
        }
        onKeyPressed = updateDirection(directionState, _)
      }
    }

    new Timeline {
      keyFrames = List(
        KeyFrame(
          time = Duration(500),
          onFinished = _ => gameState.update(gameState.value.move(directionState.value, windowSize, numberOfCells, cellSize))
        )
      )
      cycleCount = Indefinite
    }.play()
  }

  private def updateDirection(directionState: ObjectProperty[Direction], key: KeyEvent): Unit =
    key.getCode match {
      case UP    => directionState.update(Direction.UP)
      case DOWN  => directionState.update(Direction.DOWN)
      case LEFT  => directionState.update(Direction.LEFT)
      case RIGHT => directionState.update(Direction.RIGHT)
      case _     => ()
    }

}
