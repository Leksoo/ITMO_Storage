package algoLab10

import java.io.*
import java.lang.Integer.min
import java.lang.NullPointerException
import java.util.*
import kotlin.collections.ArrayList


object D {
    const val fileName = ""

    //================================================================================
    class Edge(
            val from: Int,
            val to: Int,
            val cap: Int
    ) {
        var backEdge: Edge? = null
        var flow: Int = 0
        var used = false


    }

    var N: Int = 0
    var M: Int = 0
    var S: Int = 0
    var T: Int = 0
    lateinit var graph: Array<ArrayList<Edge>>
    val edges = ArrayList<Edge>()
    lateinit var visited: Array<Boolean>

    private fun run() {
        N = IO.nextInt()
        M = IO.nextInt()
        S = N + M
        T = N + M + 1
        graph = Array(N + M + 2) { ArrayList<Edge>() }
        for (i in 0 until N) {
            var a = IO.nextInt()
            while (a != 0) {
                val to = a + N - 1
                val edge = Edge(i, to, 1)
                val backEdge = Edge(to, i, 0)
                edge.backEdge = backEdge
                backEdge.backEdge = edge
                edges.add(edge)
                graph[i].add(edge)
                graph[to].add(backEdge)
                a = IO.nextInt()
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
        for (i in N until N + M) {
            val edge = Edge(i, T, 1)
            val backEdge = Edge(T, i, 0)
            edge.backEdge = backEdge
            backEdge.backEdge = edge
            graph[i].add(edge)
            graph[T].add(backEdge)
        }
        while (true) {
            visited = Array(N + 2 + M) { false }
            val res = flowDfs(S, Int.MAX_VALUE)
            if (res == 0) break
        }
        val maxFlow = graph[S].sumBy { it.flow }
        IO.println(maxFlow)
        for (edge in edges){
            if(edge.flow == 1){
                IO.println("${edge.from+1} ${edge.to+1-N}")
            }
        }


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