package algoLab10

import java.io.*
import java.lang.Integer.min
import java.lang.NullPointerException
import java.util.*
import java.util.stream.Collectors
import kotlin.collections.ArrayList


object G {
    const val fileName = ""

    //================================================================================
    class Edge(
            val from: Int,
            val to: Int,
            val cap: Int,
            val i: Int = 0,
            val j: Int = 0
    ) {
        var backEdge: Edge? = null
        var flow: Int = 0


    }


    var N: Int = 0
    var M: Int = 0
    var S: Int = 0
    var T: Int = 0
    lateinit var graph: Array<ArrayList<Edge>>
    lateinit var table: Array<Array<Char>>
    lateinit var visited: Array<Boolean>
    val edges = ArrayList<Edge>()
    // i - in , N * M + i - out
    private fun addVertexEdge(i: Int, j: Int, cap: Int) {
        val from = i * M + j
        val to = N * M + i * M + j
        val edge = Edge(from, to, cap, i, j)
        edges.add(edge)
        val backEdge = Edge(to, from, 0, i, j)
        edge.backEdge = backEdge
        backEdge.backEdge = edge
        graph[from].add(edge)
        graph[to].add(backEdge)

    }

    const val BIG_CAP = Short.MAX_VALUE.toInt()

    private fun add(from: Int, to: Int, cap: Int, backCap: Int = 0) {
        val edge = Edge(from, to, cap)
        val backEdge = Edge(to, from, backCap)
        edge.backEdge = backEdge
        backEdge.backEdge = edge
        graph[from].add(edge)
        graph[to].add(backEdge)
    }

    private fun run() {
        N = IO.nextInt()
        M = IO.nextInt()
        graph = Array(2 * N * M) { ArrayList<Edge>() }
        table = Array(N) { Array(M) { ' ' } }
        for (i in 0 until N) {
            val line = IO.nextLine()
            for (j in 0 until line.length) {
                val ch = line[j]
                table[i][j] = ch
                when (ch) {
                    // '#' -> addVertexEdge(i, j, 0)
                    '.' -> addVertexEdge(i, j, 1)
                    '-' -> addVertexEdge(i, j, BIG_CAP)
                    'A' -> {
                        S = N * M + i * M + j
                    }
                    'B' -> {
                        T = i * M + j
                    }
                }
            }

        }
        for (i in 0 until N) {
            for (j in 0 until M) {
                val inp = i * M + j
                val out = N * M + i * M + j
                if (i != N - 1) {
                    add(out, M + inp, BIG_CAP)
                    add(out + M, inp, BIG_CAP)
                }
                if (j != M - 1) {
                    add(out, 1 + inp, BIG_CAP)
                    add(out + 1, inp, BIG_CAP)
                }
            }
        }
        var maxFlow = 0
        while (true) {
            visited = Array(N * M * 2) { false }
            val res = dfsFlow(S, Short.MAX_VALUE.toInt())
            maxFlow += res
            if (res == 0) break
        }
        if (maxFlow >= BIG_CAP) {
            IO.println(-1)
            return
        }
        if (maxFlow == 0) {
            IO.println(0)
            table.forEach { i ->
                i.forEach { j ->
                    IO.print(j)
                }
                IO.println("")
            }
            return
        }
        visited = Array(N * M * 2) { false }
        dfsCut(S)
        val cut = edges.stream().filter { visited[it.to] != visited[it.from] }.collect(Collectors.toList())
        for (edge in cut) {
            table[edge.i][edge.j] = '+'
        }
//        for (v in 0 until N * M) {
//            if (!visited[v]) continue
//            for (edge in graph[v]) {
//                if (edge.cap == 1 && edge.flow == 1 && !visited[edge.to]) {
//                    table[edge.i][edge.j] = '+'
//                    break
//                }
//            }
//
//        }
        IO.println(maxFlow)
        table.forEach { i ->
            i.forEach { j ->
                IO.print(j)
            }
            IO.println("")
        }
    }


    private fun dfsCut(from: Int) {
        if (from == T) {
            return
        }
        visited[from] = true
        for (edge in graph[from]) {
            val to = edge.to
            if (visited[to]) {
                continue
            }
            if (edge.flow < edge.cap) {
                dfsCut(to)
            }
        }
    }


    private fun dfsFlow(from: Int, c: Int): Int {
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
                val left = dfsFlow(to, min(c, edge.cap - edge.flow))
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

        fun nextLine(): String {
            return reader.readLine()
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