package lab5.drawing

interface DrawingApi {
    val drawingAreaWidth: Int
    val drawingAreaHeight: Int
    fun draw(drawFunc: (DrawBuilder) -> Unit)
}

interface DrawBuilder {
    fun drawCircle(centerX: Int, centerY: Int, radius: Int)
    fun drawString(centerX: Int, centerY: Int, str: String)
    fun drawLine(fromX: Int, fromY: Int, toX: Int, toY: Int)
}