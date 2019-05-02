package algoLab10

import java.io.*
import java.lang.Integer.min
import java.lang.NullPointerException
import java.util.*
import java.util.stream.Collectors
import kotlin.collections.ArrayList


object I {
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
    var S: Int = 0
    var T: Int = 0
    lateinit var graph: Array<ArrayList<Edge>>
    lateinit var table: Array<Array<Char>>
    lateinit var visited: Array<Boolean>
    lateinit var unplayedMatches: Array<ArrayList<Int>>
    lateinit var curScores: Array<Int>
    val edges = ArrayList<Edge>()

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
        table = Array(N) { Array(N) { ' ' } }
        curScores = Array(N) { 0 }
        unplayedMatches = Array(N) { ArrayList<Int>() }
        for (i in 0 until N) {
            val line = IO.nextLine()
            for (j in 0 until line.length) {
                val ch = line[j]
                table[i][j] = ch
                when (ch) {
                    'W' -> curScores[i] += 3
                    'w' -> curScores[i] += 2
                    'l' -> curScores[i] += 1
                    'L' -> {
                    }
                    '#' -> {
                    }
                    '.' -> unplayedMatches[i].add(j)

                }
            }
        }
        graph = Array(N + 2) { ArrayList<Edge>() }
        S = N
        T = N + 1
        for (i in 0 until N) {
            val need = IO.nextInt() - curScores[i]
            add(i, T, need)
            var can = 0
            for (j in unplayedMatches[i]){
                if(i>j){
                    can++
                    add(i, j, 3)

                }
            }
            add(S, i, can*3)

        }
        var maxFlow = 0
        while (true) {
            visited = Array(N + 2) { false }
            val res = dfsFlow(S, Short.MAX_VALUE.toInt())
            maxFlow += res
            if (res == 0) break
        }
        for (from in 0 until N) {
            for (edge in graph[from]) {
                val to = edge.to
                if (to >=N || edge.cap == 0) {
                    continue
                }
                when (edge.flow) {
                    0 -> {
                        table[from][to] = 'W'
                        table[to][from] = 'L'

                    }
                    1 -> {
                        table[from][to] = 'w'
                        table[to][from] = 'l'

                    }
                    2 -> {
                        table[from][to] = 'l'
                        table[to][from] = 'w'

                    }
                    3 -> {
                        table[from][to] = 'L'
                        table[to][from] = 'W'

                    }
                }
            }
        }
        table.forEach { i ->
            i.forEach { j ->
                IO.print(j)
            }
            IO.println("")
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