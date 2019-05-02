package algoLab10

import java.io.*
import java.lang.Integer.min
import java.lang.NullPointerException
import java.util.*
import java.util.stream.Collectors
import kotlin.collections.ArrayList
import kotlin.collections.HashMap


object B {
    const val fileName = ""

    //================================================================================
    class Edge(
            val from: Int,
            val to: Int,
            val cap: Int,
            val orderNum: Int
    ) {
        var backEdge: Edge? = null
        var flow: Int = 0


    }

    var N: Int = 0
    var M: Int = 0
    lateinit var graph: Array<HashMap<Int, Edge>>
    val edges = ArrayList<Edge>()
    lateinit var visited: Array<Boolean>
    lateinit var firstUndeleted: Array<Int>
    lateinit var d: Array<Int>
    lateinit var cut: List<Edge>

    private fun run() {
        N = IO.nextInt()
        M = IO.nextInt()
        graph = Array(N) { HashMap<Int, Edge>() }
        for (i in 0 until M) {
            val a = IO.nextInt() - 1
            val b = IO.nextInt() - 1
            val c = IO.nextInt()
            val edge = Edge(a, b, c, i)
            val backEdge = Edge(b, a, c, i)
            edge.backEdge = backEdge
            backEdge.backEdge = edge
            edges.add(edge)
            graph[a][b] = edge
            graph[b][a] = backEdge

        }
        var res = 0
        d = Array(N) { Int.MAX_VALUE }
        while (!bfs()) {
            firstUndeleted = Array(N) { 0 }
            var flow: Int
            do {
                flow = dfsFlow(0, Int.MAX_VALUE)
                res += flow
            } while (flow != 0)
            d = Array(N) { Int.MAX_VALUE }

        }
        visited = Array(N) { false }
        dfsCut(0)
        cut = edges.stream().filter { visited[it.to] != visited[it.from] }.collect(Collectors.toList())
        IO.println("${cut.size} $res")
        cut.stream().sorted { o1, o2 -> o1.orderNum.compareTo(o2.orderNum) }
                .forEach { IO.print("${it.orderNum + 1} ") }
    }

    private fun bfs(): Boolean {
        d[0] = 0
        val q = ArrayDeque<Int>()
        q.add(0)
        while (!q.isEmpty()) {
            val from = q.pollFirst()
            for (edge in graph[from].values) {
                val to = edge.to
                if (d[to] == Int.MAX_VALUE && edge.flow < edge.cap) {
                    q.add(to)
                    d[to] = d[from] + 1
                }
            }
        }
        return d[N - 1] == Int.MAX_VALUE

    }

    private fun dfsCut(from: Int) {
        if (from == N - 1) {
            return
        }
        visited[from] = true
        for (edge in graph[from].values) {
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
        if (from == N - 1 || c == 0) {
            return c
        }
        while (firstUndeleted[from] < N) {
            val to = firstUndeleted[from]
            val edge = graph[from][to]
            if (edge == null) {
                firstUndeleted[from]++
                continue
            }
            if (d[from] + 1 == d[to]) {
                val left = dfsFlow(to, min(c, edge.cap - edge.flow))
                if (left > 0) {
                    edge.flow += left
                    edge.backEdge!!.flow -= left
                    return left
                }
            }
            firstUndeleted[from]++
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