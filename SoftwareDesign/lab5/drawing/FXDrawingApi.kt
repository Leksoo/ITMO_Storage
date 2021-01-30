package lab5.drawing

import javafx.application.Application
import javafx.scene.canvas.GraphicsContext
import javafx.scene.paint.Color
import javafx.scene.text.Text
import javafx.stage.Stage


class FXDrawingApi(
    override val drawingAreaWidth: Int,
    override val drawingAreaHeight: Int
) : DrawingApi {

    var drawFunc: ((DrawBuilder) -> Unit)? = null

    override fun draw(drawFunc: (DrawBuilder) -> Unit) {
        this.drawFunc = drawFunc
        FXDrawingApp.fxDrawingApi = this
        Application.launch(FXDrawingApp::class.java)
    }

    class FXDrawBuilder(
        private val gc: GraphicsContext
    ) : DrawBuilder {
        override fun drawCircle(centerX: Int, centerY: Int, radius: Int) {
            gc.apply {
                fill = Color.BLUE
                fillOval(
                    (centerX - radius).toDouble(),
                    (centerY - radius).toDouble(),
                    2.0 * radius,
                    2.0 * radius
                )
            }
        }

        override fun drawString(centerX: Int, centerY: Int, str: String) {
            gc.apply {
                fill = Color.BLACK
                fillText(str, centerX.toDouble(), centerY.toDouble())
            }
        }

        override fun drawLine(fromX: Int, fromY: Int, toX: Int, toY: Int) {
            gc.apply {
                fill = Color.RED
                this.strokeLine(fromX.toDouble(), fromY.toDouble(), toX.toDouble(), toY.toDouble())
            }
        }
    }


}

