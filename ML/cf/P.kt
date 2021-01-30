import java.io.*
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap
import kotlin.math.pow

object P {
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

        // if table cell value is 0, E_ij = N * classCount1[k1] / N * classCount2[k2] / N
        // if all zeros: N * Sum_k1(classCount1[k1] / N) * Sum_k2(classCount1[k2] / N) = N * 1 * 1
        var res = N.toDouble()
        for (k1 in 0 until K1) {
            for ((k2, v) in table[k1]) {
                val nom = (v.toDouble() - N.toDouble() * classCount1[k1] / N * classCount2[k2] / N).pow(2)
                val denom = N.toDouble() * classCount1[k1] / N * classCount2[k2] / N
                res += nom / denom
                // it is Non-zero, subtract what we added
                res -= denom
            }

        }
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