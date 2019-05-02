package algoLab10

import java.io.*
import java.lang.Integer.min
import java.lang.NullPointerException
import java.util.*
import kotlin.collections.ArrayList


object C {
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

    var maxFlow = 0
    var res = 0

    private fun run() {
        N = IO.nextInt()
        M = IO.nextInt()
        S = IO.nextInt() - 1
        T = IO.nextInt() - 1
        graph = Array(N) { ArrayList<Edge>() }
        for (i in 0 until M) {
            val a = IO.nextInt() - 1
            val b = IO.nextInt() - 1
            val edge = Edge(a, b, 1)
            val backEdge = Edge(b, a, 0)
            edge.backEdge = backEdge
            backEdge.backEdge = edge
            edges.add(edge)
            graph[a].add(edge)
            graph[b].add(backEdge)

        }
        while (true) {
            visited = Array(N) { false }
            val res = flowDfs(S, Int.MAX_VALUE)
            if (res == 0 || graph[0].sumBy { it.flow } >= 2) break
        }
        visited = Array(N) { false }
        val path1 = ArrayList<Int>()
        pathsDfs(S, path1)
        visited = Array(N) { false }
        val path2 = ArrayList<Int>()
        pathsDfs(S, path2)
        if (res == 2) {
            IO.println("YES")
            path1.forEach {
                IO.print("$it ")
            }
            IO.println("")
            path2.forEach {
                IO.print("$it ")
            }
        } else {
            IO.println("NO")
        }

    }

    private fun pathsDfs(from: Int, path: ArrayList<Int>) {
        path.add(from + 1)
        if (from == T) {
            res++
            return
        }
        if (visited[from]) {
            return
        }
        for (edge in graph[from]) {
            val to = edge.to
            if (edge.flow == 1 && !edge.used) {
                edge.used = true
                pathsDfs(to, path)
                break
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
                    if (edge.from == S) {
                        maxFlow += left
                    }
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