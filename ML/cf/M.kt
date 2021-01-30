import java.io.*
import java.util.*
import kotlin.collections.ArrayList
import kotlin.math.pow

object M {
    val fileName = ""

    //================================================================================


    data class DataPair(
        val x: Int,
        var xRank: Int,
        val y: Int,
        var yRank: Int
    )

    fun run() {
        val N = IO.nextInt()
        var objs = ArrayList<DataPair>()
        repeat(N) {
            objs.add(DataPair(IO.nextInt(), 0, IO.nextInt(), 0))
        }

        var rank = 1
        objs.sortBy { it.x }
        objs.forEach {
          it.xRank = rank++
        }
        rank = 1
        objs.sortBy { it.y }
        objs.forEach {
            it.yRank = rank++
        }

        val diffSq = objs.map { (it.xRank - it.yRank).toDouble().pow(2) }.sum()
        val denom = N.toDouble() * (N.toDouble() * N.toDouble() - 1.0)
        IO.println("%.10f".format(1.0 - 6.0 * diffSq / denom))

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