import java.io.*
import java.util.*
import kotlin.collections.ArrayList


object A {
    val fileName = ""
    //================================================================================

    fun run() {
        val n = IO.nextInt()
        IO.nextInt()
        val k = IO.nextInt()
        val items = ArrayList<Pair<Int, Int>>()
        val parts = Array(k) { ArrayList<Int>() }
        for (i in 1..n) {
            items.add(Pair(i, IO.nextInt()))
        }
        items.sortWith(Comparator<Pair<Int, Int>> { a, b ->
            when {
                a.second < b.second -> 1
                a.second > b.second -> -1
                else -> 0
            }
        })
        var curPart = 0
        for (item in items.map { it.first }) {
            parts[curPart].add(item)
            curPart = (curPart + 1) % k
        }

        IO.print(parts.joinToString("\n") { "${it.size} ${it.sorted().joinToString(" ")}" })

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