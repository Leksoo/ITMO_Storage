package algoLab10

import java.io.*
import java.lang.Integer.min
import java.lang.NullPointerException
import java.util.*
import kotlin.collections.ArrayList


object A {
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
    var M: Int = 0
    lateinit var graph: Array<ArrayList<Edge>>
    val edges = ArrayList<Edge>()
    lateinit var visited: Array<Boolean>

    private fun run() {
        N = IO.nextInt()
        M = IO.nextInt()
        graph = Array(N) { ArrayList<Edge>() }
        for (i in 0 until M) {
            val a = IO.nextInt() - 1
            val b = IO.nextInt() - 1
            val c = IO.nextInt()
            val edge = Edge(a, b, c)
            val backEdge = Edge(b, a, c)
            edge.backEdge = backEdge
            backEdge.backEdge = edge
            edges.add(edge)
            graph[a].add(edge)
            graph[b].add(backEdge)

        }
        while (true) {
            visited = Array(N) { false }
            if (dfs(0, Int.MAX_VALUE) == 0) break
        }
        val maxFlow = graph[0].sumBy { it.flow }
        IO.println(maxFlow)
        for (edge in edges) {
            IO.println(edge.flow)
        }


    }

    private fun dfs(from: Int, c: Int): Int {
        if (from == N - 1) {
            return c
        }
        if (visited[from]) {
            return 0
        }
        visited[from] = true
        for (edge in graph[from]) {
            val to = edge.to
            if (!visited[to] && edge.flow < edge.cap) {
                val left = dfs(to, min(c, edge.cap - edge.flow))
                if (left > 0) {
                    edge.flow += left
                    edge.backEdge!!.flow -= left
                    return left
                }

            }
        }
        return 0

    }
    //==================================================================================

    @JvmStatic
    fun main(args: Array<String>) {
        A.run()
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