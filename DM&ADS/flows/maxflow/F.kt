package algoLab10

import java.io.*
import java.lang.Integer.MAX_VALUE
import java.lang.Integer.min
import java.lang.NullPointerException
import java.util.*
import kotlin.collections.ArrayList
import kotlin.math.sqrt


object F {
    const val fileName = ""

    //================================================================================
    class Edge(
            val from: Int,
            val to: Int,
            val cap: Int
    ) {
        var backEdge: Edge? = null
        var flow: Int = 0


    }

    var N: Int = 0
    var S: Int = 0
    var T: Int = 0
    lateinit var graph: Array<ArrayList<Edge>>
    lateinit var visited: Array<Boolean>
    val points = ArrayList<Triple<Double, Double, Double>>()
    val targets = ArrayList<Pair<Double, Double>>()

    private fun run() {
        N = IO.nextInt()
        for (i in 0 until N) {
            points.add(Triple(IO.nextDouble(), IO.nextDouble(), IO.nextDouble()))
        }
        for (i in 0 until N) {
            targets.add(Pair(IO.nextDouble(), IO.nextDouble()))
        }
        var l = 0.0
        var r = MAX_VALUE.toDouble()
        while (r - l > 0.0001) {
            val m = (r + l) / 2
            if (matching(m)) r = m else l = m
        }
        IO.print(l)
    }

    private fun matching(maxTime: Double): Boolean {
        S = N + N
        T = N + N + 1
        graph = Array(N + N + 2) { ArrayList<Edge>() }
        for (i in 0 until N) {
            for (j in 0 until N) {
                val point = points[i]
                val target = targets[j]
                if (sqrt((point.first - target.first) * (point.first - target.first) +
                                (point.second - target.second) * (point.second - target.second)) / point.third > maxTime) {
                    continue
                }
                val to = N + j
                val edge = Edge(i, to, 1)
                val backEdge = Edge(to, i, 0)
                edge.backEdge = backEdge
                backEdge.backEdge = edge
                graph[i].add(edge)
                graph[to].add(backEdge)
            }
        }
        for (i in 0 until N) {
            val edge = Edge(S, i, 1)
            val backEdge = Edge(i, S, 0)
            edge.backEdge = backEdge
            backEdge.backEdge = edge
            graph[S].add(edge)
            graph[i].add(backEdge)
        }
        for (i in N until N + N) {
            val edge = Edge(i, T, 1)
            val backEdge = Edge(T, i, 0)
            edge.backEdge = backEdge
            backEdge.backEdge = edge
            graph[i].add(edge)
            graph[T].add(backEdge)
        }
        while (true) {
            visited = Array(N + 2 + N) { false }
            val res = flowDfs(S, Int.MAX_VALUE)
            if (res == 0) break
        }
        val maxFlow = graph[S].sumBy { it.flow }
        return N == maxFlow
    }


    private fun flowDfs(from: Int, c: Int): Int {
        if (from == T) {
            return c
        }
        if (visited[from]) {
            return 0
        }
        visited[from] = true
        for (edge in graph[from]) {
            val to = edge.to
            if (!visited[to] && edge.flow < edge.cap) {
                val left = flowDfs(to, min(c, edge.cap - edge.flow))
                if (left > 0) {
                    edge.flow += left
                    if (edge.backEdge != null) {
                        edge.backEdge!!.flow -= left
                    }
                    return left
                }

            }
        }
        return 0

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