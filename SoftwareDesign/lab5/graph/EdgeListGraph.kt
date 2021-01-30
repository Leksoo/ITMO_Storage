package lab5.graph

import lab5.drawing.DrawingApi
import lab5.graph.Graph

class EdgeListGraph(
    n: Int,
    private val edgeList: List<Pair<Int, Int>>,
    drawingApi: DrawingApi
) : Graph(n, drawingApi) {

    override fun drawGraph() {
        drawingApi.draw {
            drawVertices(it)
            for ((a, b) in edgeList) {
                drawEdge(it, a, b)
            }
        }
    }

    companion object {
        fun buildFromInput(drawingApi: DrawingApi): EdgeListGraph {
            val n = readLine()?.toInt()
            require(n != null) { "number of vertices was expected" }
            val m = readLine()?.toInt()
            require(m != null) { "number of edges was expected" }
            val edgeList = ArrayList<Pair<Int, Int>>()
            repeat(m) {
                readLine()?.split(" ")?.map { it.toInt() }?.let { row ->
                    require(row.size == 2) { "wrong edge list format" }
                    edgeList.add(Pair(row[0], row[1]))
                }
            }
            return EdgeListGraph(n, edgeList, drawingApi)
        }
    }

}