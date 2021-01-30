package lab5.graph

import lab5.drawing.DrawBuilder
import lab5.drawing.DrawingApi
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.min
import kotlin.math.sin

abstract class Graph(
    protected val n: Int,
    protected val drawingApi: DrawingApi
) {
    private val areaCenter = Pair(drawingApi.drawingAreaWidth / 2, drawingApi.drawingAreaHeight / 2)
    private val drawDistance = min(drawingApi.drawingAreaWidth / 4, drawingApi.drawingAreaHeight / 4)
    private val vertexRadius = (drawDistance * 0.03).toInt()

    private fun getVertexCoords(id: Int): Pair<Int, Int> {
        val x = (areaCenter.first + cos(id * 2 * PI / n) * drawDistance).toInt()
        val y = (areaCenter.second + sin(id * 2 * PI / n) * drawDistance).toInt()
        return Pair(x, y)
    }

    abstract fun drawGraph()

    protected fun drawVertices(drawBuilder: DrawBuilder) {
        for (i in 0 until n) {
            val center = getVertexCoords(i)
            drawBuilder.drawCircle(center.first, center.second, vertexRadius)
            drawBuilder.drawString(center.first, center.second, i.toString())
        }
    }

    protected fun drawEdge(drawBuilder: DrawBuilder, from: Int, to: Int) {
        val fromCoords = getVertexCoords(from)
        val toCoords = getVertexCoords(to)
        drawBuilder.drawLine(fromCoords.first, fromCoords.second, toCoords.first, toCoords.second)
    }
}