import javafx.scene.text.FontWeight
import java.io.*
import java.util.*
import kotlin.collections.ArrayList
import kotlin.math.pow
import kotlin.math.sqrt
import kotlin.random.Random

object L {
    val fileName = ""

    //================================================================================


    fun run() {
        val N = IO.nextInt()
        val objs = ArrayList<Pair<Int,Int>>()
        repeat(N) {
            objs.add(Pair(IO.nextInt(), IO.nextInt()))
        }
        val av1 = objs.map { it.first }.average()
        val av2 = objs.map { it.second }.average()
        var cov = 0.0
        var sum1 = 0.0
        var sum2 = 0.0
        for (obj in objs) {
            cov += (obj.first.toDouble() - av1) * (obj.second.toDouble() - av2)
            sum1 += (obj.first.toDouble() - av1) * (obj.first.toDouble() - av1)
            sum2 += (obj.second.toDouble() - av2) * (obj.second.toDouble() - av2)
        }
        val denom = sqrt(sum1*sum2)
        if (denom == 0.0) {
            IO.println(0.0)
        } else {
            IO.println("%.10f".format(cov / denom))
        }

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