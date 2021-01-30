package lab5

import lab5.drawing.AWTDrawingApi
import lab5.drawing.DrawingApi
import lab5.drawing.FXDrawingApi
import lab5.graph.EdgeListGraph
import lab5.graph.Graph
import lab5.graph.MatrixGraph
import java.lang.IllegalArgumentException

const val WINDOW_WIDTH = 1000
const val WINDOW_HEIGHT = 1000

fun main(args: Array<String>) {
    if (args.size < 2) {
        throw  IllegalArgumentException("Wrong number of arguments")
    }
    var drawingApi: DrawingApi? = null
    var graph: Graph? = null

    drawingApi = when (args[0]) {
        "awt" -> AWTDrawingApi(WINDOW_WIDTH, WINDOW_HEIGHT)
        "fx" -> FXDrawingApi((WINDOW_WIDTH * 0.7).toInt(), (WINDOW_HEIGHT * 0.7).toInt())
        else -> null
    }
    drawingApi?.let {
        graph = when (args[1]) {
            "matrix" -> MatrixGraph.buildFromInput(drawingApi)
            "edges" -> EdgeListGraph.buildFromInput(drawingApi)
            else -> null
        }
    }

    if (drawingApi == null || graph == null) {
        throw IllegalArgumentException("wrong arguments")
    }
    graph!!.drawGraph()
}
