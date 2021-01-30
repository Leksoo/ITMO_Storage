package lab5.drawing

import java.awt.*
import java.awt.event.WindowAdapter
import java.awt.event.WindowEvent
import java.awt.geom.Ellipse2D
import kotlin.system.exitProcess


class AWTDrawingApi(
    override val drawingAreaWidth: Int,
    override val drawingAreaHeight: Int
) : DrawingApi {

    override fun draw(drawFunc: (DrawBuilder) -> Unit) {
        val frame: Frame = object : Frame() {
            override fun paint(g: Graphics) {
                val graphics2D = g as Graphics2D
                graphics2D.font = Font("TimesRoman", Font.PLAIN, 40)
                drawFunc.invoke(AWTDrawBuilder(graphics2D))
            }
        }
        frame.addWindowListener(object : WindowAdapter() {
            override fun windowClosing(we: WindowEvent?) {
                exitProcess(0)
            }
        })
        frame.setSize(drawingAreaWidth, drawingAreaHeight)
        frame.isVisible = true
    }

    class AWTDrawBuilder(
        private val graphics2D: Graphics2D
    ) : DrawBuilder {
        override fun drawCircle(centerX: Int, centerY: Int, radius: Int) {
            graphics2D.apply {
                paint = Color.BLUE
                fill(
                    Ellipse2D.Double(
                        (centerX - radius).toDouble(),
                        (centerY - radius).toDouble(),
                        2.0 * radius,
                        2.0 * radius
                    )
                )
            }
        }

        override fun drawString(centerX: Int, centerY: Int, str: String) {
            graphics2D.apply {
                paint = Color.BLACK
                drawString(str, centerX, centerY)
            }
        }

        override fun drawLine(fromX: Int, fromY: Int, toX: Int, toY: Int) {
            graphics2D.apply {
                paint = Color.RED
                drawLine(fromX, fromY, toX, toY)
            }
        }
    }
}