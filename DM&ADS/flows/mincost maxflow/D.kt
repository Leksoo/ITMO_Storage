package AlgoLab11

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

    var N: Int = 3
    var M: Int = 3
    var S: Int = 0
    var T: Int = 0
    lateinit var graph: Array<ArrayList<Edge>>
    val edges = ArrayList<Edge>()
    lateinit var visited: Array<Boolean>

    fun addEdge(from: Int,to: Int,cap: Int){
        val edge = Edge(from, to, cap)
        val backEdge = Edge(to, from, 0)
        edge.backEdge = backEdge
        backEdge.backEdge = edge
        edges.add(edge)
        graph[from].add(edge)
        graph[to].add(backEdge)
    }

    private fun run() {

        S = N + M
        T = N + M + 1
        graph = Array(N + M + 2) { ArrayList<Edge>() }
        val r1 = IO.nextInt()
        val s1 = IO.nextInt()
        val p1 = IO.nextInt()
        addEdge(0,N+2,r1)
        addEdge(1,N,s1)
        addEdge(2,N+1,p1)
        addEdge(0,N,r1)
        addEdge(1,N+1,s1)
        addEdge(2,N+2,p1)
        for (i in 0 until N) {
            addEdge(S,i,Int.MAX_VALUE)
        }
        val r2 = IO.nextInt()
        val s2 = IO.nextInt()
        val p2 = IO.nextInt()
        addEdge(N,T,r2)
        addEdge(N+1,T,s2)
        addEdge(N+2,T,p2)
        while (true) {
            visited = Array(N + 2 + M) { false }
            val res = flowDfs(S, Int.MAX_VALUE)
            if (res == 0) break
        }
        val maxFlow = graph[S].sumBy { it.flow }
        IO.println(r1+s1+p1-maxFlow)


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