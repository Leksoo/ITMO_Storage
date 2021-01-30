import java.io.*
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap
import kotlin.math.ln
import kotlin.math.pow

object Q {
    val fileName = ""

    //================================================================================

    fun run() {
        val K1 = IO.nextInt()
        val K2 = IO.nextInt()
        val N = IO.nextInt()
        val classCount1 = MutableList(K1) { 0 }
        val classCount2 = MutableList(K2) { 0 }
        val table = MutableList(K1) { HashMap<Int, Int>() }
        repeat(N) {
            val x1 = IO.nextInt() - 1
            val x2 = IO.nextInt() - 1
            classCount1[x1]++
            classCount2[x2]++
            table[x1].putIfAbsent(x2, 0)
            table[x1][x2] = table[x1][x2]!! + 1
        }

        var res = 0.0
        for (k1 in 0 until K1) {
            val probK1 = classCount1[k1].toDouble() / N
            for ((k2, v) in table[k1]) {
                if (v == 0) continue
                val prob = v.toDouble() / classCount1[k1]
                res += probK1 * prob * ln(prob)
            }
        }
        res = -res

        IO.println("%.10f".format(res))
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