import java.io.*
import java.lang.Integer.min
import java.lang.NullPointerException
import java.util.*
import kotlin.collections.ArrayList


object H {
    const val fileName = "bring"

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
    var K: Int = 0
    var S: Int = 0
    var T: Int = 0
    lateinit var graph: Array<ArrayList<Edge>>
    lateinit var visited: Array<Boolean>
    val tunnels = ArrayList<Pair<Int, Int>>()

    private fun run() {
        N = IO.nextInt()
        M = IO.nextInt()
        K = IO.nextInt()
        S = IO.nextInt() - 1
        T = IO.nextInt() - 1 + N
        graph = Array(100000) { ArrayList<Edge>() }
        for (i in 0 until M) {
            val a = IO.nextInt() - 1
            val b = IO.nextInt() - 1
            tunnels.add(Pair(a, b))
        }
        var curDay = 0
        var delivered = 0
        while (delivered < K) {
            for (i in 0 until N) {
                addEdge(i + curDay * N, i + (curDay + 1) * N, 100000, 0)
            }
            for (i in 0 until M) {
                addEdge(tunnels[i].first + curDay * N, tunnels[i].second + (curDay + 1) * N
                        , 1, 0)
                addEdge(tunnels[i].second + curDay * N, tunnels[i].first + (curDay + 1) * N
                        , 1, 0)
            }

            delivered += findFlow(K - delivered)
            T += N
            curDay++
        }
        IO.println(curDay)
        val craftPosition = IntArray(K) { S }
        for (day in 0 until curDay) {
            val flights = ArrayList<String>()
            for (i in 0 until K) {
                val pos = craftPosition[i]
                for (edge in graph[pos]) {
                    if (edge.flow >= 1) {
                        --edge.flow
                        craftPosition[i] = edge.to
                        if (edge.cap == 1) {
                            flights.add(" ${i + 1} ${edge.to % N + 1} ")
                        }
                        break
                    }
                }
            }
            IO.print("${flights.size} ")
            flights.forEach { IO.print(it) }
            IO.println("")
        }

    }

    private fun addEdge(from: Int, to: Int, capOriginal: Int, capBack: Int = 0) {
        val edge = Edge(from, to, capOriginal)
        val backEdge = Edge(to, from, capBack)
        edge.backEdge = backEdge
        backEdge.backEdge = edge
        graph[from].add(edge)
        graph[to].add(backEdge)
    }


    private fun findFlow(leftToDeliver: Int): Int {
        var flow = 0
        while (true) {
            visited = Array(100000) { false }
            val cur = dfs(S, Integer.MAX_VALUE)
            if (cur == 0) break
            flow += cur
            if (leftToDeliver <= flow) break
        }
        return flow
    }

    private fun dfs(from: Int, c: Int): Int {
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