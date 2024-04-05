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
import javafx.scene.input.KeyCode
import javafx.scene.input.KeyCode.*

object Main extends JFXApp3 {

  // stage (scene)
  // timeline on stage
  // timeline onFinished => update snakeState => onChange => redraw snake

  // direction:
  // 1 = haut
  // 2 = bas
  // 3 = gauche
  // 4 = droite

  override def start(): Unit = {
    val snakeState: ObjectProperty[Snake] = ObjectProperty(Snake(List((250, 200), (225, 200), (200, 200))))
    val direction                         = IntegerProperty(4) // droite

    stage = new PrimaryStage {
      title = "Snake"
      width = 600
      height = 600
      scene = new Scene {
        fill = White
        content = drawSnake(snakeState)
        snakeState.onChange {
          content = drawSnake(snakeState)
        }
        onKeyPressed = { key =>
          key.getCode() match {
            case UP    => direction.update(1)
            case DOWN  => direction.update(2)
            case LEFT  => direction.update(3)
            case RIGHT => direction.update(4)
            case _     => ()
          }
        }
      }
    }

    new Timeline {
      keyFrames = List(KeyFrame(time = Duration(25), onFinished = _ => snakeState.update(snakeState.value)))
      cycleCount = Indefinite
    }.play()

  }

  def drawSnake(snakeState: ObjectProperty[Snake]) =
    snakeState.value.body.map { (cellX, cellY) =>
      new Rectangle {
        x = cellX
        y = cellY
        width = 25
        height = 25
        fill = Green
      }
    }

}
