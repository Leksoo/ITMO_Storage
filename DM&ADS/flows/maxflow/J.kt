package algoLab10

import java.io.*
import java.lang.Long.max
import java.lang.Math.abs
import java.lang.NullPointerException
import java.util.*
import kotlin.collections.ArrayList


object J {
    const val fileName = ""

    //================================================================================

    class Rectangle() {
        var xLeftLow: Long = 0
        var yLeftLow: Long = 0
        var xLeftHigh: Long = 0
        var yLeftHigh: Long = 0
        var xRightLow: Long = 0
        var yRightLow: Long = 0
        var xRightHigh: Long = 0
        var yRightHigh: Long = 0


        constructor(x1: Long, y1: Long, x2: Long, y2: Long) : this() {
            if (x1 < x2) {
                if (y1 < y2) {
                    xLeftLow = x1
                    yLeftLow = y1
                    xRightHigh = x2
                    yRightHigh = y2
                    xLeftHigh = x1
                    yLeftHigh = y2
                    xRightLow = x2
                    yRightLow = y1
                } else {
                    xLeftLow = x1
                    yLeftLow = y2
                    xRightHigh = x2
                    yRightHigh = y1
                    xLeftHigh = x1
                    yLeftHigh = y1
                    xRightLow = x2
                    yRightLow = y2
                }
            } else {
                if (y1 < y2) {
                    xLeftLow = x2
                    yLeftLow = y1
                    xRightHigh = x1
                    yRightHigh = y2
                    xLeftHigh = x2
                    yLeftHigh = y2
                    xRightLow = x1
                    yRightLow = y1
                } else {
                    xLeftLow = x2
                    yLeftLow = y2
                    xRightHigh = x1
                    yRightHigh = y1
                    xLeftHigh = x2
                    yLeftHigh = y1
                    xRightLow = x1
                    yRightLow = y2
                }
            }
        }
    }


    var N: Int = 0
    var W: Long = 0
    lateinit var graph: Array<ArrayList<Pair<Int, Long>>>
    val rects = ArrayList<Rectangle>()

    private fun distance(rect1: Rectangle, rect2: Rectangle): Long {
        var width = 0L
        if (rect2.xLeftLow > rect1.xRightLow) {
            width = rect2.xLeftLow - rect1.xRightLow
        } else if (rect2.xRightLow < rect1.xLeftLow) {
            width = rect1.xLeftLow - rect2.xRightLow
        }
        width = abs(width)
        var height = 0L
        if (rect2.yLeftLow > rect1.yLeftHigh) {
            height = rect2.yLeftLow - rect1.yLeftHigh
        } else if (rect2.yLeftHigh < rect1.yLeftLow) {
            height = rect1.yLeftLow - rect2.yLeftHigh
        }
        height = abs(height)
        return max(max(width, height), 0L)
    }

    private fun add(i: Int, j: Int, w: Long) {
        graph[i].add(Pair(j, w))
        graph[j].add(Pair(i, w))
    }

    private fun run() {
        N = IO.nextInt()
        W = IO.nextLong()
        for (i in 0 until N) {
            rects.add(Rectangle(IO.nextLong(), IO.nextLong(), IO.nextLong(), IO.nextLong()))
        }
        graph = Array(N + 2) { ArrayList<Pair<Int, Long>>() }
        // N - bottom , N+1 - up
        add(N, N + 1, W)
        for (i in 0 until N) {
            val rect = rects[i]
            add(i, N, rect.yLeftLow)
            add(i, N + 1, W - rect.yLeftHigh)
            for (j in i + 1 until N) {
                add(i, j, distance(rect, rects[j]))
            }
        }

        val d = LongArray(N + 2) { (Int.MAX_VALUE).toLong() * 50001 }
        val used = BooleanArray(N + 2) { false }
        d[N] = 0
        for (i in 0 until N + 2) {
            var v = -1
            for (j in 0 until N + 2) {
                if (!used[j] && (v == -1 || d[j] < d[v]))
                    v = j
            }
            used[v] = true
            for (edge in graph[v]) {
                val to = edge.first
                if (d[v] + edge.second < d[to]) {
                    d[to] = edge.second + d[v]
                }
            }
        }
        IO.println(d[N + 1])


    }


    //==================================================================================

    @JvmStatic
    fun main(args: Array<String>) {
        this.run()
        IO.writer.flush()
        IO.reader.close()

    }

    object IO {
        val reader: BufferedReader
        val writer: PrintWriter
        private var tokenizer: StringTokenizer? = null

        init {
            if (fileName.isEmpty()) {
                reader = BufferedReader(InputStreamReader(System.`in`))
                writer = PrintWriter(System.out)
            } else {
                reader = BufferedReader(FileReader("$fileName.in"))
                writer = PrintWriter(FileWriter("$fileName.out"))
            }
        }

        fun next(): String {
            while (tokenizer == null || !tokenizer!!.hasMoreTokens()) {
                val line = reader.readLine() ?: throw NullPointerException()
                if (line == "") {
                    return ""
                }
                tokenizer = StringTokenizer(line)
            }
            return tokenizer!!.nextToken()
        }

        fun nextInt(): Int {
            return next().toInt()
        }

        fun nextDouble(): Double {
            return next().toDouble()
        }

        fun nextLong(): Long {
            return next().toLong()
        }

        fun <T> print(obj: T) {
            writer.print(obj)
        }

        fun <T> println(obj: T) {
            writer.println(obj)
        }
    }
}