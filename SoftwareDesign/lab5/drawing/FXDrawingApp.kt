package lab5.drawing

import javafx.application.Application
import javafx.scene.Group
import javafx.scene.Scene
import javafx.scene.canvas.Canvas
import javafx.scene.text.Font
import javafx.scene.text.FontWeight
import javafx.stage.Stage

class FXDrawingApp : Application() {

    override fun start(primaryStage: Stage) {
        fxDrawingApi?.let { d ->
            val root = Group()
            val canvas = Canvas(d.drawingAreaWidth.toDouble(), d.drawingAreaHeight.toDouble())
            val gc = canvas.graphicsContext2D
            gc.font = Font.font("TimesRoman", FontWeight.NORMAL, 40.0)
            d.drawFunc?.invoke(FXDrawingApi.FXDrawBuilder(gc))
            root.children.add(canvas)
            primaryStage.scene = Scene(root)
            primaryStage.show()
        }
    }

    companion object {
        var fxDrawingApi: FXDrawingApi? = null
    }
}