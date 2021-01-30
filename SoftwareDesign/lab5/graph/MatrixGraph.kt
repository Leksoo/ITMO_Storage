package lab5.graph

import lab5.drawing.DrawingApi
import lab5.graph.Graph

class MatrixGraph(
    private val matrix: List<List<Int>>,
    drawingApi: DrawingApi
) : Graph(matrix.size, drawingApi) {

    override fun drawGraph() {
        drawingApi.draw {
            drawVertices(it)
            for (i in matrix.indices) {
                for (j in matrix[i].indices) {
                    if (matrix[i][j] == 1) {
                        drawEdge(it, i, j)
                    }
                }
            }
        }
    }

    companion object {
        fun buildFromInput(drawingApi: DrawingApi): MatrixGraph {
            val n = readLine()?.toInt()
            require(n != null) { "size of matrix was expected" }
            val matrix = ArrayList<List<Int>>()
            repeat(n) {
                readLine()?.split(" ")?.map { it.toInt() }?.let { row -> matrix.add(row) }
            }
            require(matrix.size > 0 && matrix.size == matrix[0].size)
            { "matrix should be square and not empty" }
            return MatrixGraph(matrix, drawingApi)
        }
    }

}