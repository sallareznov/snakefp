package snake

import scalafx.application.JFXApp3
import scalafx.application.JFXApp3.PrimaryStage
import scalafx.scene.Scene
import scalafx.scene.paint.Color.*
import scalafx.scene.shape.Rectangle

object Main extends JFXApp3 {

  override def start(): Unit = {
    val snake = Snake(List((250, 200), (225, 200), (200, 200)))

    stage = new PrimaryStage {
        title = "Snake"
        width = 600
        height = 600
        scene = new Scene {
            fill = White
            content = snake.body.map { (cellX, cellY) =>
                new Rectangle {
                    x = cellX
                    y = cellY
                    width = 25
                    height = 25
                    fill = Green
                }
            }
        }
    }
  }
  
}
